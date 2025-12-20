package com.example.demo.config;

import com.example.demo.entity.University;
import com.example.demo.entity.Course;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UniversityRepository universityRepo;
    private final CourseRepository courseRepo;

    // Rule 6: Constructor Injection
    public DataLoader(UniversityRepository universityRepo, CourseRepository courseRepo) {
        this.universityRepo = universityRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Create a sample University
        University univ = new University();
        univ.setName("Sample State University");
        univ.setLocation("Tech City");
        univ.setActive(true);
        universityRepo.save(univ);

        // 2. Create a sample Course for that University
        Course course = new Course();
        course.setCourseName("Advanced Java");
        course.setCourseCode("CS102");
        course.setCredits(3.0);
        course.setUniversity(univ);
        course.setActive(true);
        courseRepo.save(course);

        System.out.println(">> Database seeded with sample University and Course!");
    }
}