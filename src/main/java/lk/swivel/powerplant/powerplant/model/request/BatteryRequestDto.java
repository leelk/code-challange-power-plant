package lk.swivel.powerplant.powerplant.model.request;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * BatteryRequestDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatteryRequestDto extends RequestDto {

    private String name;
    private PostalCodes postCode;
    private double watt;
    private double capacity;

    @Override
    public boolean isRequiredAvailable() {
        return watt >= 0.0 && capacity >= 0.0;
    }

    @Override
    public String toLogJson() {
        return toJson();
    }
}
