package az.texnoera.studentinformationsystem.controller;

import az.texnoera.studentinformationsystem.entity.Course;
import az.texnoera.studentinformationsystem.model.response.CourseResponse;
import az.texnoera.studentinformationsystem.model.reuqest.AddStudentRequest;
import az.texnoera.studentinformationsystem.model.reuqest.AddTeacherRequest;
import az.texnoera.studentinformationsystem.model.reuqest.CourseRequest;
import az.texnoera.studentinformationsystem.model.reuqest.TeacherRequest;
import az.texnoera.studentinformationsystem.result.DataResult;
import az.texnoera.studentinformationsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public void saveCourse(@RequestBody CourseRequest courseRequest) {
        courseService.saveCourse(courseRequest);
    }
    @PostMapping("/{id}/add-teacher")
    public void addTeacher(@PathVariable Integer id, @RequestBody AddTeacherRequest addTeacherRequest) {
        courseService.addTeacher(id,addTeacherRequest);
    }
    @PostMapping("/{id}/add-student")
    public void addStudent(@PathVariable Integer id, @RequestBody AddStudentRequest addStudentRequest) {
        courseService.addStudent(id,addStudentRequest);
    }

    @GetMapping
    public DataResult<CourseResponse> getAllCourses(@RequestParam(defaultValue = "10") Integer size,
                                                    @RequestParam(defaultValue = "0" ) Integer page) {
        return courseService.getAllCourses(size,page);
    }
    @DeleteMapping("/{course-id}/teachers/{teacher-id}")
    public void deleteTeachersFromCourse(@PathVariable("course-id") Integer courseId
                            ,@PathVariable("teacher-id") Integer teacherId) {
        courseService.deleteTeachersFromCourse(courseId,teacherId);
    }
    @DeleteMapping("/{course-id}/students/{student-id}")
    public void deleteStudentsFromCourse(@PathVariable("course-id") Integer courseId
            ,@PathVariable("student-id") Integer studentId) {
        courseService.deleteStudentsFromCourse(courseId,studentId);
    }

}
