package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
@Getter
public class BatteriesResponseDto extends ResponseDto {

    private List<BatteryResponseDto> batteries = new ArrayList<>();

    public BatteriesResponseDto(List<Battery> batteryList) {
        this.batteries = batteryList.stream()
                .map(BatteryResponseDto::new)
                .collect(Collectors.toList());
    }

}
