package ed;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dhaniel
 * Date: 5/12/13
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class CekSMS extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Encrypt encrypt = new Encrypt();
        String sms = request.getParameter("sms");
        String signature = encrypt.encrypt(sms.getBytes());
        Boolean cekSMS = encrypt.checkSignature(sms.getBytes(), signature.getBytes());

        request.setAttribute("signature", signature);
        request.setAttribute("cekSMS", cekSMS);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cekSMS.jsp");
        dispatcher.forward(request, response);
    }
}
