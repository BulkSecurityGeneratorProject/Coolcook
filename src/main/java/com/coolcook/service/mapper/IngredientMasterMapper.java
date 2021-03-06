package com.coolcook.service.mapper;

import com.coolcook.domain.*;
import com.coolcook.service.dto.IngredientMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity IngredientMaster and its DTO IngredientMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, })
public interface IngredientMasterMapper extends EntityMapper <IngredientMasterDTO, IngredientMaster> {

    @Mapping(source = "category.id", target = "categoryId")
    IngredientMasterDTO toDto(IngredientMaster ingredientMaster); 

    @Mapping(source = "categoryId", target = "category")
    IngredientMaster toEntity(IngredientMasterDTO ingredientMasterDTO); 
    default IngredientMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        IngredientMaster ingredientMaster = new IngredientMaster();
        ingredientMaster.setId(id);
        return ingredientMaster;
    }
}
