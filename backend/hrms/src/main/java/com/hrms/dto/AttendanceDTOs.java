package com.hrms.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDTOs {

    @Data
    public static class CheckInRequest {
        private LocalDate date;
        private LocalTime checkIn;
    }

    @Data
    public static class CheckOutRequest {
        private LocalTime checkOut;
        private String remarks;
    }

    @Data
    public static class Response {
        private Long id;
        private Long employeeDbId;
        private String employeeName;
        private LocalDate date;
        private LocalTime checkIn;
        private LocalTime checkOut;
        private Double workHours;
        private String status;
        private String remarks;
    }
}