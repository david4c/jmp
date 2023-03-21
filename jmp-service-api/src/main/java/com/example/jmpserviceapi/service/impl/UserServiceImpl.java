package com.example.jmpserviceapi.service.impl;

import com.example.jmpdto.converter.UserRequestDtoToUserConverter;
import com.example.jmpdto.converter.UserToUserResponseDtoConverter;
import com.example.jmpdto.dto.UserRequestDto;
import com.example.jmpdto.dto.UserResponseDto;
import com.example.jmpdto.entity.User;
import com.example.jmpdto.repository.UserRepository;
import com.example.jmpserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRequestDtoToUserConverter userRequestDtoToUserConverter;
    private final UserToUserResponseDtoConverter userToUserResponseDtoConverter;

    @Override
    public UserResponseDto save(final UserRequestDto userRequestDto) {
        final User user = userRepository.save(
                Objects.requireNonNull(userRequestDtoToUserConverter
                        .convert(userRequestDto))
        );
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    public UserResponseDto edit(final UserRequestDto userRequestDto) {
        final User user = userRequestDtoToUserConverter.convert(userRequestDto);
        final User userEntity = userRepository.findById(user.getId())
                                              .orElseThrow(
                                                      () -> new RuntimeException("can't find user with id = " +
                                                                                 user.getId()));
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setBirthday(user.getBirthday());
        return userToUserResponseDtoConverter.convert(userEntity);
    }

    @Override
    public UserResponseDto getById(final Long id) {
        return userToUserResponseDtoConverter.convert(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("can't find user with id = " + id)));
    }

    @Override
    public List<UserResponseDto> getAll() {
        final List<User> users = userRepository.findAll();
        return users.stream()
                    .map(userToUserResponseDtoConverter::convert)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }
}
