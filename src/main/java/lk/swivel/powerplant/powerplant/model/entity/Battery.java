package lk.swivel.powerplant.powerplant.model.entity;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lk.swivel.powerplant.powerplant.model.request.BatteryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Battery {

    @Transient
    private static final String BATTERY_ID_PREFIX = "bid-";

    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PostalCodes postCode;
    private double watt;
    private double capacity;


    public Battery(BatteryRequestDto batteryRequestDto) {
        this.id = BATTERY_ID_PREFIX + UUID.randomUUID();
        this.name = batteryRequestDto.getName();
        this.postCode = batteryRequestDto.getPostCode();
        this.watt = batteryRequestDto.getWatt();
        this.capacity = batteryRequestDto.getCapacity();
    }
}
