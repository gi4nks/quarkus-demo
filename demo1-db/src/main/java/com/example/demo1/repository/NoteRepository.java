package com.example.demo1.repository;

import com.example.demo1.entity.Note;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findByOwner(String owner);
}
