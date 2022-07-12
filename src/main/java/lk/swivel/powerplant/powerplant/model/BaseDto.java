package lk.swivel.powerplant.powerplant.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swivel.powerplant.powerplant.exception.PowerPlantException;

/**
 * BaseDto
 */
public interface BaseDto {
    /**
     * This method converts object to json string.
     *
     * @return json string
     */
    default String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new PowerPlantException("Object to json conversion was failed.", e);
        }
    }

    /**
     * This method converts object to json string for logging purpose.
     * PII data should be obfuscated.
     *
     * @return json string
     */
    String toLogJson();

}
