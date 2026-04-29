package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //private static final Logger logger = LoggerFactory.getLogger(UserEntryService.class);

    public boolean saveNewUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
            return true;
        }catch(Exception e){
            log.info("Enjoy");
            log.warn("Enjoy");
            log.debug("Enjoy");
            log.error("Error occurred for {}:",user.getUsername(),e);
            return false;
        }

    }
    public void saveUser(User user) {
        userEntryRepository.save(user);
    }

    public List<User> getAll() {
        return userEntryRepository.findAll();
    }
    public Optional<User> getUserEntryById(@PathVariable ObjectId myId) {
        return userEntryRepository.findById(myId);
    }
    public void deleteUserEntryById(@PathVariable ObjectId myId) {
         userEntryRepository.deleteById(myId);
    }

    public User findUserByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }


    public void saveAdmin(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepository.save(newUser);
    }
}
