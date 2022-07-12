package lk.swivel.powerplant.powerplant.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WattResponseDto extends ResponseDto {

    private static final String CAPACITY_CHARACTER = "W";
    private static final String EMPTY_STRING = " ";
    private final double watts;
    private final String displayWatts;


    public WattResponseDto(double watts) {
        this.watts = watts;
        this.displayWatts = watts + EMPTY_STRING + CAPACITY_CHARACTER;
    }
}
