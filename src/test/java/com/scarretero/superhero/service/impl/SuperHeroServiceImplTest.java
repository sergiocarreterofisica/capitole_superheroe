package com.scarretero.superhero.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.scarretero.superhero.entity.SuperHero;
import com.scarretero.superhero.mapper.SuperHeroMapper;
import com.scarretero.superhero.model.SuperHeroDto;
import com.scarretero.superhero.repository.SuperHeroRepository;

/**
 * 
 * @author SERGIO
 * 
 *         The unit test class for SuperHeroServiceImpl
 *
 */
@ExtendWith(MockitoExtension.class)
class SuperHeroServiceImplTest {

	/**
	 * The class under test
	 */
	@InjectMocks
	private SuperHeroServiceImpl service;

	/**
	 * The superhero repository
	 */
	@Mock
	private SuperHeroRepository repository;

	/**
	 * The superhero mapper
	 */
	@Mock
	private SuperHeroMapper mapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindAllSuperHeroes() {

		when(repository.findAll()).thenReturn(getAllSuperHeroes());

		List<SuperHeroDto> listSuperHero = service.findAllSuperHeroes();

		assertNotNull(listSuperHero);
		assertEquals(6, listSuperHero.size());

	}

	@Test
	void testFindAllSuperHeroesNoHeroes() {

		when(repository.findAll()).thenReturn(new ArrayList<SuperHero>());

		List<SuperHeroDto> listSuperHero = service.findAllSuperHeroes();

		assertNotNull(listSuperHero);
		assertEquals(0, listSuperHero.size());

	}

	@Test
	void testFindById() {

		when(repository.findById(Long.valueOf(anyLong()))).thenReturn(getSuperHeroOpt());
		when(mapper.fromSuperHeroToSuperHeroDto(any())).thenReturn(getSuperHeroDto());

		Optional<SuperHeroDto> superHeroDto = service.findById(Long.valueOf(3));

		assertTrue(superHeroDto.isPresent());

	}

	@Test
	void testFindByIdNotExist() {

		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		Optional<SuperHeroDto> superHeroDto = service.findById(anyLong());

		assertTrue(superHeroDto.isEmpty());

	}

	@Test
	void testFindByName() {

		when(repository.findByName(anyString())).thenReturn(getSuperHero());
		when(mapper.fromSuperHeroToSuperHeroDto(any())).thenReturn(getSuperHeroDto());

		SuperHeroDto superHeroDto = service.findByName(anyString());

		assertNotNull(superHeroDto);

	}

	@Test
	void testFindByNameNotExist() {

		when(repository.findByName(anyString())).thenReturn(null);

		SuperHeroDto superHeroDto = service.findByName(anyString());

		assertNull(superHeroDto);

	}

	@Test
	void testFindAllSuperHeroesByNameContainingIgnoreCase() {

		List<SuperHero> listSuperHero = getAllSuperHeroes();
		listSuperHero.remove(0);
		listSuperHero.remove(0);

		when(repository.findByNameContainingIgnoreCase(anyString())).thenReturn(listSuperHero);

		List<SuperHeroDto> listSuperHeroDto = service.findAllSuperHeroesByNameContainingIgnoreCase(anyString());

		assertNotNull(listSuperHeroDto);
		assertEquals(4, listSuperHeroDto.size());
	}

	@Test
	void testFindAllSuperHeroesByNameContainingIgnoreCaseNoHeroes() {

		when(repository.findByNameContainingIgnoreCase(anyString())).thenReturn(new ArrayList<SuperHero>());

		List<SuperHeroDto> listSuperHeroDto = service.findAllSuperHeroesByNameContainingIgnoreCase(anyString());

		assertNotNull(listSuperHeroDto);
		assertEquals(0, listSuperHeroDto.size());
	}

	@Test
	void testUpdateSuperHero() {

		when(repository.findById(anyLong())).thenReturn(getSuperHeroOpt());
		when(mapper.fromSuperHeroDtoToSuperHero(any())).thenReturn(getSuperHeroOpt().get());
		when(repository.save(any())).thenReturn(getSuperHeroOpt().get());
		when(mapper.fromSuperHeroToSuperHeroDto(any())).thenReturn(getSuperHeroDto());

		SuperHeroDto superHeroDto = service.updateSuperHero(any(), Long.valueOf(3));

		assertNotNull(superHeroDto);
		assertEquals(getSuperHeroDto().getCity(), superHeroDto.getCity());
		assertEquals(getSuperHeroDto().getName(), superHeroDto.getName());
		assertEquals(1, superHeroDto.getId().longValue());

	}

	@Test
	void testUpdateSuperHeroNotExist() {

		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		SuperHeroDto superHeroDto = service.updateSuperHero(any(), Long.valueOf("5"));

		assertNull(superHeroDto);

	}

	@Test
	void testSaveSuperHero() {

		when(repository.findByName(anyString())).thenReturn(null);
		when(mapper.fromSuperHeroDtoToSuperHero(any())).thenReturn(getSuperHeroOpt().get());
		when(repository.save(any())).thenReturn(getSuperHeroOpt().get());
		when(mapper.fromSuperHeroToSuperHeroDto(any())).thenReturn(getSuperHeroDto());

		SuperHeroDto superHeroDto = service.saveSuperHero(getSuperHeroDto());

		assertNotNull(superHeroDto);
		assertEquals(getSuperHeroDto().getCity(), superHeroDto.getCity());
		assertEquals(getSuperHeroDto().getName(), superHeroDto.getName());
		assertEquals(1, superHeroDto.getId().longValue());

	}

	@Test
	void testSaveSuperHeroExists() {

		when(repository.findByName(anyString())).thenReturn(getSuperHeroOpt().get());

		SuperHeroDto superHeroDto = service.saveSuperHero(getSuperHeroDto());

		assertNull(superHeroDto);

	}

	@Test
	void testDeleteSuperHero() {

		when(repository.findById(anyLong())).thenReturn(getSuperHeroOpt());

		Boolean deleted = service.deleteSuperHero(anyLong());

		assertTrue(deleted.booleanValue());

	}

	@Test
	void testDeleteSuperHeroNotExist() {

		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		Boolean deleted = service.deleteSuperHero(Long.valueOf(100));

		assertFalse(deleted.booleanValue());

	}

	private List<SuperHero> getAllSuperHeroes() {

		List<SuperHero> listSuperHero = new ArrayList<SuperHero>();

		SuperHero superHero = new SuperHero(Long.valueOf(1), "Batman", "Gotham");
		listSuperHero.add(superHero);
		superHero = new SuperHero(Long.valueOf(2), "Superman", "Metropolis");
		listSuperHero.add(superHero);
		superHero = new SuperHero(Long.valueOf(3), "Judge Dredd", "Mega City One");
		listSuperHero.add(superHero);
		superHero = new SuperHero(Long.valueOf(4), "Aquaman", "Amnesty Bay");
		listSuperHero.add(superHero);
		superHero = new SuperHero(Long.valueOf(5), "Wonder Woman", "Themyscira");
		listSuperHero.add(superHero);
		superHero = new SuperHero(Long.valueOf(6), "Spiderman", "New York");
		listSuperHero.add(superHero);

		return listSuperHero;

	}

	private Optional<SuperHero> getSuperHeroOpt() {

		SuperHero superHero = new SuperHero(Long.valueOf(1), "Wonder Woman", "Themyscira");
		return Optional.of(superHero);

	}

	private SuperHero getSuperHero() {

		return new SuperHero(Long.valueOf(1), "Wonder Woman", "Themyscira");

	}

	private SuperHeroDto getSuperHeroDto() {

		return new SuperHeroDto(Long.valueOf(1), "Wonder Woman", "Themyscira");

	}
}
