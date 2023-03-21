package com.example.jmpservicerest.controller;

import com.example.jmpdto.dto.SubscriptionRequestDto;
import com.example.jmpdto.dto.SubscriptionResponseDto;
import com.example.jmpserviceapi.service.SubscriptionService;
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
@RequestMapping("/v1/subscription")
@RequiredArgsConstructor
public class ServiceController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDto> createSubscription(
            @RequestBody final SubscriptionRequestDto subscriptionRequestDto
    ) {
        return ResponseEntity.ok(subscriptionService.save(subscriptionRequestDto));
    }

    @PutMapping
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(
            @RequestBody final SubscriptionRequestDto subscriptionRequestDto
    ) {
        return ResponseEntity.ok(subscriptionService.edit(subscriptionRequestDto));
    }

    @DeleteMapping("{id:\\d+}")
    public HttpStatus deleteSubscription(@PathVariable("id") final Long id) {
        subscriptionService.deleteById(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id:\\d+}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(subscriptionService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        return ResponseEntity.ok(
                subscriptionService.getAll()
        );
    }
}
