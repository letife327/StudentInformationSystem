package az.texnoera.studentinformationsystem.model.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentResponse {
    private int id;
    private String firstName;
    private String lastName;
    private Integer userNo;
    private String password;
    private double scholarShip;
    private CourseResponse courseResponse;
}
