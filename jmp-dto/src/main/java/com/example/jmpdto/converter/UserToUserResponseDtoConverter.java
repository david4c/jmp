package com.example.jmpdto.converter;

import com.example.jmpdto.dto.UserResponseDto;
import com.example.jmpdto.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(final User source) {
        return UserResponseDto.builder()
                              .id(source.getId())
                              .name(source.getName())
                              .surname(source.getSurname())
                              .birthday(source.getBirthday().toString())
                              .build();
    }
}
