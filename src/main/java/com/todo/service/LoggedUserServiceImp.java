package com.todo.service;

import com.todo.models.User;
import com.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoggedUserServiceImp implements LoggedUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean isUserLogged(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return (userRepository.findByUsername(username).get().getId() == id);
        }
        return false;
    }


    @Override
    public User currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return userRepository.findByUsername(username).get();
        }
        return null;
    }
}

