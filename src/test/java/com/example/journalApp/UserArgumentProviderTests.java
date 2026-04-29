package com.example.journalApp;

import com.example.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProviderTests implements ArgumentsProvider{
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("pihu").password("").build()),
                Arguments.of(User.builder().username("payal").password("payal").build())
        );
    }
}
