package com.loxon.sprinter.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Data
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "sprint_number")
    private int sprintNumber;

    @CreatedDate
    private Date created;

    @Column(name = "delivering_value")
    private int deliveringValue;

    @Column(name = "easy_to_release")
    private int easyToRelease;

    private int fun;

    @Column(name = "health_of_codebase")
    private int healthOfCodebase;

    private int teamwork;

    @Column(name = "connection_to_mission")
    private int connectionToMission;

    private int learning;

    private int control;

    private int coordination;

    private boolean deleted;

    private String comment;

}
