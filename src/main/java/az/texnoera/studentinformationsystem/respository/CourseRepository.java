package az.texnoera.studentinformationsystem.respository;

import az.texnoera.studentinformationsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
