package food.external;

import org.springframework.stereotype.Service;

@Service
public class CookingServiceImpl implements CookingService {

    /**
     * Fallback
     */
    public Cooking getCooking(Long id) {
        Cooking cooking = new Cooking();
        return cooking;
    }
}
