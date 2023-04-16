package com.scarretero.superhero.mapper;

import org.mapstruct.Mapper;

import com.scarretero.superhero.entity.SuperHero;
import com.scarretero.superhero.model.SuperHeroDto;

/**
 * @author SERGIO
 * 
 *         Interface for the superhero mapper
 *
 */
@Mapper(componentModel = "spring")
public interface SuperHeroMapper {

	/**
	 * Map from SuperHero to SuperHeroDto
	 * 
	 * @param superHero - the SuperHero entity
	 * @return {@link SuperHeroDto}
	 */
	SuperHeroDto fromSuperHeroToSuperHeroDto(SuperHero superHero);

	/**
	 * Map from SuperHeroDto to SuperHero
	 * 
	 * @param superHero - the SuperHero Dto
	 * @return {@link SuperHero}
	 */
	SuperHero fromSuperHeroDtoToSuperHero(SuperHeroDto superHeroDto);

}
