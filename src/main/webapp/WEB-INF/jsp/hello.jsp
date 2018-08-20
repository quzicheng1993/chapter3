<%@ page pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Hello</title>
    </head>

    <body>
    <%
        Object current =  request.getAttribute("currentTime");
    %>
        <h1><%=current%></h1>

        <h1>Hello!</h1>
        <h2>当前时间：${currentTime}</h2>
     </body>
</html>