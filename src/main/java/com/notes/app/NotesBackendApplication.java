package com.notes.app;

import com.notes.app.model.Note;
import com.notes.app.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class NotesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesBackendApplication.class, args);
    }

    /**
     * Simple startup seeder that inserts three sample notes with explicit createdAt dates.
     * These seed entries make it easy to demo the share links during your interview.
     */
    @Bean
    CommandLineRunner seed(NoteRepository repo) {
        return args -> {
            // only seed if repository empty
            if (repo.count() == 0) {
                Note a = new Note();
                a.setTitle("Interview Demo — Welcome");
                a.setContent("This is a seeded note created for your interview demo.");
                // seed createdAt to Jan 1, 2024 10:00
                a.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));
                repo.save(a);

                Note b = new Note();
                b.setTitle("Ideas");
                b.setContent("Remember to show Figma design and the live API docs.");
                // seed createdAt to May 5, 2024 14:30
                b.setCreatedAt(LocalDateTime.of(2024, 5, 5, 14, 30));
                repo.save(b);

                Note c = new Note();
                c.setTitle("Shopping List");
                c.setContent("- Bread\\n- Milk\\n- Coffee");
                // seed createdAt to Aug 15, 2024 09:15
                c.setCreatedAt(LocalDateTime.of(2024, 8, 15, 9, 15));
                repo.save(c);
            }
        };
    }
}
