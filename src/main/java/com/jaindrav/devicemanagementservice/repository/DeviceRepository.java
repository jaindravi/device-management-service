package com.jaindrav.devicemanagementservice.repository;

import com.jaindrav.devicemanagementservice.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByBrand(String brand);
}
