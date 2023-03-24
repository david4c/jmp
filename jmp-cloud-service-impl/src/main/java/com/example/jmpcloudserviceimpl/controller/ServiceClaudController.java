package com.example.jmpcloudserviceimpl.controller;

import com.example.jmpcloudserviceimpl.resource.SubscriptionResource;
import com.example.jmpdto.dto.SubscriptionRequestDto;
import com.example.jmpdto.dto.SubscriptionResponseDto;
import com.example.jmpservicerest.controller.ServiceController;
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
@RequestMapping("/v2/subscription")
@RequiredArgsConstructor
@Api(value = "ServiceClaudController", description = "REST APIs for subscription resource")
public class ServiceClaudController {

    private final ServiceController serviceController;

    @ApiOperation(value = "Create a new subscription", response = SubscriptionResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "subscription created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(
            @RequestBody final SubscriptionRequestDto subscriptionRequestDto
    ) {
        final ResponseEntity<SubscriptionResponseDto> response = serviceController.createSubscription(
                subscriptionRequestDto);
        return getSubscriptionResourceResponseEntity(response);
    }

    @ApiOperation(value = "Edit user", response = SubscriptionResource.class)
    @PutMapping
    public ResponseEntity<SubscriptionResource> updateSubscription(
            @RequestBody final SubscriptionRequestDto subscriptionRequestDto
    ) {
        final ResponseEntity<SubscriptionResponseDto> response = serviceController.updateSubscription(
                subscriptionRequestDto);
        return getSubscriptionResourceResponseEntity(response);
    }

    @ApiOperation(value = "Delete a user", response = String.class)
    @DeleteMapping("{id:\\d+}")
    public HttpStatus deleteSubscription(@PathVariable("id") final Long id) {
        return serviceController.deleteSubscription(id);
    }

    @ApiOperation(value = "Get user by ID", response = SubscriptionResource.class)
    @GetMapping("{id:\\d+}")
    public ResponseEntity<SubscriptionResource> getSubscription(@PathVariable("id") final Long id) {
        final ResponseEntity<SubscriptionResponseDto> response = serviceController.getSubscription(id);
        return getSubscriptionResourceResponseEntity(response);
    }

    @ApiOperation(value = "Get list of users", response = List.class)
    @GetMapping
    public ResponseEntity<List<SubscriptionResource>> getAllSubscription() {
        final ResponseEntity<List<SubscriptionResponseDto>> response = serviceController.getAllSubscription();
        if (response.getStatusCode().isError() || response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        final List<SubscriptionResource> resources = response.getBody()
                                                             .stream()
                                                             .map(SubscriptionResource::new)
                                                             .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    private static ResponseEntity<SubscriptionResource> getSubscriptionResourceResponseEntity(final ResponseEntity<SubscriptionResponseDto> response) {
        if (response.getStatusCode().isError() || response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        final SubscriptionResponseDto subscriptionDto = response.getBody();
        final SubscriptionResource resource = new SubscriptionResource(subscriptionDto);
        return ResponseEntity.ok(resource);
    }

}
