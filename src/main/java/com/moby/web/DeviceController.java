package com.moby.web;

import com.moby.entiry.Device;
import com.moby.entiry.DeviceType;
import com.moby.entiry.Room;
import com.moby.service.DeviceService;
import com.moby.service.DeviceTypeService;
import com.moby.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Moby on 16/6/12.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String deviceHome(Model model) {
        List<Device> list = deviceService.findAllDevice();
        List<DeviceType> deviceTypeList = deviceTypeService.findAllDeviceType();
        List<Room> roomList = roomService.findAllRoom();

        model.addAttribute("list", list);
        model.addAttribute("deviceTypeList", deviceTypeList);
        model.addAttribute("roomList", roomList);

        return "device/home";
    }

    @RequestMapping(value = "/{deviceID}/detail", method = RequestMethod.GET)
    public String deviceDetail(@PathVariable("deviceID") Long deviceID, Model model) {
        if (deviceID == null) {
            return "redirect:/device/home";
        }
        Device device = deviceService.getDeviceById(deviceID);
        if (device == null) {
            return "redirect:/device/home";
        }
        model.addAttribute("device", device);
        return "device/device_detail";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addDevice(Model model) {

        return "device/device_add";
    }


}
