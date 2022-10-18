package ru.itmo.first_course_project.fitness;

import ru.itmo.first_course_project.fitness.enums.GymZone;

import java.time.LocalDate;
import java.time.LocalTime;

public class Application {

    public static void main(String[] args) {

        GymMembership abon1 = new GymMembership("Сергей", "Сергеев", 1969, "Разовый", 1);
        GymMembership abon2 = new GymMembership("Иван", "Иванов", 1954, "Дневной", 15);
        GymMembership abon3 = new GymMembership("Юрий", "Юрьев", 1997, "Полный", 15);

        FitnessClub bestClub = new FitnessClub();

        bestClub.doTraining(abon1, "Тренажерный зал", LocalTime.of(12, 15)); // Проверка закрытого ФХ
        bestClub.startWorking(LocalDate.now().plusDays(2)); // Запускаем работу клуба
        bestClub.doTraining(abon1, "Тренажерный зал", LocalTime.of(12, 15)); // абонемент ONE_TIME закончился
        bestClub.doTraining(abon2, "Бассейн", LocalTime.of(12, 15)); // Ваш тип абонемента - Дневной. Не дает право тренировок в выбранной зоне - Бассейн
        bestClub.doTraining(abon2, "Тренажерный зал", LocalTime.of(17, 15)); // Ваш тип абонемента не позволяет заниматься в выбранное время
        bestClub.doTraining(abon2, "Тренажерный зал", LocalTime.of(12, 15)); // Запускаем клиента на тренировку в тренажерный зал
        bestClub.doTraining(abon2, "Групповые занятия", LocalTime.of(12, 15)); // Проверяем, что в другую зону не попасть, т.к. клиент уже в трен зале
        bestClub.doTraining(abon3,"Тренажерный зал", LocalTime.of(12, 15)); // Запускаем клиента на тренировку в тренажерный зал (число клиентов в тренажерном зале - 2)

        // Проверка на заполненность зоны тренировок
        for (int i = 0; i < 19; i++) {
            GymMembership abon = new GymMembership("Сергей", "Сергеев", 1969, "Полный", 10);
            bestClub.doTraining(abon,"Тренажерный зал", LocalTime.of(12, 15));
        }

        // Выводим информацию о посетителях
        bestClub.printFitnessClubMembersInfo();

        bestClub.stopWorking(); // клуб закрывается
        bestClub.printFitnessClubMembersInfo(); // смотрим, что никого не осталось
    }
}
