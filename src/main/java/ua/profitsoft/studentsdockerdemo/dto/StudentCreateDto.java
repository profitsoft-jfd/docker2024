package ua.profitsoft.studentsdockerdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateDto {

    private String name;
    private String surname;
    private Double averageGrade;
    private long groupId;
}
