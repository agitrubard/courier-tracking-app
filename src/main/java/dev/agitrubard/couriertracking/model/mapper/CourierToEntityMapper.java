package dev.agitrubard.couriertracking.model.mapper;

import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.entity.CourierEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierToEntityMapper extends BaseMapper<Courier, CourierEntity> {

    CourierToEntityMapper INSTANCE = Mappers.getMapper(CourierToEntityMapper.class);

}
