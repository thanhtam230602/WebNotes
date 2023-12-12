package com.vothanhtamcoder.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vothanhtamcoder.entity.Notes;
import com.vothanhtamcoder.entity.User;
import com.vothanhtamcoder.repository.UserRepository;
import com.vothanhtamcoder.service.NotesService;



import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NotesService notesSevice;
	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}

	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes(Model m,Principal p) {
		User user = getUser(p, m);
		List<Notes> notes = notesSevice.getNotesByUser(user);
		m.addAttribute("notesList",notes);
		return "view_notes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id,Model m) {
		Notes notes = notesSevice.getNotesById(id);
		m.addAttribute("n",notes);
		return "edit_notes";
	}
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m) 
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		
		Notes saveNotes = notesSevice.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Notes Save success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/addNotes";
	}
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m) 
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		
		Notes saveNotes = notesSevice.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Notes update success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}
	@GetMapping("/deleteNotes/{id}")
	public String eleteNotes(@PathVariable int id,HttpSession session) {
		boolean f= notesSevice.deleteNotes(id);
		if (f) {
			session.setAttribute("msg", "delete success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}
	
	
}
