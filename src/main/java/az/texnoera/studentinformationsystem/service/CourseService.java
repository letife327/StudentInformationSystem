package az.texnoera.studentinformationsystem.service;

import az.texnoera.studentinformationsystem.entity.Course;
import az.texnoera.studentinformationsystem.entity.Student;
import az.texnoera.studentinformationsystem.entity.Teacher;
import az.texnoera.studentinformationsystem.model.response.CourseResponse;
import az.texnoera.studentinformationsystem.model.reuqest.AddStudentRequest;
import az.texnoera.studentinformationsystem.model.reuqest.AddTeacherRequest;
import az.texnoera.studentinformationsystem.model.reuqest.CourseRequest;
import az.texnoera.studentinformationsystem.respository.CourseRepository;
import az.texnoera.studentinformationsystem.respository.StudentRepository;
import az.texnoera.studentinformationsystem.respository.TeacherRepository;
import az.texnoera.studentinformationsystem.result.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public void saveCourse(CourseRequest courseRequest) {
        Course course = Course.builder()
                .name(courseRequest.getName())
                .build();
        courseRepository.save(course);
    }

    public void addTeacher(Integer courseId, AddTeacherRequest addTeacherRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Teacher teacher = teacherRepository.findById(addTeacherRequest.getTeacherId()).orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        course.getTeachers().add(teacher);
        courseRepository.save(course);

    }

    public void addStudent(Integer id, AddStudentRequest addStudentRequest) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Student student = studentRepository.findById(addStudentRequest.getStudentId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        student.setCourse(course);
        studentRepository.save(student);
    }

    public DataResult<CourseResponse> getAllCourses(Integer size, Integer page) {
        Pageable pages = PageRequest.of(page, size);
        Page<Course> pageCourse = courseRepository.findAll(pages);
        List<CourseResponse> courseResponse = pageCourse.stream().map(course -> {
            return CourseResponse.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .teachers(course.getTeachers())
                    .build();
        }).toList();
        return new DataResult<>(courseResponse, size, page, pageCourse.getTotalPages());
    }

    public void deleteTeachersFromCourse(Integer id, Integer teacherId) {
        Course course  =  courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
     course.getTeachers().stream().filter(teacher -> teacher.getId()==teacherId).map(teacher -> {
            return course.getTeachers().remove(teacher);
        });
     courseRepository.save(course);
    }

    public void deleteStudentsFromCourse(Integer courseId, Integer studentId) {
    }
}
