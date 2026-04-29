package com.example.journalApp.service;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;
import com.example.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try{
            User user =  userEntryService.findUserByUsername(username);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveUser(user);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occurred while saving JournalEntry",e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId, String username) {
        User  user =  userEntryService.findUserByUsername(username);
        boolean deleted=false;
        try {
            deleted = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if(deleted){
                userEntryService.saveUser(user);
                journalEntryRepository.deleteById(myId);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting JournalEntry",e);
        }
        return deleted;
    }


}
