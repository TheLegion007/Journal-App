package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user =  userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved =  journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            log.error("Exception" , e);
        }
    }

    public Optional<JournalEntry> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    public void deleteById(ObjectId myId, String userName) {
        User user = userService.findByUserName(userName);   // checked if user journal list has one id equal to deleteID //
        user.getJournalEntries().removeIf(x->x.getId().equals(myId));  // remove it from list //
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }

    public void updateById(JournalEntry journalEntry, ObjectId myId , String userName) {
        journalEntryRepository.save(journalEntry);
    }
}
