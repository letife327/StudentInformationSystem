package az.texnoera.studentinformationsystem.model.response;

import az.texnoera.studentinformationsystem.entity.Teacher;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Builder
@Data
public class CourseResponse {
    private int id;
    private String name;
    private Set<Teacher> teachers;
}
