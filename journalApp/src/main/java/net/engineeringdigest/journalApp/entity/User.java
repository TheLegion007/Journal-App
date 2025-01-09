package net.engineeringdigest.journalApp.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")   // collection is equal to table in mongoDB //
@Getter
@Setter    //or @Data
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)  // username needs to be unique, add same in application properties as well //
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
