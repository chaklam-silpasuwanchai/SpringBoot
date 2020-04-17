package com.example.notes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notes.model.Notes;

public interface NoteRepo extends JpaRepository<Notes, Integer> {
	
}
