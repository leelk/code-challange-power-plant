package lk.swivel.powerplant.powerplant.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.swivel.powerplant.powerplant.config.Translator;
import lk.swivel.powerplant.powerplant.enums.ErrorResponseStatusType;
import lk.swivel.powerplant.powerplant.enums.SuccessResponseStatusType;
import lk.swivel.powerplant.powerplant.exception.PowerPlantException;
import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lk.swivel.powerplant.powerplant.model.request.BatteriesRequestDto;
import lk.swivel.powerplant.powerplant.model.response.BatteriesResponseDto;
import lk.swivel.powerplant.powerplant.model.response.BatteriesStatusByPostalCodeResponseDto;
import lk.swivel.powerplant.powerplant.service.BatteryService;
import lk.swivel.powerplant.powerplant.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/batteries")
@Api(value = "", tags = {"Battery Controller"})
@Tag(name = "Battery Controller", description = "Battery related operation are taken place here.")
public class BatteryController extends Controller {

    private final BatteryService batteryService;

    public BatteryController(Translator translator, BatteryService batteryService) {
        super(translator);
        this.batteryService = batteryService;
    }


    /**
     * Save Batteries list.
     *
     * @param batteriesRequestDto batteriesRequestDto
     * @return BatteriesResponseDto
     */
    @PostMapping()
    public ResponseEntity<ResponseWrapper> saveBatteriesList(@RequestBody BatteriesRequestDto batteriesRequestDto) {
        try {
            if (!batteriesRequestDto.isRequiredAvailable()) {
                return getErrorResponse(ErrorResponseStatusType.INVALID_VALUE);
            }
            var savedBatteryList = batteryService.saveBatteries(batteriesRequestDto.getBatteries());
            var batteriesResponseDto = new BatteriesResponseDto(savedBatteryList);
            log.debug("Successfully saved the batteries list.");
            return getSuccessResponse(batteriesResponseDto, SuccessResponseStatusType.SAVE_BATTERY);
        } catch (PowerPlantException e) {
            log.error("Failed to save the battery list.");
            return getInternalServerError();
        }
    }

    /**
     * Get battles list by postal code.
     *
     * @param postalCode postalCode
     * @return BatteriesStatusByPostalCodeResponseDto
     */
    @GetMapping("/postal-code/{postalCode}")
    public ResponseEntity<ResponseWrapper> getBatteriesByPostalArea(@PathVariable String postalCode) {
        try {
            List<Battery> batteriesByPostalCode = batteryService.getBatteriesByPostalCode(postalCode);
            var batteriesStatusByPostalCodeResponseDto =
                    new BatteriesStatusByPostalCodeResponseDto(batteriesByPostalCode);
            log.debug("Successfully returned the batteries summery by postalCode: {}", postalCode);
            return getSuccessResponse(batteriesStatusByPostalCodeResponseDto,
                    SuccessResponseStatusType.BATTERIES_BY_POSTAL_CODE);
        } catch (IllegalArgumentException e) {
            log.error("Failed to returned the batteries summery by postalCode: {}.", postalCode);
            return getErrorResponse(ErrorResponseStatusType.INVALID_POSTAL_CODE);
        } catch (PowerPlantException e) {
            return getInternalServerError();
        }
    }
}
