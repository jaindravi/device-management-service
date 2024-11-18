package com.jaindrav.devicemanagementservice.service;

import com.jaindrav.devicemanagementservice.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceManagerService {

     List<Device> getAllDevices();
     Device addDevice(Device device);
     Optional<Device> getDeviceById(Long deviceId);
     Device updateDevice(Long deviceID, Device device);
     void deleteDevice(Long deviceId);
     List<Device> searchDevicesByBrand(String brand);

}
