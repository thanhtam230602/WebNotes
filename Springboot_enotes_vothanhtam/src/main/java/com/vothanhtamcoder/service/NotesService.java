package com.vothanhtamcoder.service;

import java.util.List;

import com.vothanhtamcoder.entity.Notes;
import com.vothanhtamcoder.entity.User;

public interface NotesService {

	
	public Notes saveNotes(Notes notes);
	
	public Notes getNotesById(int id);
	
	public List<Notes> getNotesByUser(User user);
	
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(int id);
	
}
