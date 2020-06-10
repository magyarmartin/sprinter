package com.loxon.sprinter.repository;

import com.loxon.sprinter.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findBySprintNumber( final int sprintNumber );

    List<Evaluation> findBySprintNumberAndDeletedFalse( final int sprintNumber );

    Evaluation findFirstByOrderBySprintNumberDesc();

    void deleteAllBySprintNumber( final int sprintNumber );

}
