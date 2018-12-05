package com.helloboot.controller;


import com.helloboot.dao.UserRepository;
import com.helloboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 前端控制器
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

    @RequestMapping("/test")
    public String test(HttpServletRequest request,
                       @RequestParam(value = "id") String id) {
        System.out.println(id);
        Map headers = getHeadersInfo(request);
        return id;
    }

    @PostMapping("/post")
    public String post(HttpServletRequest request,
                       @RequestBody String param) {
        Map headers = getHeadersInfo(request);
        System.out.println(param);
        return "success";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request,
                         @PathVariable("id") String id) {
        Map headers = getHeadersInfo(request);
        return id;
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestBody String str) {
        return "success";
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}

