package az.texnoera.studentinformationsystem.model.reuqest;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private Integer userNo;
    private String password;
    private double scholarShip;
}
