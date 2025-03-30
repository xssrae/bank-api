package com.bank_api.service;

import com.bank_api.domain.model.User;


public interface UserService {
    User findById(Long id);
    User create(User userToCreate);
}
