package com.coolcook.service.mapper;

import com.coolcook.domain.*;
import com.coolcook.service.dto.RecipeMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RecipeMaster and its DTO RecipeMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, IngredientQtyMappingMapper.class, })
public interface RecipeMasterMapper extends EntityMapper <RecipeMasterDTO, RecipeMaster> {

    @Mapping(source = "category.id", target = "categoryId")
    RecipeMasterDTO toDto(RecipeMaster recipeMaster); 

    @Mapping(source = "categoryId", target = "category")
    RecipeMaster toEntity(RecipeMasterDTO recipeMasterDTO); 
    default RecipeMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecipeMaster recipeMaster = new RecipeMaster();
        recipeMaster.setId(id);
        return recipeMaster;
    }
}
