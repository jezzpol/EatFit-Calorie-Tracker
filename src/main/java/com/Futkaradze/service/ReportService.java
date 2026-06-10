package com.Futkaradze.service;

import com.Futkaradze.exception.UserException;
import com.Futkaradze.responses.CaloriesCheckResponse;
import com.Futkaradze.responses.DailyReportResponse;
import java.time.LocalDate;
import java.util.Map;

public interface ReportService {
    DailyReportResponse getDailyHistory(Long id, LocalDate date) throws UserException;

    CaloriesCheckResponse checkDailyCalories(Long id, LocalDate date) throws UserException;

    Map<LocalDate, Integer> getCaloriesHistory(Long userId, LocalDate date);
}
