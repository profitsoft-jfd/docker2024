package ua.profitsoft.studentsdockerdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private double averageGrade;

    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_group")
    private Group group;

    public Student(String name, String surname, double averageGrade, Group group) {
        this.name = name;
        this.surname = surname;
        this.averageGrade = averageGrade;
        this.group = group;
    }

    @PrePersist
    public void prePersist() {
        fullName = name + " " + surname;
    }
}
