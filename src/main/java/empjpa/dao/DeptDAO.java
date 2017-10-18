package empjpa.dao;

import empjpa.entity.Department;
import empjpa.util.EMUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class DeptDAO extends BaseDAO<Department> {
    public Department getById(long deptno) {
        EntityManager em = EMUtil.getEntityManager();
        Department department = em.find(Department.class, deptno);
        department.getEmployees().size();
        em.close();
        return department;
    }

    public List<Department> getAll(){
        EntityManager em = EMUtil.getEntityManager();
        List<Department> departments = em.createQuery("from Department ", Department.class).getResultList();
        em.close();
        return departments;
    }

}
