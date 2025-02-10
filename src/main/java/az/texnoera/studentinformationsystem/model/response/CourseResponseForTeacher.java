package az.texnoera.studentinformationsystem.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourseResponseForTeacher {
    private int id;
    private String name;
}
