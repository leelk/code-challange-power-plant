package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatteriesStaticsResponseDto {

    // TODO name change
    private AverageCapacityResponseDto capacity;
    private AverageWattsResponseDto watts;


    public BatteriesStaticsResponseDto(List<Battery> batteryList) {
        this.capacity = new AverageCapacityResponseDto(batteryList);
        this.watts = new AverageWattsResponseDto(batteryList);
    }
}
