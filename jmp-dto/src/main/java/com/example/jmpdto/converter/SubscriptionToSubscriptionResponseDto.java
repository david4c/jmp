package com.example.jmpdto.converter;

import com.example.jmpdto.dto.SubscriptionResponseDto;
import com.example.jmpdto.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionToSubscriptionResponseDto implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(final Subscription source) {
        return SubscriptionResponseDto.builder()
                                      .id(source.getId())
                                      .userId(source.getUser().getId())
                                      .startDate(source.getStartDate().toString())
                                      .build();
    }
}
