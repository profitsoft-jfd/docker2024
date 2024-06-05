package ua.profitsoft.studentsdockerdemo.dao.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.profitsoft.studentsdockerdemo.dto.PageableSearchStudentDto;
import ua.profitsoft.studentsdockerdemo.model.Student;
import java.util.List;


@Repository
public class StudentCustomRepository {

    @Autowired
    private EntityManager entityManager;

    public PaginatedResult<Student> search(PageableSearchStudentDto searchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        root.join("group");
        Predicate predicate = criteriaBuilder.conjunction();

        if (searchDto.getGroupId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("group").get("id"), searchDto.getGroupId()));
        }
        if (searchDto.getFullName() != null && !searchDto.getFullName().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("fullName"), "%" + searchDto.getFullName() + "%"));
        }
        if (searchDto.getAverageGrade() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("averageGrade"), searchDto.getAverageGrade()));
        }
        criteriaQuery.where(predicate);
        criteriaQuery.select(root);

        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
        if (searchDto.getPage() != null && searchDto.getSize() != null) {
            typedQuery.setFirstResult((searchDto.getPage()) * searchDto.getSize());
            typedQuery.setMaxResults(searchDto.getSize());
        }

        List<Student> students = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Student.class)));
        Long totalElements = entityManager.createQuery(countQuery).getSingleResult();
        if (searchDto.getSize() == null) {
            searchDto.setSize(10);
        }
        if (searchDto.getPage() == null) {
            searchDto.setPage(10);
        }
        int totalPages = (int) Math.ceil((double) totalElements / searchDto.getSize());
        return new PaginatedResult<>(students, totalElements, totalPages);
    }
}
