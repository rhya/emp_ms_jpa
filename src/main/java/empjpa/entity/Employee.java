package empjpa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/10/18.
 */
@Entity
@Table(name = "emp")
public class Employee {
    @Id @GeneratedValue(generator = "seqEmp")
    @SequenceGenerator(name = "seqEmp",sequenceName = "seq_emp",allocationSize = 1)
    private long empno;

    @Column(name = "ename", length = 10)
    private String name;

    private String job;

    @Column(name = "sal")
    private float salary;

    @Column(name = "comm")
    private Float commission;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "mgr")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> memebers;

    public Employee() {
    }

    public Employee(String name, String job, float salary, Float commission, Date hireDate, Department department) {
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.commission = commission;
        this.hireDate = hireDate;
        this.department = department;
    }

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(long empno) {
        this.empno = empno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getMemebers() {
        return memebers;
    }

    public void setMemebers(List<Employee> memebers) {
        this.memebers = memebers;
    }
}
