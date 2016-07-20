<%-- 
    Document   : add
    Author     : user1    
    カートに追加ページ
    ・商品の情報を受け取り、クッキーやセッションに追加する
    ・画面には「カートに追加しました」という文言が出てくる。
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.ItemData"%>
<%
    ItemData item = (ItemData)request.getAttribute("itemdata");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート追加</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h1>カートに追加しました</h1>
        <%=item.name%><%=request.getAttribute("qty")%>個<br>
        価格:<div style="color:red;font-weight: bold;display: inline-block">&yen;<%=item.price%></div>
        </div>
    </body>
</html>
