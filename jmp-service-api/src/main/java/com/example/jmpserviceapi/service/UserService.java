package com.example.jmpserviceapi.service;

import com.example.jmpdto.dto.UserRequestDto;
import com.example.jmpdto.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto save(final UserRequestDto userRequestDto);

    UserResponseDto edit(final UserRequestDto userRequestDto);

    UserResponseDto getById(final Long id);

    List<UserResponseDto> getAll();

    void deleteById(final Long id);
}
