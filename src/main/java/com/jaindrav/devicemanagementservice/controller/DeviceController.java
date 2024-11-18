package com.jaindrav.devicemanagementservice.controller;

import com.jaindrav.devicemanagementservice.model.Device;
import com.jaindrav.devicemanagementservice.service.DeviceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceManagerService deviceService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Device>> getAllDevices(){
        List<Device> allDevices = deviceService.getAllDevices();
        if(allDevices!=null && !allDevices.isEmpty()){
            return new ResponseEntity<>(allDevices, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(allDevices, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDevice")
    public ResponseEntity<Device> addNewDevice(@RequestBody Device device){
        try{
            deviceService.addDevice(device);
            return new ResponseEntity<>(device, HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long deviceId){
        Optional<Device> device = deviceService.getDeviceById(deviceId);
        return device.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable Long deviceId){
        try{
            deviceService.deleteDevice(deviceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{deviceId}")
    public ResponseEntity<?> updateDevice(@PathVariable Long deviceId, @RequestBody Device device){
     try{
         Device updatedDevice = deviceService.updateDevice(deviceId, device);
         return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
     }catch(Exception ex){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
    }

    @GetMapping("/getByBrand")
    public ResponseEntity<List<Device>> getDevicesByBrand(@RequestParam String brand){
        List<Device> deviceList = deviceService.searchDevicesByBrand(brand);
        if(deviceList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(deviceList);
    }
}


