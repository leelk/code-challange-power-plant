package lk.swivel.powerplant.powerplant.service;

import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lk.swivel.powerplant.powerplant.model.request.BatteryRequestDto;

import java.util.List;

public interface BatteryService {

    List<Battery> saveBatteries(List<BatteryRequestDto> batteryRequestDos);

    List<Battery> getBatteriesByPostalCode(String postalCode);
}
