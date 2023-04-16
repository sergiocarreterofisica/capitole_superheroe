package com.scarretero.superhero.service;

import java.util.List;
import java.util.Optional;

import com.scarretero.superhero.model.SuperHeroDto;

/**
 * @author SERGIO
 * 
 *         Interface for the superhero service
 *
 */
public interface SuperHeroService {

	/**
	 * Find all the supeheroes
	 * 
	 * @return - {@link List}
	 */
	public List<SuperHeroDto> findAllSuperHeroes();

	/**
	 * Find superhero by id
	 * 
	 * @param id - Id of the superhero
	 * @return {@link Optional}
	 */
	public Optional<SuperHeroDto> findById(Long id);

//	/**
//	 * Find superhero by id
//	 * 
//	 * @param id - Id of the superhero
//	 * @return {@link SuperHeroDto}
//	 */
//	public SuperHeroDto findById(Long id);

	/**
	 * Find all the superheroes than contain the parameter value in his name no
	 * matter case
	 * 
	 * @param name - Name of the superhero
	 * @return {@link List}
	 */
	public List<SuperHeroDto> findAllSuperHeroesByNameContainingIgnoreCase(String name);

	/**
	 * Update a superhero
	 * 
	 * @param superHeroDto - Superhero to update
	 * @param id           - The superhero identifier
	 * @return {@link SuperHeroDto}
	 */
	public SuperHeroDto updateSuperHero(SuperHeroDto superHeroDto, Long id);

	/**
	 * Save a superhero
	 * 
	 * @param superHeroDto - Superhero to save
	 */
	public SuperHeroDto saveSuperHero(SuperHeroDto superHeroDto);

	/**
	 * Delete a superhero
	 * 
	 * @param id - Id of the superhero
	 * @return {@link Boolean}
	 */
	public Boolean deleteSuperHero(Long id);

}
