package com.example.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "journal_entries")
@Data //Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDate date;
    public void setDate(LocalDate date) {
        this.date = date;
    }


}
