package com.helloboot.model;

import com.helloboot.model.DateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lujianhao
 * @date 2018/12/16
 */
@Repository
public interface DateTestDao extends JpaRepository<DateTest, Long> {
}
