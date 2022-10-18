package ru.itmo.first_course_project.fitness.enums;

import java.time.LocalTime;

public enum GymMembershipType {
    ONE_TIME(
            LocalTime.of(8, 0),
            LocalTime.of(22, 0),
            "Разовый"),
    DAILY(
            LocalTime.of(8, 0),
            LocalTime.of(16, 0),
            "Дневной"),
    FULL(
            LocalTime.of(8, 0),
            LocalTime.of(22, 0),
            "Полный");

    private final LocalTime startTime, endTime;
    private String gymMembershipTypeName;

    GymMembershipType(LocalTime startTime, LocalTime endTime, String gymMembershipTypeName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.gymMembershipTypeName = gymMembershipTypeName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getGymMembershipTypeName() {return gymMembershipTypeName;}
}
