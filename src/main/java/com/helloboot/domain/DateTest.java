package com.helloboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author lujianhao
 * @date 2018/12/16
 */
@Data
@Entity
@Table(name = "date_test")
public class DateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime timestamp;
    private LocalDateTime datetime;
}
