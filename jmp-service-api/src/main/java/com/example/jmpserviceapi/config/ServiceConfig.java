package com.example.jmpserviceapi.config;

import com.example.jmpdto.converter.SubscriptionRequestDtoToSubscription;
import com.example.jmpdto.converter.SubscriptionToSubscriptionResponseDto;
import com.example.jmpdto.converter.UserRequestDtoToUserConverter;
import com.example.jmpdto.converter.UserToUserResponseDtoConverter;
import com.example.jmpdto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.jmpdto.repository")
@RequiredArgsConstructor
public class ServiceConfig {

    private final UserRepository userRepository;

    @Bean
    public SubscriptionToSubscriptionResponseDto subscriptionToSubscriptionResponseDto() {
        return new SubscriptionToSubscriptionResponseDto();
    }

    @Bean
    public SubscriptionRequestDtoToSubscription subscriptionRequestDtoToSubscription() {
        return new SubscriptionRequestDtoToSubscription(userRepository);
    }

    @Bean
    public UserToUserResponseDtoConverter userToUserResponseDtoConverter() {
        return new UserToUserResponseDtoConverter();
    }

    @Bean
    public UserRequestDtoToUserConverter userRequestDtoToUserConverter() {
        return new UserRequestDtoToUserConverter();
    }
}
