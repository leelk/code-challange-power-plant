package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lk.swivel.powerplant.powerplant.model.entity.Battery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatteryResponseDto extends ResponseDto {

    private String id;
    private String name;
    private PostalCodes postCode;
    private WattResponseDto watts;
    private CapacityResponseDto capacity;

    public BatteryResponseDto(Battery battery) {
        this.id = battery.getId();
        this.name = battery.getName();
        this.postCode = battery.getPostCode();
        this.watts = new WattResponseDto(battery.getWatt());
        this.capacity = new CapacityResponseDto(battery.getCapacity());
    }
}
