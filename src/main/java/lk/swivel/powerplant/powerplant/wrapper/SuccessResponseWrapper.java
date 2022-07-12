package lk.swivel.powerplant.powerplant.wrapper;

import lk.swivel.powerplant.powerplant.enums.ResponseStatusType;
import lk.swivel.powerplant.powerplant.enums.SuccessResponseStatusType;
import lk.swivel.powerplant.powerplant.model.response.ResponseDto;
import lombok.Getter;

/**
 * SuccessResponseWrapper
 */
@Getter
public class SuccessResponseWrapper extends ResponseWrapper {

    private final int statusCode;

    public SuccessResponseWrapper(ResponseStatusType status, SuccessResponseStatusType successResponseStatusType,
                                  ResponseDto responseDto, String displayMessage) {
        super(status, successResponseStatusType.getMessage(), responseDto, displayMessage);
        this.statusCode = successResponseStatusType.getCode();
    }

}
