@Service
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ Test cases expect exact field name
    private UniversityRepository repository;

    // ✅ REQUIRED: no-args constructor (used by TestNG)
    public UniversityServiceImpl() {
    }

    // ✅ REQUIRED: Spring DI constructor
    @org.springframework.beans.factory.annotation.Autowired
    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {

        // test31: invalid name
        if (university == null ||
            university.getName() == null ||
            university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name required");
        }

        // test03: duplicate name
        if (repository.findByName(university.getName()).isPresent()) {
            throw new IllegalArgumentException("exists");
        }

        university.setActive(true);
        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {
        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (university.getName() != null) {
            existing.setName(university.getName());
        }
        if (university.getCountry() != null) {
            existing.setCountry(university.getCountry());
        }
        if (university.getAccreditationLevel() != null) {
            existing.setAccreditationLevel(university.getAccreditationLevel());
        }

        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University uni = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        uni.setActive(false);
        repository.save(uni);
    }
}
