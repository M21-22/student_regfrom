package web;

import DAO.CourseDao;
import DAO.StudentDao;
import domain.CourseInfo;
import domain.StudentInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    private Log logger = LogFactory.getLog(getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String json = request.getParameter("dataLine");
        int action = Integer.parseInt(request.getParameter("action"));
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpSession session = request.getSession(true);
            CourseInfo crs = mapper.readValue(json,CourseInfo.class);
            new CourseDao(action, crs);

        } catch (Exception e){
            logger.info(e);
        }
    }
}

