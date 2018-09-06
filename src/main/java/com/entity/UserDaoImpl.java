package com.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserDaoImpl {
	@Autowired
	UserRepo userrepo;

	public User findById(int id) {
		User u = userrepo.getOne(id);
		return u;
	}

	public void saveUser(User user) {
		userrepo.save(user);
	}

	public void updateUser(User user) {
		userrepo.save(user);

	}

	public void deleteUserById(int id) {
		userrepo.deleteById(id);

	}

	public List<User> findAllUsers() {
		List<User> lis = userrepo.findAll();
		return lis;

	}

}