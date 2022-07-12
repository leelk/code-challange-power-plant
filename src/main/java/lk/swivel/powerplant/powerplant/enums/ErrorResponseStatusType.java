package lk.swivel.powerplant.powerplant.enums;

import lombok.Getter;

@Getter
public enum ErrorResponseStatusType {

    INTERNAL_SERVER_ERROR(5000, "Internal server error."),
    INVALID_POSTAL_CODE(5001,"Invalid postal code."),
    INVALID_VALUE(5002, "Invalid values for battery watt & capacity.");


    private final int code;
    private final String message;

    ErrorResponseStatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Error code covert into string to read display message from error property file
     *
     * @param errorCode errorCode
     * @return errorCode as string
     */
    public static String getCodeString(int errorCode) {
        return Integer.toString(errorCode);
    }
}
