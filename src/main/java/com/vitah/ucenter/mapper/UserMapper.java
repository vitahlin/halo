package com.vitah.ucenter.mapper;

import com.vitah.ucenter.dto.UserDTO;
import com.vitah.ucenter.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author vitah
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * User对象转DTO
     *
     * @param user
     * @return
     */
    UserDTO userToDTO(User user);
}
