package lk.swivel.powerplant.powerplant.model.request;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the {@link BatteriesRequestDto} class
 */
class BatteriesRequestDtoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Should_ReturnTrue_When_RequiredFieldsAreAvailable() {
        BatteriesRequestDto batteriesRequestDto = getBatteriesRequestDto();
        assertTrue(batteriesRequestDto.isRequiredAvailable());
    }

    @Test
    void Should_ReturnFalse_When_OneBatteryCapacityIsInvalid() {
        BatteriesRequestDto batteriesRequestDto = getBatteriesRequestDto();
        batteriesRequestDto.getBatteries().get(0).setCapacity(-254);
        assertFalse(batteriesRequestDto.isRequiredAvailable());
    }

    @Test
    void Should_ReturnFalse_When_OneBatteryWattIsInvalid() {
        BatteriesRequestDto batteriesRequestDto = getBatteriesRequestDto();
        batteriesRequestDto.getBatteries().get(0).setWatt(-254);
        assertFalse(batteriesRequestDto.isRequiredAvailable());
    }

    /**
     * This method creates sample BatteriesRequestDto.
     *
     * @return BatteriesRequestDto
     */
    private BatteriesRequestDto getBatteriesRequestDto() {
        BatteriesRequestDto batteriesRequestDto = new BatteriesRequestDto();
        BatteryRequestDto batteryRequestOne =
                new BatteryRequestDto("ABDS", PostalCodes.POSTAL_002, 12, 45);
        BatteryRequestDto batteryRequestTwo =
                new BatteryRequestDto("AVDDX", PostalCodes.POSTAL_004, 152, 521);
        BatteryRequestDto batteryRequestThree =
                new BatteryRequestDto("BKDEU", PostalCodes.POSTAL_002, 185, 742);
        batteriesRequestDto.getBatteries().add(batteryRequestOne);
        batteriesRequestDto.getBatteries().add(batteryRequestTwo);
        batteriesRequestDto.getBatteries().add(batteryRequestThree);
        return batteriesRequestDto;
    }
}