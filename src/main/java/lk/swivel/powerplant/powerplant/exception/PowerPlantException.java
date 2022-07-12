package lk.swivel.powerplant.powerplant.exception;

public class PowerPlantException extends RuntimeException {

    /**
     * Authentication Exception with error message and throwable error
     *
     * @param errorMessage error message
     * @param error        error
     */
    public PowerPlantException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
