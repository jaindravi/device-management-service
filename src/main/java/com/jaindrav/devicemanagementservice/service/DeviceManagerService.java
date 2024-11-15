package com.jaindrav.devicemanagementservice.service;

import com.jaindrav.devicemanagementservice.model.Device;
import org.springframework.stereotype.Service;

@Service
public interface DeviceManagerService {

    public Device getAllDevices();
    public void addDevice(Device device);
    public Device getDeviceById(long deviceId);
    public void updateDevice(Device device);
    public void deleteDevice(String deviceName);
    public Device searchDeviceByName(String deviceName);

}
