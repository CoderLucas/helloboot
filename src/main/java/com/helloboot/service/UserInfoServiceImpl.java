package com.helloboot.service;

import com.helloboot.model.UserInfo;
import com.helloboot.model.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lujianhao
 * @date 2019-08-21
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String userName) {
        return userInfoDao.findByUsername(userName);
    }
}
