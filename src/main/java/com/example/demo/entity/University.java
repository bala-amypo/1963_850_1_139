@Entity
@Data
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location; // Ensure this field exists
    private Boolean active = true;

    // Manual setter if Lombok fails
    public void setLocation(String location) {
        this.location = location;
    }
}