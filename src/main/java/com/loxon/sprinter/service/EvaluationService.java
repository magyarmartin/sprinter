package com.loxon.sprinter.service;

import com.loxon.sprinter.dto.EvaluationDto;
import com.loxon.sprinter.entity.Evaluation;
import com.loxon.sprinter.mapper.EvaluationMapper;
import com.loxon.sprinter.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EvaluationService {

    private EvaluationRepository repository;
    private EvaluationMapper mapper;

    @Autowired
    public EvaluationService( final EvaluationRepository repository, final EvaluationMapper mapper ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public int getCurrentSprintNumber() {
        Evaluation evaluation = repository.findFirstByOrderBySprintNumberDesc();
        return evaluation == null ? 0 : evaluation.getSprintNumber();
    }

    public EvaluationDto getSprintEvaluation( final int sprintNumber ) {
        List<Evaluation> evaluations = repository.findBySprintNumberAndDeletedFalse(sprintNumber);
        return mapper.evaluationsToEvaluationDto(evaluations);
    }

    public void saveSingleEvaluation( final Evaluation evaluation ) {
        if ( evaluation.getComment() == null || evaluation.getComment().isEmpty() ) {
            evaluation.setComment(null);
        }
        evaluation.setCreated(new Date());
        evaluation.setDeleted(false);
        repository.save(evaluation);
    }

    public void resetSprint( final int sprintNumber ) {
        List<Evaluation> evaluations = repository.findBySprintNumber(sprintNumber);
        evaluations.forEach(evaluation -> evaluation.setDeleted(true));
        repository.saveAll(evaluations);
    }

    public void startNewSprint( final Integer sprintNumber ) {
        Evaluation evaluation = new Evaluation();
        if ( sprintNumber != null ) {
            evaluation.setSprintNumber(sprintNumber);
        } else {
            evaluation.setSprintNumber(getCurrentSprintNumber() + 1);
        }
        repository.save(evaluation);
    }

}
