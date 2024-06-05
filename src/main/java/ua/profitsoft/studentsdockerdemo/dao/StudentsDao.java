package ua.profitsoft.studentsdockerdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.profitsoft.studentsdockerdemo.model.Student;

@Repository
public interface StudentsDao extends JpaRepository<Student, Long> {

    Student findByNameAndSurname(String name, String surname);
}
