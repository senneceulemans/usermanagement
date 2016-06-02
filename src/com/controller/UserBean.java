package com.controller;

import java.util.List;

import com.dao.UserDao;
import com.model.User;

public class UserBean {
	private UserDao userDao = new UserDao();
	
	public boolean checkConnection(){
		return userDao.checkConnection();
	}
		
	public boolean create(User u) {
	      return userDao.addUser(u);	      
	}
	
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	public boolean deleteUser(int userid){
		return userDao.deleteUser(userid);
	}
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public User getUserFromID(int userid){
		return userDao.getUserById(userid);
	}
}
