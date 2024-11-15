package com.jaindrav.devicemanagementservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private long id;
    private String deviceName;
    private String brand;
    private LocalDateTime creationTime;

}
