package ua.profitsoft.studentsdockerdemo.dao.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResult<T> {
    private List<T> resultList;
    private Long totalCount;
    private Integer totalPages;

    public PaginatedResult(List<T> resultList) {
        this.resultList = resultList;
    }
}
