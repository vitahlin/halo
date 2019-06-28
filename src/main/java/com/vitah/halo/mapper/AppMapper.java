package com.vitah.halo.mapper;

import com.vitah.halo.dto.AppSummaryDTO;
import com.vitah.halo.entity.App;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author vitah
 */
@Mapper(componentModel = "spring")
public interface AppMapper {

    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    /**
     * App数据转DTO
     *
     * @param apps
     * @return
     */
    List<AppSummaryDTO> appsToDTOs(List<App> apps);
}
