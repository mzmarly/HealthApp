package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dailyStars")
@Data
public class DailyStars {
    @Id
    @Column(name = "dailyStars_id")
    private long dailyStars_id;

    @Column(name = "loginNumberDayInRow")
    private int loginNumberDayInRow;

    public DailyStars() {
    }

    @Column(name = "goodReportInRow")
    private int goodReportInRow;

    @Column(name = "loginMessage")
    private String loginMessage;

    @Column(name = "message")
    private String reportMessage;

}
