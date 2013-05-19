package ed;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dhaniel
 * Date: 4/27/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendSMS extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        Encrypt encrypt = new Encrypt();
        String sms = request.getParameter("sms");
        String signature = encrypt.encrypt(sms.getBytes());

        request.setAttribute("signature", signature);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showSMS.jsp");
        dispatcher.forward(request, response);
    }
}
