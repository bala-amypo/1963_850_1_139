@Entity
@Table(name = "programs", uniqueConstraints = {@UniqueConstraint(columnNames = {"university_id", "name"})})
@Data
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    private String name;
    private String level; // UG, PG
}