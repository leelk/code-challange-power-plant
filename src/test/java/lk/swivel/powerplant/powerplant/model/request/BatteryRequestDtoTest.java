package lk.swivel.powerplant.powerplant.model.request;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the {@link BatteryRequestDto} class
 */
class BatteryRequestDtoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Should_ReturnTrue_When_RequiredFieldsAreAvailable() {
        BatteryRequestDto batteryRequestDto = getBatteryRequestDto();
        assertTrue(batteryRequestDto.isRequiredAvailable());
    }

    @Test
    void Should_ReturnFalse_When_WattIsInValid() {
        BatteryRequestDto batteryRequestDto = getBatteryRequestDto();
        batteryRequestDto.setWatt(-450);
        assertFalse(batteryRequestDto.isRequiredAvailable());
    }

    @Test
    void Should_ReturnFalse_When_CapacityIsInValid() {
        BatteryRequestDto batteryRequestDto = getBatteryRequestDto();
        batteryRequestDto.setCapacity(-40);
        assertFalse(batteryRequestDto.isRequiredAvailable());
    }

    /**
     * This method creates sample BatteryRequestDto.
     *
     * @return BatteryRequestDto
     */
    private BatteryRequestDto getBatteryRequestDto() {
        var batteryRequestDto = new BatteryRequestDto();
        batteryRequestDto.setName("ABCS");
        batteryRequestDto.setPostCode(PostalCodes.POSTAL_002);
        batteryRequestDto.setCapacity(500.00);
        batteryRequestDto.setWatt(250.0);
        return batteryRequestDto;
    }
}