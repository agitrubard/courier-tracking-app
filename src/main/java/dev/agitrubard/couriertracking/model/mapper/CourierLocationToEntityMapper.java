package dev.agitrubard.couriertracking.model.mapper;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.entity.CourierLocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierLocationToEntityMapper extends BaseMapper<CourierLocation, CourierLocationEntity> {

    CourierLocationToEntityMapper INSTANCE = Mappers.getMapper(CourierLocationToEntityMapper.class);

    @Override
    @Mapping(target = "latitude", source = "location.latitude")
    @Mapping(target = "longitude", source = "location.longitude")
    CourierLocationEntity map(CourierLocation source);

}
