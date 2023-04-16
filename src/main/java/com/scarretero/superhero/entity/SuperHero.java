package com.scarretero.superhero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author SERGIO
 * 
 *         Superheroes entity
 */
@Entity
@Table(name = "superhero")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class SuperHero implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 8243349440061547827L;

	/**
	 * The superheroe identificator
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The superheroe name
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	/**
	 * The superheroe city
	 */
	@Column(name = "city", nullable = false, unique = false)
	private String city;
}
