package ua.profitsoft.studentsdockerdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Setter
@NoArgsConstructor
@Jacksonized
@Builder
@AllArgsConstructor
public class PageableSearchStudentDto {

    private String fullName;
    private Double averageGrade;
    private Long groupId;
    private Integer page;
    private Integer size;
}
