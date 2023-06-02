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
import java.io.PrintWriter;
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

        ObjectMapper mapper = new ObjectMapper();

        try{
            HttpSession session = request.getSession(true);

            StudentInfo st = mapper.readValue(json,StudentInfo.class);
            st.setImage(imageEncoded);
            if(action==1 || action==2){
                int[] err = new StudentValidation(st).checkValid();
                response.setContentType("application/json");
                boolean passValue;
                if(!Arrays.stream(err).anyMatch(x -> x==1)){
                    new StudentDao(action, st);
                    passValue = true;
                } else {
                    passValue = false;
                }
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("err",err);
                jsonObj.put("pass",passValue);

                PrintWriter pw = response.getWriter();
                pw.print(jsonObj);
                pw.close();
            } else if (action==3 || action==4){
                new StudentDao(action, st);
            }
        } catch (Exception e){
            logger.info(e);
        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        inputStream.close();
        return new String(bytes, "UTF-8");
    }
}

