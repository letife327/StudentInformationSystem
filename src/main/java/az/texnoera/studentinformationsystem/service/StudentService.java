package az.texnoera.studentinformationsystem.service;

import az.texnoera.studentinformationsystem.entity.Student;
import az.texnoera.studentinformationsystem.entity.User;
import az.texnoera.studentinformationsystem.model.response.CourseResponse;
import az.texnoera.studentinformationsystem.model.response.StudentResponse;
import az.texnoera.studentinformationsystem.model.reuqest.StudentRequest;
import az.texnoera.studentinformationsystem.respository.StudentRepository;
import az.texnoera.studentinformationsystem.respository.UserRepository;
import az.texnoera.studentinformationsystem.result.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public void saveStudent(StudentRequest studentRequest) {
        User user = User.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .password(studentRequest.getPassword())
                .userNo(studentRequest.getUserNo())
                .build();

        User savedUser = userRepository.save(user);

        Student student = Student.builder()
                .scholarShip(studentRequest.getScholarShip())
                .user(savedUser).build();

        studentRepository.save(student);
    }

    public DataResult<StudentResponse> getAllStudents(Integer page, Integer size) {
        Pageable pages  = PageRequest.of(page, size);
        Page<Student> students  = studentRepository.findAll(pages);
       List<StudentResponse> studentResponse =  students.stream().map(student ->{
            return StudentResponse.builder()
                    .id(student.getId())
                    .firstName(student.getUser().getFirstName())
                    .lastName(student.getUser().getLastName())
                    .password(student.getUser().getPassword())
                    .userNo(student.getUser().getUserNo())
                    .scholarShip(student.getScholarShip())
                    .courseResponse(CourseResponse.builder()
                            .id(student.getCourse().getId())
                            .name(student.getCourse().getName()).build())
                    .build();
               }).toList();
        return new DataResult<>(studentResponse,page,size,students.getTotalPages());
    }
}
