package az.texnoera.studentinformationsystem.service;

import az.texnoera.studentinformationsystem.entity.Teacher;
import az.texnoera.studentinformationsystem.entity.User;
import az.texnoera.studentinformationsystem.model.response.CourseResponseForTeacher;
import az.texnoera.studentinformationsystem.model.response.TeacherResponse;
import az.texnoera.studentinformationsystem.model.reuqest.TeacherRequest;
import az.texnoera.studentinformationsystem.respository.CourseRepository;
import az.texnoera.studentinformationsystem.respository.TeacherRepository;
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
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public void saveTeacher(TeacherRequest teacherRequest) {
        User user  = User.builder()
                .firstName(teacherRequest.getFirstName())
                .lastName(teacherRequest.getLastName())
                .userNo(teacherRequest.getUserNo())
                .password(teacherRequest.getPassword())
                .build();
        User saveUser  = userRepository.save(user);
        Teacher teacher  = Teacher.builder().phoneNumber(teacherRequest.getPhoneNumber())
                .salary(teacherRequest.getSalary())
                .user(saveUser).build();
        teacherRepository.save(teacher);

    }

    public DataResult<TeacherResponse> getTeachers(Integer page, Integer size) {
        Pageable pages = PageRequest.of(page, size);
        Page<Teacher> teachers  = teacherRepository.findAll(pages);
        List<TeacherResponse> responseTeacher = teachers.stream().map(teacher->{
            return TeacherResponse.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getUser().getFirstName())
                    .lastName(teacher.getUser().getLastName())
                    .userNo(teacher.getUser().getUserNo())
                    .salary(teacher.getSalary())
                    .phoneNumber(teacher.getPhoneNumber())
                    .courses(teacher.getCourses().stream().map(course -> {
                        return CourseResponseForTeacher.builder()
                                .id(course.getId())
                                .name(course.getName())
                                .build();
                    }).toList())
                    .build();
                    }).toList();
       return new DataResult<>(responseTeacher,size,page,teachers.getTotalPages());
    }
}
