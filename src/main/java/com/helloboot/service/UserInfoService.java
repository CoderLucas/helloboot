package com.helloboot.service;

import com.helloboot.model.UserInfo;

/**
 * @author lujianhao
 * @date 2019-08-21
 */
public interface UserInfoService {
    UserInfo findByUsername(String userName);
}
