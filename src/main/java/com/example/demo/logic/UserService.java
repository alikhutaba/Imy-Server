package com.example.demo.logic;

import java.util.List;

import com.example.demo.rest.boundaries.UserBoundary;

public interface UserService {

	public UserBoundary userLogIn(String userEmail, String password);

	public UserBoundary userRegistration(UserBoundary newUser);

	public UserBoundary addUser(UserBoundary newUser, String userId);

	public void updateUser(UserBoundary updateUser, String userId);

	public void deleteUser(UserBoundary deleteUser, String userId);

	public UserBoundary getUserById(String userId, String searchUserId);

	public List<UserBoundary> getAllUsers(String userId, int page, int size);

}
