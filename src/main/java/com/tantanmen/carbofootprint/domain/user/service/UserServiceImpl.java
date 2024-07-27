package com.tantanmen.carbofootprint.domain.user.service;

import com.tantanmen.carbofootprint.domain.user.entity.Role;
import com.tantanmen.carbofootprint.domain.user.entity.User;
import com.tantanmen.carbofootprint.domain.user.enums.UserRoles;
import com.tantanmen.carbofootprint.domain.user.repository.UserRepository;
import com.tantanmen.carbofootprint.domain.user.web.dto.LoginRequestDto;
import com.tantanmen.carbofootprint.domain.user.web.dto.LoginResponseDto;
import com.tantanmen.carbofootprint.domain.user.web.dto.SignUpResponseDto;
import com.tantanmen.carbofootprint.domain.user.web.dto.SignUpRequestDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
import com.tantanmen.carbofootprint.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    // SignUp
    @Override
    public ResponseEntity<CustomApiResponse<SignUpResponseDto>> signUp(SignUpRequestDto.Request request) {

        log.info("SignUp loginId : {}", request.getLoginId());
        log.info("SignUp password : {}", request.getPassword());

        if(userRepository.existsByLoginId(request.getLoginId())) {
            CustomApiResponse<SignUpResponseDto> existsLoginId = CustomApiResponse.createFailWithoutData(HttpStatus.METHOD_NOT_ALLOWED.value(), "이미 존재하는 아이디 입니다.");

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(existsLoginId);
        }

        User user = User.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new ArrayList<>())
                .build();

        Role role = Role.builder()
                .role(UserRoles.ADMIN)
                .build();

        user.addRole(role);

        userRepository.save(user);

        List<Role> roles = user.getRoles().stream()
                .map(Function.identity())
                .collect(Collectors.toList());

        String token = jwtTokenProvider.createToken(user.getLoginId(), roles);

        SignUpResponseDto responseDto = SignUpResponseDto.builder()
                .token(token)
                .build();

        CustomApiResponse<SignUpResponseDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseDto, "회원가입에 성공하였습니다.");

        return ResponseEntity.ok(response);
    }

    // Login
    @Override
    public ResponseEntity<CustomApiResponse<LoginResponseDto>> login(LoginRequestDto.Request request) {

        log.info("Login loginId : {}", request.getLoginId());
        log.info("Login password : {}", request.getPassword());

        if(!userRepository.existsByLoginId(request.getLoginId())) {
            CustomApiResponse<LoginResponseDto> loginIdError = CustomApiResponse.createFailWithoutData(HttpStatus.METHOD_NOT_ALLOWED.value(), "잘못된 아이디입니다.");

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(loginIdError);
        }

        User user = userRepository.findByLoginId(request.getLoginId()).orElseThrow(RuntimeException::new);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            CustomApiResponse<LoginResponseDto> passwordError = CustomApiResponse.createFailWithoutData(HttpStatus.METHOD_NOT_ALLOWED.value(), "잘못된 비밀번호입니다.");

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(passwordError);
        }

        List<Role> roles = user.getRoles().stream()
                .map(Function.identity())
                .collect(Collectors.toList());

        String token = jwtTokenProvider.createToken(user.getLoginId(), roles);

        LoginResponseDto responseDto = LoginResponseDto.builder()
                .token(token)
                .build();


        CustomApiResponse<LoginResponseDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseDto, "로그인에 성공하였습니다.");

        return ResponseEntity.ok(response);
    }


}
