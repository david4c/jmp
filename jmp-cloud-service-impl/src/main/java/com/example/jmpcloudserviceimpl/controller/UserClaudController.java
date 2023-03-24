package com.example.jmpcloudserviceimpl.controller;

import com.example.jmpcloudserviceimpl.resource.UserResource;
import com.example.jmpdto.dto.UserRequestDto;
import com.example.jmpdto.dto.UserResponseDto;
import com.example.jmpservicerest.controller.UserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2/user")
@RequiredArgsConstructor
@Api(value = "UserClaudController", description = "REST APIs for User resource")
public class UserClaudController {

    private final UserController userController;

    @ApiOperation(value = "Create a new user", response = UserResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody final UserRequestDto userRequestDto) {
        final ResponseEntity<UserResponseDto> userResponse = userController.createUser(userRequestDto);
        return getUserResourceResponseEntity(userResponse);
    }

    @ApiOperation(value = "Edit user", response = UserResource.class)
    @PutMapping
    public ResponseEntity<UserResource> updateUser(@RequestBody final UserRequestDto userRequestDto) {
        final ResponseEntity<UserResponseDto> userResponse = userController.updateUser(userRequestDto);
        return getUserResourceResponseEntity(userResponse);
    }

    @ApiOperation(value = "Delete a user", response = String.class)
    @DeleteMapping("{id:\\d+}")
    public HttpStatus deleteUser(@PathVariable("id") final Long id) {
        return userController.deleteUser(id);
    }

    @ApiOperation(value = "Get user by ID", response = UserResource.class)
    @GetMapping("{id:\\d+}")
    public ResponseEntity<UserResource> getUser(@PathVariable("id") final Long id) {
        final ResponseEntity<UserResponseDto> response = userController.getUser(id);
        return getUserResourceResponseEntity(response);
    }

    @ApiOperation(value = "Get list of users", response = List.class)
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUser() {
        final ResponseEntity<List<UserResponseDto>> response = userController.getAllUser();
        if (response.getStatusCode().isError() || response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        final List<UserResource> userResources = response.getBody()
                                                         .stream()
                                                         .map(UserResource::new).collect(Collectors.toList());
        return ResponseEntity.ok(userResources);
    }

    private ResponseEntity<UserResource> getUserResourceResponseEntity(final ResponseEntity<UserResponseDto> response) {
        if (response.getStatusCode().isError() || response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        final UserResponseDto userDto = response.getBody();
        final UserResource resource = new UserResource(userDto);
        return ResponseEntity.ok(resource);
    }
}
