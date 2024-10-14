package com.hw.spring_hw_ap.repository_2;

import com.hw.spring_hw_ap.models_2.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByEnginePowerBetween(int minEnginePower, int maxEnginePower);
}

