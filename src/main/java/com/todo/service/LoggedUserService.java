package com.todo.service;

import com.todo.models.User;

public interface LoggedUserService {
    Boolean isUserLogged(Long id);
    User currentUser();
}