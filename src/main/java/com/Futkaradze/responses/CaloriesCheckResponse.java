package com.Futkaradze.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaloriesCheckResponse {
    private boolean ExceedingLimit;

    private Integer CaloriesConsumed;

    private Integer CalorieNorm;
}