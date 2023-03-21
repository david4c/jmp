package com.example.jmpdto.converter;

import com.example.jmpdto.dto.SubscriptionRequestDto;
import com.example.jmpdto.entity.Subscription;
import com.example.jmpdto.entity.User;
import com.example.jmpdto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SubscriptionRequestDtoToSubscription implements Converter<SubscriptionRequestDto, Subscription> {

    private final UserRepository userRepository;

    @Override
    public Subscription convert(final SubscriptionRequestDto source) {
        final User user = userRepository.findById(source.getUserId()).orElseThrow(
                () -> new RuntimeException("can't find user with id = " + source.getUserId())
        );
        return Subscription.builder()
                           .id(source.getId())
                           .user(user)
                           .startDate(LocalDate.now())
                           .build();
    }
}
