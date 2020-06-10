package com.loxon.sprinter.mapper;

import com.loxon.sprinter.dto.EvaluationDto;
import com.loxon.sprinter.entity.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationMapper {

    public EvaluationDto evaluationsToEvaluationDto(final List<Evaluation> evaluations ) {
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluations.forEach(evaluation -> {
            evaluationDto.getDeliveringValue().addValue(evaluation.getDeliveringValue());
            evaluationDto.getEasyToRelease().addValue(evaluation.getEasyToRelease());
            evaluationDto.getFun().addValue(evaluation.getFun());
            evaluationDto.getConnectionToMission().addValue(evaluation.getConnectionToMission());
            evaluationDto.getSustainedValue().addValue(evaluation.getSustainedValue());
            evaluationDto.getTeamwork().addValue(evaluation.getTeamwork());
            if ( evaluation.getComment() != null ) {
                evaluationDto.getComments().add(evaluation.getComment());
            }
        });
        if ( !evaluations.isEmpty() ) {
            evaluationDto.setSprintNumber(evaluations.get(0).getSprintNumber());
        }
        return evaluationDto;
    }

}
