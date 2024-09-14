package martapi_rest.mart.food;

import jakarta.persistence.*;

@Table(name = "foods") //database table name
@Entity(name = "foods") //represents table in database
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private Integer price;

}
