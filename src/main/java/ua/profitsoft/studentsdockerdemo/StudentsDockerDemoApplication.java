package ua.profitsoft.studentsdockerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.profitsoft.studentsdockerdemo.model.Group;
import ua.profitsoft.studentsdockerdemo.model.Student;
import ua.profitsoft.studentsdockerdemo.service.GroupService;
import ua.profitsoft.studentsdockerdemo.service.StudentService;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class StudentsDockerDemoApplication implements CommandLineRunner {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(StudentsDockerDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Group group = new Group("group" + i);
            groups.add(group);
        }
        groupService.saveAll(groups);

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student("StdName " + i, "stdSurname" + i, i, groups.get(i));
            students.add(student);
        }
        studentService.saveAll(students);
    }
}
