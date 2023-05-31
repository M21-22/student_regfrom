package web;

import domain.UsersInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class LoginServlet extends HttpServlet {
    Log logger = LogFactory.getLog(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = Constant.PAGE_MAIN;
        boolean forward = false;
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        UserService userService = new UserServiceImpl();
        String ACTION = "action";

        if (request.getParameter("action") != null) {
            ACTION = request.getParameter("action");
        }

        try {
            HttpSession session = request.getSession(true);

            if (ACTION.equals(Constant.ACTION_LOGIN)) {
                String realIP = request.getHeader("X-Real-IP");
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                String tomcatIp = request.getLocalAddr();

                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                if (!ipAddress.equals(realIP)) {
                    ipAddress = ipAddress + "----" + realIP;
                }

                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                logger.info("Login Process -login IP=" + ipAddress + "- Username" + userName);
                UsersInfo user = userService.getUser(0L, userName, password);

                if (user.getId() !=0) {
                    session = request.getSession(true);
                    session.setAttribute("sesID", session.getId());
                    session.setAttribute(Constant.USER, user);

                    address = Constant.PAGE_MAIN;
                    forward = false;
                } else {
                    logger.info("Invalid username or password");
                    address = Constant.PAGE_LOGIN;
                    request.getSession().setAttribute(Constant.MESSAGE, "İstifadəçi adı və ya şifrə səhvdir");
                    forward = false;
                }
            }
        }
        catch (Exception ex) {
            logger.error(" Error in loginservlete action login" + ex.toString());
            forward = true;
            address = Constant.PAGE_LOGIN;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
            logger.info("main page redirect login success");
            response.sendRedirect(response.encodeRedirectURL(address));
        }
    }

}
