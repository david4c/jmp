package com.example.jmpdto.converter;

import org.springframework.core.convert.converter.Converter;

import com.example.jmpdto.dto.UserRequestDto;
import com.example.jmpdto.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Override
    public User convert(final UserRequestDto source) {
        final LocalDate birthday = LocalDate.parse(source.getBirthday(), FORMATTER);

        return User.builder()
                   .id(source.getId())
                   .name(source.getName())
                   .surname(source.getSurname())
                   .birthday(birthday)
                   .build();
    }
}
