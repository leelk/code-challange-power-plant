package lk.swivel.powerplant.powerplant.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CapacityResponseDto extends ResponseDto {

    private static final String CAPACITY_CHARACTER = "mAH";
    private static final String EMPTY_STRING = " ";
    private final double capacity;
    private final String displayCapacity;


    public CapacityResponseDto(double capacity) {
        this.capacity = capacity;
        this.displayCapacity = capacity + EMPTY_STRING + CAPACITY_CHARACTER;
    }
}
