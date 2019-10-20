package com.helloboot.controller;


import com.helloboot.model.UserDao;
import com.helloboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private UserDao userDao;

    @GetMapping("/id")
    public Optional<User> test(@RequestParam(value = "id")Long id) {
        return userDao.findById(id);
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

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user = new User(1L,"123","123","123",1);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/list")
    public Page list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(1, 10, sort);
        return userDao.findAll(pageable);
    }
}

