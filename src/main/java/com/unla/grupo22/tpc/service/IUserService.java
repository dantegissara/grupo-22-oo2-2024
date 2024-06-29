package com.unla.grupo22.tpc.service;

import java.util.Optional;

import com.unla.grupo22.tpc.entities.User;

public interface IUserService {
	Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    User save(User user);
    void delete(User user);
}
