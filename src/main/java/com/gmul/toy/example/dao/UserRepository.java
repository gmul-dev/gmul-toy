package com.gmul.toy.example.dao;

import com.gmul.toy.example.domain.User;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
