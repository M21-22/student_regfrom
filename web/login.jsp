<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/12/2023
  Time: 4:20 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="web.Constant" %>

<%
  String errorMessage = (String) request.getSession().getAttribute(Constant.MESSAGE);
%>
<html>
<head>
  <meta charset="utf-8">
  <title>Login</title>
  <link rel="stylesheet" href="/login.css">
</head>
<body onload>
<h1>Login</h1>
<form action="login?action=login" method="post">
  <table style="width: 100%">
    <tbody><tr>
      <td>UserName</td>
      <td><input type="text" id="username" name="username" required=""></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" id="password" name="password" required=""></td>
    </tr>

    </tbody></table>
  <input type="submit" value="Submit">
  <p><%= errorMessage!=null ? errorMessage : "" %></p>
</form>


</body>
</html>
