package com.Futkaradze.service;

import com.Futkaradze.entity.User;
import com.Futkaradze.exception.UserException;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id) throws UserException;

    List<User> getAllUsers();

    void deleteUser(Long id) throws UserException;

    User updateUser(Long id, User user) throws UserException;
}