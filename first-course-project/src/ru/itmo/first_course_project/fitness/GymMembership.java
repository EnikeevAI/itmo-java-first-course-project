package ru.itmo.first_course_project.fitness;

import ru.itmo.first_course_project.fitness.enums.GymMembershipType;

import java.time.LocalDate;

public class GymMembership {

    private String firstName;

    private String secondName;

    private int birthYear;
    private GymMembershipType gymMembershipType;
    private LocalDate startTime, endTime;

    public GymMembership(String firstName, String secondName, int birthYear, String gymMembershipType, int daysDuration) {
        setFirstName(firstName);
        setSecondName(secondName);
        setBirthYear(birthYear);
        setGymMembershipType(gymMembershipType);
        this.startTime = LocalDate.now();
        setEndTime(daysDuration);
        System.out.println("Абонемент успешно создан");
    }

    private void setFirstName(String firstName) {
        if (firstName.length() < 3 || firstName == null)
            throw new IllegalArgumentException("Имя клиента в абонементе должно содержать минимум 3 буквы");
        this.firstName = firstName;
    }

    private void setSecondName(String secondName) {
        if (secondName.length() < 3 || secondName == null)
            throw new IllegalArgumentException("Фамилия клиента в абонементе должна содержать минимум 3 буквы");
        this.secondName = secondName;
    }

    private void setBirthYear(int birthYear) {
        int currentYear = LocalDate.now().getYear();
        if (currentYear - birthYear > 80 || currentYear - birthYear < 0)
            throw new IllegalArgumentException("Проверьте правильность ввода года рождения клиента");
    }

    private void setGymMembershipType(String gymMembershipType) {
        if (gymMembershipType == null) throw new IllegalArgumentException("Необходимо задать тип абонемента");

        for (GymMembershipType gymType: GymMembershipType.values()) {
            if (gymType.getGymMembershipTypeName().toLowerCase().equals(gymMembershipType.toLowerCase())) {
                this.gymMembershipType = gymType;
            }
        }

        if (this.gymMembershipType == null)
            throw new IllegalArgumentException("Такого вида абонемента не существует. Проверьте правильность выбора");
    }

    private void setEndTime(int daysDuration) {
        if (daysDuration < 1) throw new IllegalArgumentException("Длительность абонемента д.б. не менее 1 дня");

        if (this.gymMembershipType.equals(GymMembershipType.ONE_TIME) && daysDuration != 1) {
            System.out.printf("Для разового абонемента длительность 1 день. Количество дней %d заменено на 1\n", daysDuration);
            this.endTime = this.startTime.plusDays(1);
        } else {
            this.endTime = this.startTime.plusDays(daysDuration);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public GymMembershipType getGymMembershipType() {
        return gymMembershipType;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
