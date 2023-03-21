package com.example.jmpservicerest.controller;

import com.example.jmpdto.dto.UserRequestDto;
import com.example.jmpdto.dto.UserResponseDto;
import com.example.jmpserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody final UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.save(userRequestDto));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody final UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.edit(userRequestDto));
    }

    @DeleteMapping("{id:\\d+}")
    public HttpStatus deleteUser(@PathVariable("id") final Long id) {
        userService.deleteById(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id:\\d+}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAll());
    }

}
