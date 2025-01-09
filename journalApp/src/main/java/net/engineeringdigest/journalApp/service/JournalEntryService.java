package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
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

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public void saveEntry(JournalEntry journalEntry) {
       try {
           journalEntry.setDate(LocalDateTime.now());
           journalEntryRepository.save(journalEntry);
       } catch (Exception e) {
           log.error("Exception" , e);
       }
    }

    public Optional<JournalEntry> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    public void deleteById(ObjectId myId) {
         journalEntryRepository.deleteById(myId);
    }

    public void updateById(JournalEntry journalEntry, ObjectId myId) {
        journalEntryRepository.save(journalEntry);
    }
}

