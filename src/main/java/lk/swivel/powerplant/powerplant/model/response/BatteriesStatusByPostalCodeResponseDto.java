package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
@Getter
public class BatteriesStatusByPostalCodeResponseDto extends ResponseDto {

    private BatteriesStaticsResponseDto statics;
    private List<BatteryResponseDto> batteries = new ArrayList<>();


    public BatteriesStatusByPostalCodeResponseDto(List<Battery> batteryList) {
        this.statics = new BatteriesStaticsResponseDto(batteryList);
        this.batteries = batteryList.stream()
                .map(BatteryResponseDto::new).sorted(Comparator.comparing(BatteryResponseDto::getName))
                .collect(Collectors.toList());
    }
}
