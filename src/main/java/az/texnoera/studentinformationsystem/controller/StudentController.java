package az.texnoera.studentinformationsystem.controller;

import az.texnoera.studentinformationsystem.model.response.StudentResponse;
import az.texnoera.studentinformationsystem.model.reuqest.StudentRequest;
import az.texnoera.studentinformationsystem.result.DataResult;
import az.texnoera.studentinformationsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    public void saveStudent(@RequestBody StudentRequest studentRequest) {
        studentService.saveStudent(studentRequest);
    }
    @GetMapping
    public DataResult<StudentResponse> getAllStudents(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer size) {
        return studentService.getAllStudents( page,size);

    }

}
