package com.jaindrav.devicemanagementservice.controller;

import com.jaindrav.devicemanagementservice.model.Device;
import com.jaindrav.devicemanagementservice.service.DeviceManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeviceControllerTest {

    @InjectMocks
    private DeviceController deviceController;

    @Mock
    private DeviceManagerService deviceService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    void testGetAllDevices() throws Exception {
        Device device1 = new Device(1L, "Device1", "BrandXYZ", LocalDateTime.now(),null);
        Device device2 = new Device(2L, "Device2", "BrandXYZ", LocalDateTime.now(),null);
        when(deviceService.getAllDevices()).thenReturn(Arrays.asList(device1, device2));

        mockMvc.perform(get("/devices/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(deviceService, times(1)).getAllDevices();
    }

    @Test
    void testCreateDevice() throws Exception {
        Device requestedDevice = new Device(null, "Device1", "BrandXYZ", null, null);
        Device addedDevice = new Device(2L, "Device1", "BrandXYZ", LocalDateTime.now(),null);
        when(deviceService.addDevice(any(Device.class))).thenReturn(addedDevice);

        // Act & Assert
        mockMvc.perform(post("/devices/addDevice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"deviceName\": \"Device1\", \"brand\": \"BrandXYZ\"}"))
                .andExpect(status().isCreated());

        verify(deviceService, times(1)).addDevice(any(Device.class));
    }

    @Test
    void testGetDeviceById() throws Exception {
        Long id = 2L;
        Device device = new Device(id, "Device2", "Brand2", LocalDateTime.now(), null);
        when(deviceService.getDeviceById(id)).thenReturn(Optional.of(device));

        mockMvc.perform(get("/devices/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.deviceName").value("Device2"))
                .andExpect(jsonPath("$.brand").value("Brand2"))
                .andDo(print());
    }

    @Test
    void testUpdateDevice() throws Exception{
        Long deviceId = 1L;
        Device updatedDevice = new Device(deviceId, "UpdatedDevice", "BrandXYZ", LocalDateTime.of(2024, 11, 14, 10, 30, 45), LocalDateTime.now());
        when(deviceService.updateDevice(eq(deviceId), any(Device.class))).thenReturn(updatedDevice);

        mockMvc.perform(put("/devices/update/{deviceId}", deviceId)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceName\": \"updatedDevice\", \"brand\": \"BrandXYZ\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.deviceName").value("UpdatedDevice"))
                .andExpect(jsonPath("$.brand").value("BrandXYZ"))
                .andDo(print());

        verify(deviceService, times(1)).updateDevice(eq(deviceId), any(Device.class));

    }

    @Test
    void testDeleteDevice() throws Exception {
        Long id = 1L;
        doNothing().when(deviceService).deleteDevice(id);

        mockMvc.perform(delete("/devices/delete/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
