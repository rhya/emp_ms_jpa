package empjpa.web;

import empjpa.dao.EmpDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/10/18.
 */
@WebServlet("/empdel")
public class EmpDelServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empno = req.getParameter("empno");

        new EmpDAO().remove(Long.valueOf(empno));

        resp.sendRedirect("/all");
    }
}
