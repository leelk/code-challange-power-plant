package lk.swivel.powerplant.powerplant.model.request;

import lk.swivel.powerplant.powerplant.model.BaseDto;

/**
 * RequestDto
 */
public abstract class RequestDto implements BaseDto {

    public boolean isRequiredAvailable() {
        return true;
    }
}
