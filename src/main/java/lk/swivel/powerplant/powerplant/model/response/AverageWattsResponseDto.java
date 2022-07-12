package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AverageWattsResponseDto extends ResponseDto {

    private static final String WATT_CHARACTER = "W";
    private static final String EMPTY_STRING = " ";
    private double totalWatts;
    private String displayTotalWatts;
    private double averageWatts;
    private String displayAverageWatts;


    public AverageWattsResponseDto(List<Battery> batteryList) {

        getTotalWatts(batteryList);
        getAverageWatts(batteryList.size());
    }

    private void getTotalWatts(List<Battery> batteryList) {
        double total = batteryList.stream().map(Battery::getWatt).mapToDouble(Double::doubleValue).sum();
        this.totalWatts = total;
        this.displayTotalWatts = total + EMPTY_STRING + WATT_CHARACTER;
    }

    private void getAverageWatts(int noOfBattery) {
        double average = (this.totalWatts / noOfBattery);
        this.averageWatts = average;
        this.displayAverageWatts = average + EMPTY_STRING + WATT_CHARACTER;

    }
}
