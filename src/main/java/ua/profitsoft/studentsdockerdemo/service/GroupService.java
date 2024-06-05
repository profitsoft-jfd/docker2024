package ua.profitsoft.studentsdockerdemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.profitsoft.studentsdockerdemo.dao.GroupDao;
import ua.profitsoft.studentsdockerdemo.exception.EntityNotFoundException;
import ua.profitsoft.studentsdockerdemo.model.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    public void saveAll(List<Group> groups) {
        groupDao.saveAll(groups);
    }

    public Group findByIdOrThrow(long id) {
        return groupDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " not found"));
    }

    public List<Group> findAllDistinct() {
        List<Group> allGroups = groupDao.findAll();
        return new ArrayList<>(allGroups.stream()
                .collect(Collectors.toMap(Group::getName, group -> group, (existing, replacement) -> existing))
                .values());
    }

}
