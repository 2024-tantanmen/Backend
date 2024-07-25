package com.tantanmen.carbofootprint.domain.classification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;


	@Column(name = "amount")
	private Integer amount;

	@Column(name = "kcal")
	private Double kcal;

	@Column(name = "carbohydrate")
	private Double carbohydrate;

	@Column(name = "sugar")
	private Double sugar;

	@Column(name = "fat")
	private Double fat;

	@Column(name = "protein")
	private Double protein;

	@Column(name = "calcium")
	private Double calcium;

	@Column(name = "phosphorus")
	private Double phosphorus;

	@Column(name = "natrium")
	private Double natrium;

	@Column(name = "kalium")
	private Double kalium;

	@Column(name = "magnesium")
	private Double magnesium;

	@Column(name = "iron")
	private Double iron;

	@Column(name = "zinc")
	private Double zinc;

	@Column(name = "cholesterol")
	private Double cholesterol;

	@Column(name = "transfat")
	private Double transfat;


}
