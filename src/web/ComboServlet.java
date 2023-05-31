package web;

import DAO.GetDataDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/combo")
public class ComboServlet extends HttpServlet {
    private Log logger = LogFactory.getLog(getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetDataDao dataDao = new GetDataDao();
        int tableId = Integer.parseInt(request.getParameter("tableId"));
        try{
            HttpSession session = request.getSession(true);
            JSONArray data = dataDao.getData(tableId);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(data.toString());
        } catch (Exception e){
            logger.info(e);
        }
    }
}
