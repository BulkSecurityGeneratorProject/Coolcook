package com.coolcook.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.coolcook.app.domain.IngredientQtyMapping;

import com.coolcook.app.repository.IngredientQtyMappingRepository;
import com.coolcook.app.web.rest.util.HeaderUtil;
import com.coolcook.app.service.dto.IngredientQtyMappingDTO;
import com.coolcook.app.service.mapper.IngredientQtyMappingMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing IngredientQtyMapping.
 */
@RestController
@RequestMapping("/api")
public class IngredientQtyMappingResource {

    private final Logger log = LoggerFactory.getLogger(IngredientQtyMappingResource.class);

    private static final String ENTITY_NAME = "ingredientQtyMapping";

    private final IngredientQtyMappingRepository ingredientQtyMappingRepository;

    private final IngredientQtyMappingMapper ingredientQtyMappingMapper;

    public IngredientQtyMappingResource(IngredientQtyMappingRepository ingredientQtyMappingRepository, IngredientQtyMappingMapper ingredientQtyMappingMapper) {
        this.ingredientQtyMappingRepository = ingredientQtyMappingRepository;
        this.ingredientQtyMappingMapper = ingredientQtyMappingMapper;
    }

    /**
     * POST  /ingredient-qty-mappings : Create a new ingredientQtyMapping.
     *
     * @param ingredientQtyMappingDTO the ingredientQtyMappingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ingredientQtyMappingDTO, or with status 400 (Bad Request) if the ingredientQtyMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ingredient-qty-mappings")
    @Timed
    public ResponseEntity<IngredientQtyMappingDTO> createIngredientQtyMapping(@RequestBody IngredientQtyMappingDTO ingredientQtyMappingDTO) throws URISyntaxException {
        log.debug("REST request to save IngredientQtyMapping : {}", ingredientQtyMappingDTO);
        if (ingredientQtyMappingDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ingredientQtyMapping cannot already have an ID")).body(null);
        }
        IngredientQtyMapping ingredientQtyMapping = ingredientQtyMappingMapper.toEntity(ingredientQtyMappingDTO);
        ingredientQtyMapping = ingredientQtyMappingRepository.save(ingredientQtyMapping);
        IngredientQtyMappingDTO result = ingredientQtyMappingMapper.toDto(ingredientQtyMapping);
        return ResponseEntity.created(new URI("/api/ingredient-qty-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ingredient-qty-mappings : Updates an existing ingredientQtyMapping.
     *
     * @param ingredientQtyMappingDTO the ingredientQtyMappingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ingredientQtyMappingDTO,
     * or with status 400 (Bad Request) if the ingredientQtyMappingDTO is not valid,
     * or with status 500 (Internal Server Error) if the ingredientQtyMappingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ingredient-qty-mappings")
    @Timed
    public ResponseEntity<IngredientQtyMappingDTO> updateIngredientQtyMapping(@RequestBody IngredientQtyMappingDTO ingredientQtyMappingDTO) throws URISyntaxException {
        log.debug("REST request to update IngredientQtyMapping : {}", ingredientQtyMappingDTO);
        if (ingredientQtyMappingDTO.getId() == null) {
            return createIngredientQtyMapping(ingredientQtyMappingDTO);
        }
        IngredientQtyMapping ingredientQtyMapping = ingredientQtyMappingMapper.toEntity(ingredientQtyMappingDTO);
        ingredientQtyMapping = ingredientQtyMappingRepository.save(ingredientQtyMapping);
        IngredientQtyMappingDTO result = ingredientQtyMappingMapper.toDto(ingredientQtyMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ingredientQtyMappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ingredient-qty-mappings : get all the ingredientQtyMappings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ingredientQtyMappings in body
     */
    @GetMapping("/ingredient-qty-mappings")
    @Timed
    public List<IngredientQtyMappingDTO> getAllIngredientQtyMappings() {
        log.debug("REST request to get all IngredientQtyMappings");
        List<IngredientQtyMapping> ingredientQtyMappings = ingredientQtyMappingRepository.findAll();
        return ingredientQtyMappingMapper.toDto(ingredientQtyMappings);
    }

    /**
     * GET  /ingredient-qty-mappings/:id : get the "id" ingredientQtyMapping.
     *
     * @param id the id of the ingredientQtyMappingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ingredientQtyMappingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ingredient-qty-mappings/{id}")
    @Timed
    public ResponseEntity<IngredientQtyMappingDTO> getIngredientQtyMapping(@PathVariable Long id) {
        log.debug("REST request to get IngredientQtyMapping : {}", id);
        IngredientQtyMapping ingredientQtyMapping = ingredientQtyMappingRepository.findOne(id);
        IngredientQtyMappingDTO ingredientQtyMappingDTO = ingredientQtyMappingMapper.toDto(ingredientQtyMapping);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ingredientQtyMappingDTO));
    }

    /**
     * DELETE  /ingredient-qty-mappings/:id : delete the "id" ingredientQtyMapping.
     *
     * @param id the id of the ingredientQtyMappingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ingredient-qty-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteIngredientQtyMapping(@PathVariable Long id) {
        log.debug("REST request to delete IngredientQtyMapping : {}", id);
        ingredientQtyMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
