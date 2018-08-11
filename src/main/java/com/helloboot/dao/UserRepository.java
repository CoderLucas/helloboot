package com.helloboot.dao;

import com.helloboot.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CoderLucas on 2018/8/7.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
