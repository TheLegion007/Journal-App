package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal_v1")
class JournalEntryController {

    private Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(String.valueOf(myEntry.getId()), myEntry);
        return true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable String myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable String myId) {
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable String myId, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(String.valueOf(myId), myEntry);
    }

}
