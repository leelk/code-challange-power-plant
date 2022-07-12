package lk.swivel.powerplant.powerplant.controller;

import lk.swivel.powerplant.powerplant.config.Translator;
import lk.swivel.powerplant.powerplant.enums.ErrorResponseStatusType;
import lk.swivel.powerplant.powerplant.enums.ResponseStatusType;
import lk.swivel.powerplant.powerplant.enums.SuccessResponseStatusType;
import lk.swivel.powerplant.powerplant.model.response.ResponseDto;
import lk.swivel.powerplant.powerplant.wrapper.ErrorResponseWrapper;
import lk.swivel.powerplant.powerplant.wrapper.ResponseWrapper;
import lk.swivel.powerplant.powerplant.wrapper.SuccessResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Controller {


    protected final Translator translator;

    @Autowired
    public Controller(Translator translator) {
        this.translator = translator;
    }

    /**
     * This method creates the data response for success request.
     *
     * @param responseDto responseDto
     * @return response entity
     */
    protected ResponseEntity<ResponseWrapper> getSuccessResponse(ResponseDto responseDto,
                                                                 SuccessResponseStatusType successResponseStatusType) {

        var successResponseWrapper = new SuccessResponseWrapper(ResponseStatusType.SUCCESS,
                successResponseStatusType, responseDto,
                translator.toLocale(successResponseStatusType.getCodeString(successResponseStatusType.getCode())));
        return new ResponseEntity<>(successResponseWrapper, HttpStatus.OK);
    }

    /**
     * This method creates the internal server error response.
     *
     * @return response entity
     */
    protected ResponseEntity<ResponseWrapper> getInternalServerError() {
        var errorResponseWrapper = new ErrorResponseWrapper(ResponseStatusType.ERROR,
                ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getMessage(), null,
                translator.toLocale(ErrorResponseStatusType.
                        getCodeString(ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getCode())),
                ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getCode());
        return new ResponseEntity<>(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method creates the empty data response for bad request.
     *
     * @param errorsResponseStatusType errorResponseStatusType
     * @return bad request error response
     */
    protected ResponseEntity<ResponseWrapper> getErrorResponse(ErrorResponseStatusType errorsResponseStatusType) {
        var errorResponseWrapper = new ErrorResponseWrapper(ResponseStatusType.ERROR,
                errorsResponseStatusType.getMessage(), null,
                translator.toLocale(ErrorResponseStatusType.getCodeString(errorsResponseStatusType.getCode())),
                errorsResponseStatusType.getCode());
        return new ResponseEntity<>(errorResponseWrapper, HttpStatus.BAD_REQUEST);
    }
}
