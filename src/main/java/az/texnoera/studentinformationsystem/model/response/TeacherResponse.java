package az.texnoera.studentinformationsystem.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class TeacherResponse {
    private int id;
    private String firstName;
    private String lastName;
    private Integer userNo;
    private double salary;
    private String phoneNumber;
    private List<CourseResponseForTeacher> courses;
}
