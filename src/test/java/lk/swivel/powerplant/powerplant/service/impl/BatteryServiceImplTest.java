package lk.swivel.powerplant.powerplant.service.impl;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lk.swivel.powerplant.powerplant.exception.PowerPlantException;
import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lk.swivel.powerplant.powerplant.model.request.BatteryRequestDto;
import lk.swivel.powerplant.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests the {@link BatteryServiceImpl} class
 */

class BatteryServiceImplTest {

    private BatteryServiceImpl batteryService;
    @Mock
    private BatteryRepository batteryRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        batteryService = new BatteryServiceImpl(batteryRepository);
    }

    @Test
    void ShouldReturnBatteryList_When_ValidPostalCodeIsProved() {
        List<Battery> sampleBatteryList = getSampleBatteryList();
        when(batteryRepository.findBatteriesByPostCode(PostalCodes.POSTAL_002)).thenReturn(sampleBatteryList);
        assertEquals(sampleBatteryList.stream().findFirst().get().getId(),
                batteryService.getBatteriesByPostalCode("POSTAL_002").stream().findFirst().get().getId());
    }

    @Test
    void Should_ThrowPowerPlantException_When_SaveBatteryRequestDtoListFailed() {
        List<BatteryRequestDto> sampleBatteryRequestDtoList = getSampleBatteryRequestDtoList();
        when(batteryRepository.saveAll(anyList())).thenThrow(new DataAccessException("ERROR") {
        });
        PowerPlantException powerPlantException = assertThrows(PowerPlantException.class,
                () -> batteryService.saveBatteries(sampleBatteryRequestDtoList));
        assertEquals("Saving batteries info into database was failed.", powerPlantException.getMessage());
    }

    @Test
    void ShouldThrowPowerPlantException_When_InvalidPostalCodeProvedForGetBatteryListByPostalCode() {
        when(batteryRepository.findBatteriesByPostCode(PostalCodes.POSTAL_002))
                .thenThrow(new DataAccessException("ERROR") {
                });
        PowerPlantException powerPlantException = assertThrows(PowerPlantException.class,
                () -> batteryService.getBatteriesByPostalCode("POSTAL_002"));
        assertEquals("Reading Battery list by postal code: POSTAL_002 was failed.",
                powerPlantException.getMessage());
    }

    /**
     * This method creates sample BatteryRequestDto List.
     *
     * @return BatteryRequestDto List
     */
    private List<BatteryRequestDto> getSampleBatteryRequestDtoList() {
        List<BatteryRequestDto> batteryRequestDos = new ArrayList<>();
        batteryRequestDos.add(new BatteryRequestDto("ABSCD", PostalCodes.POSTAL_002, 25.02, 154));
        batteryRequestDos.add(new BatteryRequestDto("MHBFK", PostalCodes.POSTAL_004, 415.25, 458));
        return batteryRequestDos;
    }

    /**
     * This method creates sample Battery List.
     *
     * @return List<Battery
     */
    private List<Battery> getSampleBatteryList() {
        List<Battery> batteryList = new ArrayList<>();
        batteryList.add(new Battery("bid15-454544", "AFBJA", PostalCodes.POSTAL_002, 1254, 154));
        batteryList.add(new Battery("bid15-feag45", "VMNFE", PostalCodes.POSTAL_002, 25, 415));
        return batteryList;
    }
}