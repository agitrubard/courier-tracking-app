package dev.agitrubard.couriertracking.model.mapper;

import dev.agitrubard.couriertracking.model.Store;
import dev.agitrubard.couriertracking.model.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreEntityToDomainMapper extends BaseMapper<StoreEntity, Store> {

    StoreEntityToDomainMapper INSTANCE = Mappers.getMapper(StoreEntityToDomainMapper.class);

    @Override
    @Mapping(target = "location.latitude", source = "latitude")
    @Mapping(target = "location.longitude", source = "longitude")
    Store map(StoreEntity source);

}
