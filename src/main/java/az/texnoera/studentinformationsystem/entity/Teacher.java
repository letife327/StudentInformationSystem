package az.texnoera.studentinformationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int salary;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(mappedBy = "teachers")
    Set<Course> courses = new HashSet<>();


}