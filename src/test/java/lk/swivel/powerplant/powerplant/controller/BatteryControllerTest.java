package lk.swivel.powerplant.powerplant.controller;

import lk.swivel.powerplant.powerplant.config.Translator;
import lk.swivel.powerplant.powerplant.enums.ErrorResponseStatusType;
import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lk.swivel.powerplant.powerplant.enums.SuccessResponseStatusType;
import lk.swivel.powerplant.powerplant.exception.PowerPlantException;
import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lk.swivel.powerplant.powerplant.model.request.BatteriesRequestDto;
import lk.swivel.powerplant.powerplant.model.request.BatteryRequestDto;
import lk.swivel.powerplant.powerplant.service.BatteryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class tests the {@link BatteryController} class
 */
class BatteryControllerTest {

    private static final String SAVE_BATTERY_LIST_URL = "/api/v1/batteries";
    private static final String GET_BATTERY_LIST_URL = "/api/v1/batteries/postal-code/{postalCode}";
    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String ERROR_STATUS = "ERROR";

    @Mock
    private BatteryService batteryService;

    @Mock
    private Translator translator;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        openMocks(this);
        BatteryController batteryController = new BatteryController(translator, batteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(batteryController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Should_ReturnsOk_WhenSavingBatteryList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SAVE_BATTERY_LIST_URL)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(getSampleBatteriesRequestDto().toLogJson()))
                .andExpect(status().isOk());
    }

    @Test
    void Should_ReturnBadRequest_When_RequiredFieldIsInvalid() throws Exception {
        BatteriesRequestDto sampleBatteriesRequestDto = getSampleBatteriesRequestDto();
        sampleBatteriesRequestDto.getBatteries().get(0).setWatt(-5754);
        mockMvc.perform(MockMvcRequestBuilders.post(SAVE_BATTERY_LIST_URL)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(sampleBatteriesRequestDto.toLogJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void Should_ReturnInternalServerError_When_Saving_BatteryListIsFailed() throws Exception {
        BatteriesRequestDto sampleBatteriesRequestDto = getSampleBatteriesRequestDto();

        doThrow(new PowerPlantException("ERROR", new Throwable())).when(batteryService).saveBatteries(anyList());
        mockMvc.perform(MockMvcRequestBuilders.post(SAVE_BATTERY_LIST_URL)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(sampleBatteriesRequestDto.toLogJson()))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message")
                        .value(ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getMessage()));
    }

    @Test
    void Should_ReturnOK_When_ValidFieldAreProvidedForGetBatteriesListByPostCode() throws Exception {

        String url = GET_BATTERY_LIST_URL.replace("{postalCode}", "POSTAL_002");
        List<Battery> sampleBatteryList = getSampleBatteryList();
        when(batteryService.getBatteriesByPostalCode("POSTCODE")).thenReturn(sampleBatteryList);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(SUCCESS_STATUS))
                .andExpect(jsonPath("$.message")
                        .value(SuccessResponseStatusType.BATTERIES_BY_POSTAL_CODE.getMessage()));
    }

    @Test
    void should_ReturnBadRequest_When_PostalCodeIsInvalid() throws Exception {
        String url = GET_BATTERY_LIST_URL.replace("{postalCode}", "POSTAL_002154");
        doThrow(new IllegalArgumentException()).when(batteryService).getBatteriesByPostalCode("POSTAL_002154");
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(ERROR_STATUS))
                .andExpect(jsonPath("$.message")
                        .value(ErrorResponseStatusType.INVALID_POSTAL_CODE.getMessage()));
    }

    @Test
    void Should_ReturnInternalServerError_When_GetBatteriesListIsFailed() throws Exception {
        String url = GET_BATTERY_LIST_URL.replace("{postalCode}", "POSTAL_002154");
        doThrow(new PowerPlantException("ERROR", new Throwable()))
                .when(batteryService).getBatteriesByPostalCode("POSTAL_002154");
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(ERROR_STATUS))
                .andExpect(jsonPath("$.message")
                        .value(ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getMessage()));
    }

    /**
     * This method creates a sample BatteriesRequestDto.
     *
     * @return BatteriesRequestDto
     */
    private BatteriesRequestDto getSampleBatteriesRequestDto() {
        BatteriesRequestDto batteriesRequestDto = new BatteriesRequestDto();
        BatteryRequestDto anker = new BatteryRequestDto("ANKER", PostalCodes.POSTAL_002, 451, 1544);
        batteriesRequestDto.getBatteries().add(anker);
        return batteriesRequestDto;
    }


    /**
     * This method creates a sample BatteryList.
     *
     * @return Battery List
     */
    private List<Battery> getSampleBatteryList() {
        List<Battery> batteryList = new ArrayList<>();
        Battery hp = new Battery("bid-4f54e5", "HP", PostalCodes.POSTAL_002, 45, 56);
        Battery anker = new Battery("bid-4f545f", "ANKER", PostalCodes.POSTAL_002, 54, 89);
        Battery apple = new Battery("bid-4f15eg", "APPLE", PostalCodes.POSTAL_004, 26, 56);
        batteryList.add(hp);
        batteryList.add(anker);
        batteryList.add(apple);
        return batteryList;
    }

}