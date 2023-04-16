package com.scarretero.superhero.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.scarretero.superhero.entity.SuperHero;

/**
 * @author SERGIO
 * 
 *         SuperHero interface repository
 *
 */
public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {

	/**
	 * Find superheroes by their names Find superheroes than have the parameter
	 * value in their name no matter case
	 * 
	 * @param name - Name of the supehero
	 * @return {@link List}
	 */
	public List<SuperHero> findByNameContainingIgnoreCase(String name);
	
	/**
	 * Find a superhero by name
	 * @param name - Name of the superhero
	 * @return {@link SuperHero}
	 */
	public SuperHero findByName(String name);

}
