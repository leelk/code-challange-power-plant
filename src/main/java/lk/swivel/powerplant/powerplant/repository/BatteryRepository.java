package lk.swivel.powerplant.powerplant.repository;

import lk.swivel.powerplant.powerplant.enums.PostalCodes;
import lk.swivel.powerplant.powerplant.model.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Battery repository
 */
public interface BatteryRepository extends JpaRepository<Battery, String> {

    /**
     * This method used to get battery list by postal code.
     *
     * @param postalCode postal code
     * @return Battery list
     */
    List<Battery> findBatteriesByPostCode(PostalCodes postalCode);

}
