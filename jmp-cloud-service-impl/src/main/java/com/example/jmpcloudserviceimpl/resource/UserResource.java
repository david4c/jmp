package com.example.jmpcloudserviceimpl.resource;

import com.example.jmpcloudserviceimpl.controller.UserClaudController;
import com.example.jmpdto.dto.UserResponseDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UserResource extends RepresentationModel<UserResource> {

    private final Long id;
    private final String name;
    private final String surname;
    private final String birthday;

    public UserResource(final UserResponseDto userResponseDto) {
        this.id = userResponseDto.getId();
        this.birthday = userResponseDto.getBirthday();
        this.name = userResponseDto.getName();
        this.surname = userResponseDto.getSurname();
        addLinks();
    }

    private void addLinks() {
        final Link selfLink = linkTo(methodOn(UserClaudController.class).getUser(id)).withSelfRel();
        add(selfLink);
        final Link collectionLink = linkTo(methodOn(UserClaudController.class).getAllUser()).withRel("collection");
        add(collectionLink);
    }
}
