package com.helloboot.dao;

import com.helloboot.domain.DateTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lujianhao
 * @date 2018/12/16
 */
@Repository
public interface DateTestRepository extends CrudRepository<DateTest, Long> {
}
