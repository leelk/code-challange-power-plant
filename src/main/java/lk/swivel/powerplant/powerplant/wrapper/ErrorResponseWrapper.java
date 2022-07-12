package lk.swivel.powerplant.powerplant.wrapper;

import lk.swivel.powerplant.powerplant.enums.ResponseStatusType;
import lk.swivel.powerplant.powerplant.model.response.ResponseDto;
import lombok.Getter;

/**
 * ErrorResponseWrapper
 */
@Getter
public class ErrorResponseWrapper extends ResponseWrapper {

    private final int errorCode;

    public ErrorResponseWrapper(ResponseStatusType status,
                                String message, ResponseDto data, String displayMessage, int errorCode) {
        super(status, message, data, displayMessage);
        this.errorCode = errorCode;
    }
}
