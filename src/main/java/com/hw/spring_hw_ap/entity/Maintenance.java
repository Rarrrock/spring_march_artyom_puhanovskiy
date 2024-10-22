package com.hw.spring_hw_ap.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "maintenances") // Эта аннотация указывает, что объект будет храниться в MongoDB
public class Maintenance {

    @Id
    private String id;

    private String name;
    private String description;
    private double price;
}


