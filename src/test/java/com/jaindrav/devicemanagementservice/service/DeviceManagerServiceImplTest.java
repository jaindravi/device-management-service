package com.jaindrav.devicemanagementservice.service;

import com.jaindrav.devicemanagementservice.model.Device;
import com.jaindrav.devicemanagementservice.repository.DeviceRepository;
import com.jaindrav.devicemanagementservice.service.impl.DeviceManagerServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeviceManagerServiceImplTest {

    @InjectMocks
    private DeviceManagerServiceImpl deviceServiceImpl;

    @Mock
    DeviceRepository deviceRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDevices(){
        when(deviceRepository.findAll()).thenReturn(getDeviceList());

        List<Device> devicesList = deviceServiceImpl.getAllDevices();

        assertNotNull(devicesList);
        assertEquals(2, devicesList.size());
        verify(deviceRepository, times(1)).findAll();
    }

    @Test
    void testAddDevice(){
        Device requestedDevice = new Device(null, "Device1", "BrandXYZ", null, null);
        Device addedDevice = new Device(1L, "Device1", "BrandXYZ", LocalDateTime.now(),null);
        when(deviceRepository.save(requestedDevice)).thenReturn(addedDevice);

        Device createdDevice = deviceServiceImpl.addDevice(requestedDevice);

        assertNotNull(createdDevice);
        assertEquals(1L, createdDevice.getId());
        verify(deviceRepository, times(1)).save(requestedDevice);
    }

    @Test
    void testGetDeviceById(){
        Optional<Device> device = Optional.of(new Device(1L, "Device1", "BrandXYZ", LocalDateTime.now(), null));
        when(deviceRepository.findById(1L)).thenReturn(device);

        Optional<Device> deviceById = deviceServiceImpl.getDeviceById(1L);

        assertNotNull(deviceById);
        assertTrue(deviceById.isPresent());
        assertEquals(1L, deviceById.get().getId());
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateDevice(){
        Device newDevice = new Device(null, "NewDevice1", null, null, null);
        Device oldDevice = new Device(1L, "Device1", "BrandXY", LocalDateTime.of(2024, 11, 14, 10, 30, 45), null);
        Device updatedDevice = new Device(1L, "NewDevice1", "BrandXYZ", LocalDateTime.of(2024, 11, 14, 10, 30, 45), LocalDateTime.now());
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(oldDevice));
        when(deviceRepository.save(oldDevice)).thenReturn(updatedDevice);
       Device updatedNewDevice = deviceServiceImpl.updateDevice(1L, newDevice);

        assertNotNull(updatedNewDevice);
        assertNotNull(updatedNewDevice.getUpdationTime());
    }

    @Test
    void testDeleteDevice_Success() {
        when(deviceRepository.existsById(1L)).thenReturn(true);
        deviceServiceImpl.deleteDevice(1L);

        verify(deviceRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDevice_ThrowsException_WhenDeviceNotFound() {
        Long deviceId = 2L;
        when(deviceRepository.existsById(deviceId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            deviceServiceImpl.deleteDevice(deviceId);
        });

        assertEquals("Device with Id " + deviceId + " not found", exception.getMessage());
        verify(deviceRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGetDeviceByBrand(){
        when(deviceRepository.findAllByBrand("BrandXYZ")).thenReturn(getDeviceList());

        List<Device> devicesByBrandList = deviceServiceImpl.searchDevicesByBrand("BrandXYZ");

        assertNotNull(devicesByBrandList);
        assertEquals(2, devicesByBrandList.size());
        verify(deviceRepository, times(1)).findAllByBrand("BrandXYZ");
    }


    public List<Device> getDeviceList(){
        Device device1 = new Device(1L, "Device1", "BrandXYZ", LocalDateTime.now(),null);
        Device device2 = new Device(2L, "Device2", "BrandXYZ", LocalDateTime.now(),null);
        return Arrays.asList(device1,device2);
    }
}
