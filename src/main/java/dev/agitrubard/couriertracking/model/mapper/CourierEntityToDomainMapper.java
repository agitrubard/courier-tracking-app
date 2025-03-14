package dev.agitrubard.couriertracking.model.mapper;

import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.entity.CourierEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierEntityToDomainMapper extends BaseMapper<CourierEntity, Courier> {

    CourierEntityToDomainMapper INSTANCE = Mappers.getMapper(CourierEntityToDomainMapper.class);

}
