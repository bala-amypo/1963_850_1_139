@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    private String courseCode;
    private String courseName;
    private Double credits; // MUST be 'credits' to match DataLoader
    private String description;
    private Boolean active = true;

    // Manual setter if Lombok fails
    public void setCredits(Double credits) {
        this.credits = credits;
    }
    
    public Double getCredits() {
        return this.credits;
    }
}