package dev.agitrubard.couriertracking.model.mapper;

import dev.agitrubard.couriertracking.model.CourierStoreEntry;
import dev.agitrubard.couriertracking.model.entity.CourierStoreEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierStoreEntryEntityToDomainMapper extends BaseMapper<CourierStoreEntryEntity, CourierStoreEntry> {

    CourierStoreEntryEntityToDomainMapper INSTANCE = Mappers.getMapper(CourierStoreEntryEntityToDomainMapper.class);

}
