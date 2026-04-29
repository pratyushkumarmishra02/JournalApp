package com.example.journalApp.service;

import com.example.journalApp.UserArgumentProviderTests;
import com.example.journalApp.entity.User;
import com.example.journalApp.repository.JournalEntryRepository;
import com.example.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntryServiceTests {

    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Disabled
    public void testAdd(){
        assertEquals(4,2+2);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Raja",
            "Ram",
            "Pihu",
            "Pratyusa"
    })
    public void testFindByUsername(String username){
        /*User user = userEntryRepository.findByUsername(username);
        assertEquals("Raja",user.getUsername());
        assertFalse(!user.getJournalEntries().isEmpty());*/
        assertNotNull(userEntryRepository.findByUsername(username),"Failed to find user "+username);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,3,4",
            "2,4,6"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }


    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProviderTests.class)
    public void testSaveNewUserEntry(User user){
        assertTrue(userEntryService.saveNewUser(user),"Failed to save new user entry");
    }

}
