package web;

import DAO.StudentDao;
import domain.StudentInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/edit")
public class EditDataServlet extends HttpServlet {
    private Log logger = LogFactory.getLog(getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int tableId = Integer.parseInt(request.getParameter("tableId"));
        String json = request.getParameter("dataLine");
        ObjectMapper mapper = new ObjectMapper();

        try{
            HttpSession session = request.getSession(true);
            if(tableId==1){
                StudentInfo st = mapper.readValue(json,StudentInfo.class);
//                new StudentDao().editData(st);
            } else if(tableId==2){

            }
//            response.setContentType("application/json");
//            PrintWriter out = response.getWriter();
//            out.print(data.toString());

        } catch (Exception e){
            logger.info(e);
        }
    }
}

