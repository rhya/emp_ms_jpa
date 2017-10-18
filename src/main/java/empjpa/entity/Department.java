package empjpa.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/10/18.
 */
@Entity
@Table(name = "dept")
public class Department {
    @Id @GeneratedValue(generator = "seqDept")
    @SequenceGenerator(name = "seqDept",sequenceName = "seq_dept",allocationSize = 1)
    private long deptno;

    @Column(name = "dname")
    private String name;

    @Column(name = "loc")
    private String location;

    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public long getDeptno() {
        return deptno;
    }

    public void setDeptno(long deptno) {
        this.deptno = deptno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
