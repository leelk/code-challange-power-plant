package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AverageCapacityResponseDto extends ResponseDto {
    private static final String CAPACITY_CHARACTER = "mAH";
    private static final String EMPTY_STRING = " ";
    private double totalCapacity;
    private double averageCapacity;
    private String displayTotalCapacity;
    private String displayAverageCapacity;


    public AverageCapacityResponseDto(List<Battery> batteryList) {
        getTotalCapacity(batteryList);
        getAverageCapacity(batteryList.size());
    }

    private void getTotalCapacity(List<Battery> batteryList) {
        double total = batteryList.stream().map(Battery::getCapacity).mapToDouble(Double::doubleValue).sum();
        this.totalCapacity = total;
        this.displayTotalCapacity = total + EMPTY_STRING + CAPACITY_CHARACTER;
    }

    private void getAverageCapacity(int noOfBatteries) {
        double average = (this.totalCapacity / noOfBatteries);
        this.averageCapacity = average;
        this.displayAverageCapacity = average + EMPTY_STRING + CAPACITY_CHARACTER;
    }

}
