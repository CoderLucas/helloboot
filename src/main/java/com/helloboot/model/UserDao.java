package com.helloboot.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CoderLucas on 2018/8/7.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
