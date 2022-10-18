package ru.itmo.first_course_project.fitness.enums;

import ru.itmo.first_course_project.fitness.GymMembership;

import java.io.PrintStream;

public enum GymZone {
    SWIMMING_POOL
            (
                    new GymMembershipType[] {GymMembershipType.ONE_TIME, GymMembershipType.FULL},
                    "Бассейн"),
    GROUP_CLASSES
            (
                    new GymMembershipType[] {GymMembershipType.DAILY, GymMembershipType.FULL},
                    "Групповые занятия"),
    GYM
            (
                    new GymMembershipType[] {GymMembershipType.ONE_TIME, GymMembershipType.DAILY, GymMembershipType.FULL},
                    "Тренажерный зал");

    private GymMembershipType[] validMembership;

    private String gymZoneName;

    GymZone(GymMembershipType[] validMembership, String gymZoneName) {
        this.validMembership = new GymMembershipType[validMembership.length];
        for (int i = 0; i < validMembership.length; i++) {
            this.validMembership[i] = validMembership[i];
        }
        this.gymZoneName = gymZoneName;
    }

    public String getGymZoneName() {
        return this.gymZoneName;
    }

    public static GymZone getGymZoneByZoneName(String gymZoneName) {
        for (GymZone zone: GymZone.values()) {
            if (gymZoneName.toLowerCase().equals(zone.getGymZoneName().toLowerCase())) {
                return zone;
            }
        }
        return null;
    }

    // Доступен ли проход в зону для владельца абонемента
    public boolean isAccessGranted(GymMembership gymMembership) {
        for (GymMembershipType gymMembershipType: validMembership) {
            if(gymMembershipType.equals(gymMembership.getGymMembershipType())) return true;
        }
        return false;
    }

}
