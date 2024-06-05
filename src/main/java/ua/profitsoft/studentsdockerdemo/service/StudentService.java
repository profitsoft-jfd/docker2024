package ua.profitsoft.studentsdockerdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.profitsoft.studentsdockerdemo.dao.StudentsDao;
import ua.profitsoft.studentsdockerdemo.dao.custom.PaginatedResult;
import ua.profitsoft.studentsdockerdemo.dao.custom.StudentCustomRepository;
import ua.profitsoft.studentsdockerdemo.dto.PageableSearchStudentDto;
import ua.profitsoft.studentsdockerdemo.dto.StudentCreateDto;
import ua.profitsoft.studentsdockerdemo.dto.StudentDto;
import ua.profitsoft.studentsdockerdemo.exception.EntityNotFoundException;
import ua.profitsoft.studentsdockerdemo.model.Student;
import ua.profitsoft.studentsdockerdemo.dto.PageableStudentDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private StudentCustomRepository studentCustomRepository;

    @Autowired
    private GroupService groupService;

    public StudentDto findStudentById(long studentId) {
        return convertToDto(getByIdOrThrow(studentId));
    }

    public void saveAll(List<Student> students) {
        studentsDao.saveAll(students);
    }

    public StudentDto updateStudent(StudentCreateDto createDto, Long id) {
        Student existing = getByIdOrThrow(id);
        existing.setName(createDto.getName());
        existing.setSurname(createDto.getSurname());
        updateStudent(existing, createDto);
        return convertToDto(studentsDao.save(existing));
    }

    private void updateStudent(Student student, StudentCreateDto dto) {
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setAverageGrade(dto.getAverageGrade());
        student.setGroup(groupService.findByIdOrThrow(dto.getGroupId()));
    }

    public StudentDto createStudent(StudentCreateDto createDto) {
        Student student = convertFromCreateDto(createDto);
        return convertToDto(studentsDao.save(student));
    }

    public void deleteById(long id) {
        studentsDao.deleteById(id);
    }

    private Student convertFromCreateDto(StudentCreateDto createDto) {
        Student student = new Student();
        student.setName(createDto.getName());
        student.setSurname(createDto.getSurname());
        student.setAverageGrade(createDto.getAverageGrade());
        student.setGroup(groupService.findByIdOrThrow(createDto.getGroupId()));
        return student;
    }

    public PageableStudentDto findAll(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        Page<Student> page = studentsDao.findAll(pageable);
        List<StudentDto> studentDtoList = page.getContent().stream()
                .map(this::convertToDto)
                .toList();
        return PageableStudentDto.builder()
                .studentDtos(studentDtoList)
                .totalElement(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .size(page.getSize())
                .build();
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setFullName(student.getFullName());
        studentDto.setAverageGrade(student.getAverageGrade());
        studentDto.setGroupId(student.getGroup().getId());
        studentDto.setGroupName(student.getGroup().getName());
        return studentDto;
    }

    private Student getByIdOrThrow(Long id) {
        return studentsDao.findById(id).orElseThrow(() -> new EntityNotFoundException("No students found with id " + id));
    }

    public PageableStudentDto search(PageableSearchStudentDto searchDto) {
        PaginatedResult<Student> paginatedResult = studentCustomRepository.search(searchDto);
        List<StudentDto> dtos = paginatedResult.getResultList().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return PageableStudentDto.builder()
                .studentDtos(dtos)
                .totalElement(paginatedResult.getTotalCount())
                .totalPages(paginatedResult.getTotalPages())
                .page(searchDto.getPage())
                .size(searchDto.getSize())
                .build();
    }
}
