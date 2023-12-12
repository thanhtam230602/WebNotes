package com.vothanhtamcoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vothanhtamcoder.entity.Notes;
import com.vothanhtamcoder.entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

	public List<Notes> findByUser(User user);
}
