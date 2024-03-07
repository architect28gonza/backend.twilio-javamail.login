package com.application.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.application.entity.UserEntity;
import com.application.lib.dto.UserDto;

public class AppMapper {

    public UserEntity userDtoToEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(userDto, UserEntity.class);
    }
}
