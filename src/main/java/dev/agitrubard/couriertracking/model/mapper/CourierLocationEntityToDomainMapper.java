package dev.agitrubard.couriertracking.model.mapper;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.entity.CourierLocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierLocationEntityToDomainMapper extends BaseMapper<CourierLocationEntity, CourierLocation> {

    CourierLocationEntityToDomainMapper INSTANCE = Mappers.getMapper(CourierLocationEntityToDomainMapper.class);

    @Override
    @Mapping(target = "location.latitude", source = "latitude")
    @Mapping(target = "location.longitude", source = "longitude")
    CourierLocation map(CourierLocationEntity source);

}
