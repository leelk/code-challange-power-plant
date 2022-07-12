package lk.swivel.powerplant.powerplant.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BatteriesRequestDto
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BatteriesRequestDto extends RequestDto {

    private List<BatteryRequestDto> batteries = new ArrayList<>();

    @Override
    public boolean isRequiredAvailable() {
        long falseCount =
                batteries.stream()
                        .map(BatteryRequestDto::isRequiredAvailable)
                        .collect(Collectors.toList())
                        .stream().filter(value -> !value)
                        .count();
        return falseCount <= 0;
    }

    @Override
    public String toLogJson() {
        return toJson();
    }
}
