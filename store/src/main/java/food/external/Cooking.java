package food.external;

import java.util.Date;
import lombok.Data;

@Data
public class Cooking {

    private Long id;
    private String orderid;
    private String foodid;
    private String option;
    private String status;
}
