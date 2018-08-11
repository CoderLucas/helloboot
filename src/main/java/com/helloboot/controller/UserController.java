package com.helloboot.controller;


import com.helloboot.dao.UserRepository;
import com.helloboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLucas
 * @since 2018-07-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/id")
    public Optional<User> test(@RequestParam(value = "id")Long id) {
        return userRepository.findById(id);
    }
}

