package com.loxon.sprinter.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EvaluationDto {

    public EvaluationDto() {
        deliveringValue = new EvaluationValue();
        easyToRelease = new EvaluationValue();
        fun = new EvaluationValue();
        healthOfCodebase = new EvaluationValue();
        teamwork = new EvaluationValue();
        connectionToMission = new EvaluationValue();
        control = new EvaluationValue();
        learning = new EvaluationValue();
        coordination = new EvaluationValue();
        comments = new ArrayList<>();
    }

    private int sprintNumber;

    private EvaluationValue deliveringValue;

    private EvaluationValue easyToRelease;

    private EvaluationValue fun;

    private EvaluationValue healthOfCodebase;

    private EvaluationValue teamwork;

    private EvaluationValue connectionToMission;

    private EvaluationValue learning;

    private EvaluationValue control;

    private EvaluationValue coordination;

    private List<String> comments;

}
