package com.hw.spring_hw_ap.sheduler;

import com.hw.spring_hw_ap.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceReminderScheduler {

    private final CarService carService;

    @Scheduled(cron = "*/10 * * * * ?") // Запуск каждые 10 секунд
    public void sendReminders() {
        System.out.println("Scheduler triggered: sending maintenance reminders...");
        carService.sendMaintenanceReminder();
        System.out.println("Reminders have been sent.");
    }
}
