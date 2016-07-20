<%-- 
    Document   : error
    Author     : user1
    各種エラーを表示するページです
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>エラー</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        エラーが発生しました。以下をご確認ください<br>
        <%=request.getAttribute("error")%>
        </div>
    </body>
</html>
