package az.texnoera.studentinformationsystem.controller;

import az.texnoera.studentinformationsystem.model.response.TeacherResponse;
import az.texnoera.studentinformationsystem.model.reuqest.TeacherRequest;
import az.texnoera.studentinformationsystem.result.DataResult;
import az.texnoera.studentinformationsystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @PostMapping
    public void saveTeacher(@RequestBody TeacherRequest teacherRequest) {
        teacherService.saveTeacher(teacherRequest);
    }
    @GetMapping
    public DataResult<TeacherResponse> getTeachers(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size) {
        return teacherService.getTeachers(page,size);
    }
}
