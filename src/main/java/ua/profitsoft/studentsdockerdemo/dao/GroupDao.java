package ua.profitsoft.studentsdockerdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.profitsoft.studentsdockerdemo.model.Group;

@Repository
public interface GroupDao extends JpaRepository<Group, Long> {
}
