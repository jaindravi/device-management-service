package com.jaindrav.devicemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceName;
    private String brand;
    private LocalDateTime creationTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updationTime;

}
