package com.example.jmpcloudserviceimpl.resource;

import com.example.jmpcloudserviceimpl.controller.ServiceClaudController;
import com.example.jmpdto.dto.SubscriptionResponseDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@EqualsAndHashCode(callSuper = true)
public class SubscriptionResource extends RepresentationModel<SubscriptionResource> {

    private final Long id;
    private final Long userId;
    private final String startDate;

    public SubscriptionResource(final SubscriptionResponseDto subscriptionResponseDto) {
        this.id = subscriptionResponseDto.getId();
        this.userId = subscriptionResponseDto.getUserId();
        this.startDate = subscriptionResponseDto.getStartDate();
        addLinks();
    }

    private void addLinks() {
        final Link selfLink = linkTo(methodOn(ServiceClaudController.class).getSubscription(id)).withSelfRel();
        add(selfLink);
        final Link collectionLink = linkTo(methodOn(ServiceClaudController.class).getAllSubscription()).withRel("collection");
        add(collectionLink);
    }
}
