package com.example.jmpserviceapi.service.impl;

import com.example.jmpdto.converter.SubscriptionRequestDtoToSubscription;
import com.example.jmpdto.converter.SubscriptionToSubscriptionResponseDto;
import com.example.jmpdto.dto.SubscriptionRequestDto;
import com.example.jmpdto.dto.SubscriptionResponseDto;
import com.example.jmpdto.entity.Subscription;
import com.example.jmpdto.repository.SubscriptionRepository;
import com.example.jmpserviceapi.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionToSubscriptionResponseDto subscriptionToSubscriptionResponseDtoConverter;
    private final SubscriptionRequestDtoToSubscription subscriptionRequestDtoToSubscription;

    @Override
    public SubscriptionResponseDto save(final SubscriptionRequestDto subscriptionRequestDto) {
        final Subscription subscription = subscriptionRepository.save(
                Objects.requireNonNull(subscriptionRequestDtoToSubscription
                        .convert(subscriptionRequestDto)));
        return subscriptionToSubscriptionResponseDtoConverter
                .convert(subscription);
    }

    @Override
    public SubscriptionResponseDto edit(final SubscriptionRequestDto subscriptionRequestDto) {
        final Subscription subscription = subscriptionRequestDtoToSubscription.convert(subscriptionRequestDto);
        final Subscription subscriptionEntity = subscriptionRepository
                .findById(subscription.getId())
                .orElseThrow(() -> new RuntimeException("can't find subscription with id = " + subscription.getId()));

        //todo check if user exist!
        subscriptionEntity.setUser(subscription.getUser());
        subscriptionEntity.setStartDate(subscription.getStartDate());

        return subscriptionToSubscriptionResponseDtoConverter.convert(subscriptionEntity);
    }

    @Override
    public SubscriptionResponseDto getById(final Long id) {

        return subscriptionToSubscriptionResponseDtoConverter.convert(subscriptionRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("can't find subscription with id = " + id)));
    }

    @Override
    public List<SubscriptionResponseDto> getAll() {
        final List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions
                .stream()
                .map(subscriptionToSubscriptionResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(final Long id) {
        subscriptionRepository.deleteById(id);
    }
}
