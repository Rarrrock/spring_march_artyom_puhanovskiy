package com.hw.spring_hw_ap.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Генерация зашифрованных паролей для пользователей
        String adminPassword = "admin_password"; // Пароль для администратора
//        $2a$10$RHbqC85ezIayigCRBIoxSuilP9Tp8I8BGe4rdOOAatOJfH3SqSzFW
        String userPassword = "user_password"; // Пароль для обычного пользователя
//        $2a$10$EVfg86v/wTJ6pA.VhSPnQOpVgaM3eydWm6zVVJ9gHIMtwxq7xbNwa

        // Шифрование паролей
        String encodedAdminPassword = encoder.encode(adminPassword);
        String encodedUserPassword = encoder.encode(userPassword);

        // Вывод зашифрованных паролей
        System.out.println("Зашифрованный пароль администратора: " + encodedAdminPassword);
        System.out.println("Зашифрованный пароль пользователя: " + encodedUserPassword);
    }
}