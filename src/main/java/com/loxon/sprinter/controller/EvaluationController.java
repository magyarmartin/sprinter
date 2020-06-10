package com.loxon.sprinter.controller;

import com.loxon.sprinter.dto.EvaluationDto;
import com.loxon.sprinter.entity.Evaluation;
import com.loxon.sprinter.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController {

    private EvaluationService service;

    @Autowired
    public EvaluationController( final EvaluationService service ) {
        this.service = service;
    }

    @GetMapping("/sprint/number")
    public int getCurrentSprintNumber() {
        return service.getCurrentSprintNumber();
    }

    @GetMapping("/evaluation/{sprintNumber}")
    public EvaluationDto getSprintEvaluation( @PathVariable("sprintNumber") final int sprintNumber ) {
        return service.getSprintEvaluation(sprintNumber);
    }

    @PostMapping("/evaluation")
    public void saveSingleEvaluation( @RequestBody final Evaluation evaluation ) {
        service.saveSingleEvaluation(evaluation);
    }

    @DeleteMapping("/sprint/reset")
    public void resetSpring( @RequestBody final int sprintNumber ) {
        service.resetSprint(sprintNumber);
    }

    @PostMapping("/sprint/new")
    public void startNewSprint( @RequestBody final Integer sprintNumber ) {
        service.startNewSprint(sprintNumber);
    }

}
