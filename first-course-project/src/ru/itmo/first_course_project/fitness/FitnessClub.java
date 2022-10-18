package ru.itmo.first_course_project.fitness;

import ru.itmo.first_course_project.fitness.enums.GymMembershipType;
import ru.itmo.first_course_project.fitness.enums.GymZone;

import java.time.LocalDate;
import java.time.LocalTime;

public class FitnessClub {

    private GymMembership[] swimmingPoolMembers;
    private GymMembership[] groupClassesMembers;
    private GymMembership[] gymMembers;

    private LocalDate workingDate;

    public FitnessClub() {
        clubInitialization();
    }

    private void setWorkingDate(LocalDate workingDate) {
        if (workingDate == null) throw new IllegalArgumentException("Необходимо передать корректную дату работы фитнес клуба");
        this.workingDate = workingDate;
    }

    private void clubInitialization() {
        workingDate = null;
        swimmingPoolMembers = new GymMembership[20];
        groupClassesMembers = new GymMembership[20];
        gymMembers = new GymMembership[20];
    }

    public void startWorking(LocalDate workingDate) {
        System.out.println("Дата: " + workingDate + ". Клуб начинает работу.");
        setWorkingDate(workingDate);
    }

    public void stopWorking() {
        clubInitialization();
    }

    private boolean isFitnessClubWorking() {
        return workingDate != null;
    }

    private boolean isGymMembershipActive(GymMembership membership) {
        return membership.getEndTime().isAfter(workingDate);
    }

    private boolean isTrainingTimecorrect(GymMembership membership, LocalTime trainingTime) {
        return trainingTime.isAfter(membership.getGymMembershipType().getStartTime()) &&
                trainingTime.isBefore(membership.getGymMembershipType().getEndTime());
    }

    private boolean isAccessToZoneGranted(GymZone gymZone, GymMembership membership) {
        return gymZone.isAccessGranted(membership);
    }

    private boolean isTrainingZoneFull(GymMembership[] trainingZone) {
        for (GymMembership member: trainingZone) {
            if (member == null) return false;
        }
        return true;
    }

    private boolean isMembershipInZone(GymMembership membership, GymMembership[] gymZoneMemberships) {
        for (GymMembership gymZoneMembership: gymZoneMemberships) {
            if (gymZoneMembership == null) break;
            if (gymZoneMembership.equals(membership)) return true;
        }
        return false;
    }

    private void addToTrainingZone(GymMembership[] trainingZone, GymMembership membership) {
        for (int i = 0; i < trainingZone.length; i++) {
            if (trainingZone[i] == null) {
                trainingZone[i] = membership;
                System.out.println("Посетитель добавлен в выбранную зону");
                break;
            }

        }
    }

    public void doTraining(GymMembership membership, String trainingZone, LocalTime trainingTime) {
        if (membership == null) throw new IllegalArgumentException("Некорректный абонемент");
        if (trainingZone == null) throw new IllegalArgumentException("Выбрана некорректная зона для занятий");
        if (trainingTime == null) throw new IllegalArgumentException("Некорректное желаемое время тренировки");

        if (!isFitnessClubWorking()) {
            System.out.println("К сожалению, фитнес клуб в данный момент закрыт. Приходите в другое время");
            return;
        }

        GymZone gymZone = GymZone.getGymZoneByZoneName(trainingZone);

        if (gymZone == null) {
            System.out.println("К сожалению, в нашем фитнес клубе отсутствует выбранная вами зона для тренировок");
            return;
        }

        if (!isGymMembershipActive(membership)) {
            System.out.println("Ваш абонемент закончился " + membership.getEndTime() + ". Продлите абонемент");
            return;
        }

        if (!isTrainingTimecorrect(membership, trainingTime)) {
            System.out.println("Ваш тип абонемента не позволяет заниматься в выбранное время");
            return;
        }

        if (!isAccessToZoneGranted(gymZone, membership)) {
            System.out.println("Ваш тип абонемента - " + membership.getGymMembershipType().getGymMembershipTypeName() +
                    ". Не дает право тренировок в выбранной зоне - " + gymZone.getGymZoneName());
            return;
        }

        if (    isMembershipInZone(membership , swimmingPoolMembers) ||
                isMembershipInZone(membership, groupClassesMembers) ||
                isMembershipInZone(membership, gymMembers)) {
            System.out.println("Вы уже записаны в другую зону тренировок");
            return;
        }

        if (gymZone.equals(GymZone.SWIMMING_POOL) && !isTrainingZoneFull(swimmingPoolMembers)) {
            addToTrainingZone(swimmingPoolMembers, membership);
            System.out.println("Проходите на тренировку в бассейн");
        } else if (gymZone.equals(GymZone.GROUP_CLASSES) && !isTrainingZoneFull(groupClassesMembers)) {
            addToTrainingZone(groupClassesMembers, membership);
            System.out.println("Проходите на тренировку в зал групповых занятий");
        } else if (gymZone.equals(GymZone.GYM) && !isTrainingZoneFull(gymMembers)) {
            addToTrainingZone(gymMembers, membership);
            System.out.println("Проходите на тренировку в тренажерный зал");
        } else {
            System.out.println("В выбранной Вами зоне тренировок нет свободных мест");
        }
    }

}
