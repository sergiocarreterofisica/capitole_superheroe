package com.scarretero.superhero.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scarretero.superhero.annotation.ExecutionIntervalAnnotation;
import com.scarretero.superhero.model.SuperHeroDto;
import com.scarretero.superhero.service.SuperHeroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author SERGIO
 *
 *         Controller of the superhero endpoint
 */
@RestController
@RequestMapping("/api")
public class SuperHeroController {

	/**
	 * The superhero service
	 */
	private SuperHeroService service;

	/**
	 * Constructor
	 * 
	 * @param service - the superhero service
	 */
	public SuperHeroController(SuperHeroService service) {
		this.service = service;
	}

	/**
	 * Get a superhero by identifier
	 * 
	 * @param id - Superhero identifier
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(path = "/superheroes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ExecutionIntervalAnnotation
	@ApiOperation(value = "Get a superhero by identifier")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Superhero founded"),
			@ApiResponse(code = 409, message = "Superhero not founded") })
	public ResponseEntity<?> getSuperHero(
			@ApiParam(value = "Superhero identifier") @PathVariable(value = "id", required = true) Long id) {

		Optional<SuperHeroDto> optionalSuperHeroDto = service.findById(id);

		if (optionalSuperHeroDto.isPresent()) {

			return new ResponseEntity<>(optionalSuperHeroDto.get(), HttpStatus.OK);

		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Get all the superheroes. You can filter by their names
	 * 
	 * @return {@link List}
	 */
	@GetMapping(path = "/superheroes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ExecutionIntervalAnnotation
	@ApiOperation(value = "Get all the superheroes. You can filter by their names.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Superheroes founded"),
			@ApiResponse(code = 404, message = "Superheroes not founded") })
	@ResponseStatus(HttpStatus.OK)
	public List<SuperHeroDto> getSuperHeroes(
			@ApiParam(value = "Superhero name") @RequestParam(value = "name", required = false) String name) {

		if (StringUtils.isNotBlank(name)) {

			return service.findAllSuperHeroesByNameContainingIgnoreCase(name);

		} else {

			return service.findAllSuperHeroes();
		}

	}

	/**
	 * Save a superhero
	 * 
	 * @param superHeroDto - The superhero
	 * @return {@link ResponseEntity}
	 */
	@PostMapping(path = "/superheroes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ExecutionIntervalAnnotation
	@ApiOperation(value = "Save a superhero.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Superhero saved"),
			@ApiResponse(code = 409, message = "Superhero not saved by conflict") })
	public ResponseEntity<?> insertSuperHero(
			@ApiParam(value = "Superhero") @RequestBody(required = true) SuperHeroDto superHeroDto) {

		SuperHeroDto superHeroDtoInserted = service.saveSuperHero(superHeroDto);

		if (!Objects.isNull(superHeroDtoInserted)) {

			return new ResponseEntity<>(superHeroDtoInserted, HttpStatus.CREATED);
		}

		return new ResponseEntity<Void>(HttpStatus.CONFLICT);

	}

	/**
	 * Update a superhero
	 * 
	 * @param superHeroDto - the superhero
	 * @return {@link ResponseEntity}
	 */
	@PutMapping(path = "/superheroes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ExecutionIntervalAnnotation
	@ApiOperation(value = "Update a superhero.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Superhero updated"),
			@ApiResponse(code = 404, message = "Superhero not saved because doesn't exist") })
	public ResponseEntity<?> updateSuperHero(
			@ApiParam(value = "Superhero") @RequestBody(required = true) SuperHeroDto superHeroDto,
			@ApiParam(value = "Superhero identifier") @PathVariable(value = "id", required = true) Long id) {

		SuperHeroDto superHeroDtoUpdated = service.updateSuperHero(superHeroDto, id);

		if (!Objects.isNull(superHeroDtoUpdated)) {

			return new ResponseEntity<>(superHeroDtoUpdated, HttpStatus.OK);

		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Delete a superhero
	 * 
	 * @param id - the superhero identifier
	 * @return {@link ResponseEntity}
	 */
	@DeleteMapping(path = "superheroes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ExecutionIntervalAnnotation
	@ApiOperation(value = "Delete a superhero.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Superhero deleted"),
			@ApiResponse(code = 404, message = "Superhero not deleted because doesn't exist") })
	@ApiParam()
	public ResponseEntity<?> deleteSuperHero(
			@ApiParam(value = "Superhero identifier", type = "int64") @PathVariable(value = "id", required = true) Long id) {

		Boolean superHeroDeleted = service.deleteSuperHero(id);

		if (superHeroDeleted.booleanValue()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
