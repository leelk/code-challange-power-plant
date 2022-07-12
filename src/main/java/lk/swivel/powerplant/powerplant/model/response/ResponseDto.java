package lk.swivel.powerplant.powerplant.model.response;

import lk.swivel.powerplant.powerplant.model.BaseDto;

public abstract class ResponseDto implements BaseDto {

    @Override
    public String toLogJson() {
        return toJson();
    }
}
