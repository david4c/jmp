package com.example.jmpserviceapi.service;

import com.example.jmpdto.dto.SubscriptionRequestDto;
import com.example.jmpdto.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDto save(final SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto edit(final SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto getById(final Long id);

    List<SubscriptionResponseDto> getAll();

    void deleteById(final Long id);
}
