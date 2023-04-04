package food.domain;

import food.domain.Rejected;
import food.domain.CookStarted;
import food.domain.CookFinished;
import food.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Cooking_table")
@Data

public class Cooking  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String orderid;
    
    
    
    
    
    private String foodid;
    
    
    
    
    
    private String option;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){


        Rejected rejected = new Rejected(this);
        rejected.publishAfterCommit();


        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.




        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();



        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();

    }

    public static CookingRepository repository(){
        CookingRepository cookingRepository = StoreApplication.applicationContext.getBean(CookingRepository.class);
        return cookingRepository;
    }

    public void accept or reject(){
        //
    }


    public void acceptorreject(){
        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        food.external.Cooking cooking = new food.external.Cooking();
        // mappings goes here
        StoreApplication.applicationContext.getBean(food.external.CookingService.class)
            .start(cooking);

    }
    public void start(){
    }
    public void finish(){
    }

    public static void loadeOrderInfo(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

        
    }


}
