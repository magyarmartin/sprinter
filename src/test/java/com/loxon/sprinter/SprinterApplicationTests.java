package com.loxon.sprinter;

import com.loxon.sprinter.dto.EvaluationDto;
import com.loxon.sprinter.entity.Evaluation;
import com.loxon.sprinter.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SprinterApplicationTests {

    @Autowired
    private EvaluationService service;

    @Test
    void contextLoads() {
//        Evaluation evaluation = new Evaluation();
//        evaluation.setSprintNumber(2);
//        evaluation.setComment("adsads");
//        service.saveSingleEvaluation(evaluation);
//        EvaluationDto e = service.getSprintEvaluation(2);
//        System.out.println(e.getComments().get(0));
    }

}
