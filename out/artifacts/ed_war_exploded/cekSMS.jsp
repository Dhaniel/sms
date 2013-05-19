<%--
  Created by IntelliJ IDEA.
  User: dhaniel
  Date: 4/27/13
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<% String sms = request.getParameter("sms"); %>
<% String signature = request.getParameter("signature"); %>
<% String cekSMS = request.getParameter("cekSMS"); %>

<span><% System.out.print(sms); %></span>
<% if(!cekSMS.isEmpty()) {
    System.out.print("Verify");
}else {
    System.out.print(" failed ");
} %>

</form>
</body>
</html>