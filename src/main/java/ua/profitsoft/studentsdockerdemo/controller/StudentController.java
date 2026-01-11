package ua.profitsoft.studentsdockerdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.profitsoft.studentsdockerdemo.dto.PageableSearchStudentDto;
import ua.profitsoft.studentsdockerdemo.dto.PageableStudentDto;
import ua.profitsoft.studentsdockerdemo.dto.StudentCreateDto;
import ua.profitsoft.studentsdockerdemo.dto.StudentDto;
import ua.profitsoft.studentsdockerdemo.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public PageableStudentDto findAllStudents(@RequestParam(required = false, defaultValue = "0") int from,
                                              @RequestParam(required = false, defaultValue = "5") int size) {
        return studentService.findAll(from, size);
    }

    @GetMapping("/{studentId}")
    public StudentDto findStudent(@PathVariable Long studentId) {
        return studentService.findStudentById(studentId);
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentCreateDto createDto) {
        return studentService.createStudent(createDto);
    }

    @PutMapping("/{studentId}")
    public StudentDto updateStudentDto(@RequestBody StudentCreateDto createDto, @PathVariable Long studentId) {
        return studentService.updateStudent(createDto, studentId);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteById(id);
    }


    @PostMapping("/_search")
    public PageableStudentDto search(@RequestBody PageableSearchStudentDto searchDto) {
        return studentService.search(searchDto);
    }
}
