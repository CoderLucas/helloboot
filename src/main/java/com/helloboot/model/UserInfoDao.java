package com.helloboot.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lujianhao
 * @date 2019-08-21
 */
public interface UserInfoDao  extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String userName);
}
