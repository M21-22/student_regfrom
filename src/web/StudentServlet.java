package web;

import DAO.StudentDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.StudentInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import validation.StudentValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@WebServlet("/students")
@MultipartConfig
public class StudentServlet extends HttpServlet {
    private Log logger = LogFactory.getLog(getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int action = Integer.parseInt(convertInputStreamToString(request.getPart("action").getInputStream()));
        String json = convertInputStreamToString(request.getPart("dataLine").getInputStream());
        String imageEncoded = convertInputStreamToString(request.getPart("image").getInputStream());
        //        int tableId = Integer.parseInt(request.getParameter("tableId"));
//        String base64Image = request.getParameter("imageData");

        ObjectMapper mapper = new ObjectMapper();

        try{
            HttpSession session = request.getSession(true);

            StudentInfo st = mapper.readValue(json,StudentInfo.class);
            st.setImage(imageEncoded);
            int[] err = new StudentValidation(st).checkValid();
            response.setContentType("application/json");
            boolean value;

            if(!Arrays.stream(err).anyMatch(x -> x==1)){
                new StudentDao(action, st);
                value = true;
            } else{
                value = false;
            }
            System.out.println(value);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("err",err);
            jsonObj.put("pass",value);
            response.getWriter().print(jsonObj);
        } catch (Exception e){
            logger.info(e);
        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return new String(bytes, "UTF-8");
    }
}

