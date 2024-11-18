package com.jaindrav.devicemanagementservice.service.impl;

import com.jaindrav.devicemanagementservice.model.Device;
import com.jaindrav.devicemanagementservice.repository.DeviceRepository;
import com.jaindrav.devicemanagementservice.service.DeviceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceManagerServiceImpl implements DeviceManagerService {

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll()                                                                                                                           ;
    }

    @Override
    public void addDevice(Device device) {
        device.setCreationTime(LocalDateTime.now());
        deviceRepository.save(device);
    }

    @Override
    public Optional<Device> getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId);
    }

    @Override
    public Device updateDevice(Long deviceID, Device device) {
        return deviceRepository.findById(deviceID).map(existingDevice -> {
            if(device.getDeviceName()!=null && !device.getDeviceName().isEmpty()){
                existingDevice.setDeviceName(device.getDeviceName());
            }
            if(device.getBrand()!=null && !device.getBrand().isEmpty()){
                existingDevice.setBrand(device.getBrand());
            }
            existingDevice.setUpdationTime(LocalDateTime.now());
            return deviceRepository.save(existingDevice);
        }).orElseThrow(() -> new RuntimeException("Device Not Found"));

    }

    @Override
    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    @Override
    public List<Device> searchDevicesByBrand(String brand) {
       return deviceRepository.findAllByBrand(brand);
    }
}
