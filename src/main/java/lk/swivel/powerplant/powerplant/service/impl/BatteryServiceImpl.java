package lk.swivel.powerplant.powerplant.service.impl;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lk.swivel.powerplant.powerplant.exception.PowerPlantException;
import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lk.swivel.powerplant.powerplant.model.request.BatteryRequestDto;
import lk.swivel.powerplant.powerplant.repository.BatteryRepository;
import lk.swivel.powerplant.powerplant.service.BatteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;

    /**
     * This method saves new batteries' info into database.
     *
     * @param batteryRequestDos batteryRequestDtoList
     * @return Battery list
     */
    @Override
    public List<Battery> saveBatteries(List<BatteryRequestDto> batteryRequestDos) {
        try {
            List<Battery> batteryList = batteryRequestDos
                    .stream()
                    .map(Battery::new)
                    .collect(Collectors.toList());
            return batteryRepository.saveAll(batteryList);
        } catch (DataAccessException e) {
            throw new PowerPlantException("Saving batteries info into database was failed.", e);
        }
    }

    /**
     * This method returns the battery list by postal code.
     *
     * @param postalCode postal code
     * @return Battery list.
     */
    @Override
    public List<Battery> getBatteriesByPostalCode(String postalCode) {
        try {
            PostalCodes postCode = PostalCodes.valueOf(postalCode);
            return batteryRepository.findBatteriesByPostCode(postCode);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        } catch (DataAccessException e) {
            throw new PowerPlantException("Reading Battery list by postal code: " + postalCode + " was failed.", e);
        }
    }
}
