package ua.profitsoft.studentsdockerdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.profitsoft.studentsdockerdemo.model.Group;
import ua.profitsoft.studentsdockerdemo.service.GroupService;
import java.util.List;


@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    private List<Group> getAll() {
        return groupService.findAllDistinct();
    }
}
