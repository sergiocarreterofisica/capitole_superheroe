/**
 * 
 */
package com.scarretero.superhero.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
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
	 * The superhero identificator
	 */
	@ApiModelProperty(value = "The superhero identificator")
	private Long id;

	/**
	 * The superhero name
	 */
	@ApiModelProperty(value = "The superhero name")
	private String name;

	/**
	 * The superhero city
	 */
	@ApiModelProperty(value = "The superhero city")
	private String city;
}
