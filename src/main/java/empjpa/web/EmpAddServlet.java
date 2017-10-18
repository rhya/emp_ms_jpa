package empjpa.web;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addEmp")
public class EmpAddServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 0 . 设置编码
        req.setCharacterEncoding("UTF-8");

        // 1.   获取参数
        String ename = req.getParameter("ename");
        String deptno = req.getParameter("deptno");
        String sal = req.getParameter("sal");
        String hireDate = req.getParameter("hire_date");

        // 2.   验证输入
        // hireDate 时间类型的字符串，如果写错了话，就不正确
        if(ename == null || ename.trim().isEmpty()){
            //用户名不能为空
            resp.sendRedirect("/error.jsp");
        }else if(ename.trim().length() <= 10){
            /// 用户名必须要大于 10
        }
        // ......

        // 3.   封装
        Employee employee = new Employee();
        if(CommonUtil.notempty(ename)){
            employee.setName(ename);
        }
        if(CommonUtil.notempty(sal)){
            employee.setSalary(Float.parseFloat(sal));
        }
        if(CommonUtil.notempty(hireDate)){
            SimpleDateFormat sdf = new SimpleDateFormat(hireDate.indexOf("/") > 0 ? "yyyy/MM/dd" : "yyyy-MM-dd");
            Date date = null;

            try {
                date = sdf.parse(hireDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            employee.setHireDate(date);
        }
        Department dept = new Department();
        dept.setDeptno(Long.parseLong(deptno));
        employee.setDepartment(dept);

        // 4.   调用，取得数据
        new EmpDAO().persist(employee);




    }
}
