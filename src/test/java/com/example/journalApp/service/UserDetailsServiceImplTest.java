package com.example.journalApp.service;
import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl  userDetailsServiceImpl;

    @Mock
    private UserEntryRepository userEntryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userEntryRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Raja").password("1234553").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("Raja");
        Assertions.assertNotNull(user);
    }
}
