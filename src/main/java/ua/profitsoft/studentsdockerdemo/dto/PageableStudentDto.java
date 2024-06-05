package ua.profitsoft.studentsdockerdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
@Getter
@Setter
public class PageableStudentDto {

    private List<StudentDto> studentDtos;
    private Integer page;
    private Integer size;
    private Long totalElement;
    private Integer totalPages;
}
