package com.loxon.sprinter.dto;

import lombok.Data;

@Data
public class EvaluationValue {

    private int helpful;

    private int meh;

    private int disaster;

    public void addValue( final int value ) {
        switch (value) {
            case 0:
                break;
            case 1:
                ++disaster;
                break;
            case 2:
                ++meh;
                break;
            case 3:
                ++helpful;
                break;
            default:
                throw new IllegalStateException("There is no EvaluationValue defined with value: " + value);
        }
    }

}
