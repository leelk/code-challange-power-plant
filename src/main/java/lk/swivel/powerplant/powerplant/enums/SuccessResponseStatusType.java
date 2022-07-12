package lk.swivel.powerplant.powerplant.enums;

import lombok.Getter;

/**
 * Success Response Status Type
 */
@Getter
public enum SuccessResponseStatusType {

    SAVE_BATTERY(2000, "Successfully saved the battery list."),
    BATTERIES_BY_POSTAL_CODE(2001, "Successfully returned the batteries summery.");

    private final int code;
    private final String message;

    SuccessResponseStatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Success code covert into string to read display message from success property file
     *
     * @param successCode successCode
     * @return string code
     */
    public String getCodeString(int successCode) {
        return Integer.toString(successCode);
    }
}
