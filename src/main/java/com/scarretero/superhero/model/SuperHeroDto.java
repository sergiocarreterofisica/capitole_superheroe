/**
 * 
 */
package com.scarretero.superhero.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author SERGIO
 * 
 *         Superhero Dto
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SuperHeroDto implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 287892631292498403L;

	/**
	 * The superheroe identificator
	 */
	private Long id;

	/**
	 * The superheroe name
	 */
	private String name;

	/**
	 * The superheroe city
	 */
	private String city;
}
