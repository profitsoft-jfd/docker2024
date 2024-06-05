package ua.profitsoft.studentsdockerdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    private Long id;
    private String name;
    private String surname;
    private String fullName;
    private Double averageGrade;
    private Long groupId;
    private String groupName;
}
