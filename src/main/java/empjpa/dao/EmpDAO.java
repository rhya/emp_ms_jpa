package empjpa.dao;

import empjpa.entity.Department;
import empjpa.entity.Employee;
import empjpa.util.CommonUtil;
import empjpa.util.EMUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by admin on 2017/10/18.
 */
public class EmpDAO extends BaseDAO<Employee>{

    private EntityManager entityManager;


    ///  输入名字模糊查询
    public List<Employee> queryByEname(String ename) {
        entityManager = EMUtil.getEntityManager();

        String jpql = "from Employee where name like :name";
        List<Employee> employees = entityManager.createQuery(jpql, Employee.class)
                .setParameter("name", ename.toUpperCase() + "%")
                .getResultList();
        entityManager.close();
        return employees;
    }

    //  根据用户输入的多个条件进行查询
    public List<Employee> criteriaByConditions(String ename2, String lowsal, String hisal) {
        entityManager = EMUtil.getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Employee.class);

        List<Employee> employees;
        if(CommonUtil.notempty(ename2))
            criteria.add(Restrictions.like("name",ename2.toUpperCase()+"%"));
        if(CommonUtil.notempty(lowsal))
            criteria.add(Restrictions.ge("salary",Float.parseFloat(lowsal)));
        if(CommonUtil.notempty(hisal))
            criteria.add(Restrictions.le("salary",Float.parseFloat(hisal)));
        employees = criteria.addOrder(Order.desc("salary")).list();

        entityManager.close();
        return employees;
    }

    // 获取所有员工信息
    public List<Employee> getAll() {
        entityManager = EMUtil.getEntityManager();
        List<Employee> employees = entityManager.createQuery("from Employee ",Employee.class).getResultList();
        entityManager.close();
        return employees;
    }

    public Employee getById(long empno){
        entityManager = EMUtil.getEntityManager();
        Employee employee = entityManager.find(Employee.class, empno);
        entityManager.close();

        return employee;
    }

    public void persist(Employee employee){
        entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Department department = entityManager.find(Department.class,
                employee.getDepartment().getDeptno());
        employee.setDepartment(department);
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(Long along){
        entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Employee.class,along));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
