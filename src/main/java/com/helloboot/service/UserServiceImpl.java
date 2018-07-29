package com.helloboot.service;

import com.helloboot.domain.User;
import com.helloboot.dao.UserMapper;
import com.helloboot.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CoderLucas
 * @since 2018-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
