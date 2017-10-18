package empjpa.web;

import empjpa.dao.DeptDAO;
import empjpa.dao.EmpDAO;
import empjpa.entity.Department;
import empjpa.entity.Employee;
import empjpa.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2017/10/18.
 */
@WebServlet("/all")
public class AllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = null;

        String ename = req.getParameter("ename");
        String ename2 = req.getParameter("ename2");
        String lowsal = req.getParameter("lowsal");
        String hisal = req.getParameter("hisal");

        if(CommonUtil.notempty(ename)){
            employees = new EmpDAO().queryByEname(ename);
        }else if((CommonUtil.notempty(ename2))||(CommonUtil.notempty(lowsal))||(CommonUtil.notempty(hisal))){
            employees = new EmpDAO().criteriaByConditions(ename2,lowsal,hisal);
        }else{
            employees = new EmpDAO().getAll();
        }
        List<Department> departments = new DeptDAO().getAll();

        req.setAttribute("emps",employees);
        req.setAttribute("depts",departments);

        req.getRequestDispatcher("/view/all.jsp").forward(req,resp);
    }
}
