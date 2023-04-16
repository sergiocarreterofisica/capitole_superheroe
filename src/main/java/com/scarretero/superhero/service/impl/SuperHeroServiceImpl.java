package com.scarretero.superhero.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scarretero.superhero.entity.SuperHero;
import com.scarretero.superhero.mapper.SuperHeroMapper;
import com.scarretero.superhero.model.SuperHeroDto;
import com.scarretero.superhero.repository.SuperHeroRepository;
import com.scarretero.superhero.service.SuperHeroService;

/**
 * @author SERGIO
 *
 *         The superhero service implementation
 */
@Service
public class SuperHeroServiceImpl implements SuperHeroService {

	/**
	 * The superhero repository
	 */
	private SuperHeroRepository repository;

	/**
	 * The superhero mapper
	 */
	private SuperHeroMapper mapper;

	/**
	 * Constructor
	 * 
	 * @param repository - the superhero repository
	 * @param mapper     - the superhero mapper
	 */
	public SuperHeroServiceImpl(SuperHeroRepository repository, SuperHeroMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	/**
	 * @see SuperHeroService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SuperHeroDto> findAllSuperHeroes() {

		List<SuperHero> superHeroList = (List<SuperHero>) repository.findAll();

		return superHeroList.stream().map(sh -> mapper.fromSuperHeroToSuperHeroDto(sh)).collect(Collectors.toList());

	}

	/**
	 * @see SuperHeroService#findById(Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<SuperHeroDto> findById(Long id) {

		Optional<SuperHero> superHeroOpt = repository.findById(id);

		if (superHeroOpt.isPresent()) {

			return Optional.of(mapper.fromSuperHeroToSuperHeroDto(superHeroOpt.get()));

		}

		return Optional.empty();
	}

	/**
	 * @see SuperHeroService#findAllSuperHerosByNameContainingIgnoreCase(String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SuperHeroDto> findAllSuperHeroesByNameContainingIgnoreCase(String name) {

		List<SuperHero> superHeroList = (List<SuperHero>) repository.findByNameContainingIgnoreCase(name);

		return superHeroList.stream().map(sh -> mapper.fromSuperHeroToSuperHeroDto(sh)).collect(Collectors.toList());

	}

	/**
	 * @see SuperHeroService#updateSuperHero(superHeroDto)
	 */
	@Override
	@Transactional
	public SuperHeroDto updateSuperHero(SuperHeroDto superHeroDto, Long id) {

		Optional<SuperHero> superHeroOpt = repository.findById(id);

		if (superHeroOpt.isPresent()) {

			SuperHero superHero = mapper.fromSuperHeroDtoToSuperHero(superHeroDto);
			SuperHero updatedSuperHero = repository.save(superHero);

			return mapper.fromSuperHeroToSuperHeroDto(updatedSuperHero);

		}

		return null;

	}

	/**
	 * @see SuperHeroService#saveSuperHero(SuperHero)
	 */
	@Override
	@Transactional
	public SuperHeroDto saveSuperHero(SuperHeroDto superHeroDto) {

		SuperHero superHeroDB = repository.findByName(superHeroDto.getName());

		if (Objects.isNull(superHeroDB)) {

			SuperHero superHero = mapper.fromSuperHeroDtoToSuperHero(superHeroDto);
			SuperHero superHeroInserted = repository.save(superHero);

			return mapper.fromSuperHeroToSuperHeroDto(superHeroInserted);

		}

		return null;
	}

	/**
	 * @see SuperHeroService#deleteSuperHero(Long)
	 */
	@Override
	@Transactional
	public Boolean deleteSuperHero(Long id) {

		Optional<SuperHero> superHeroOpt = repository.findById(id);

		if (superHeroOpt.isPresent()) {

			repository.deleteById(id);
			return true;

		}

		return false;
	}

}
