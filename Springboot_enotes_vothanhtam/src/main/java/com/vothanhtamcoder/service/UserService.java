package com.vothanhtamcoder.service;

import com.vothanhtamcoder.entity.User;

public interface UserService {

	public User saveUser(User user);

	public boolean existEmailCheck(String email);
	
	//added

}
