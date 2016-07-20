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
        <div style="height: 300px">
                <img alt="<%=item.name%>" src="<%=item.imageurl%>" style="float: left;margin-right: 2%"><br>
                <h3 style="margin:1px"><%=item.name%></h3><br>
                <div style="font-size:small;margin-top:-1.5% "><%=item.storename%></div>
                <div style="font-weight: bold;">
                    <span style="color: red">&yen;<%=item.price%></span>
                    個数:<%=request.getAttribute("qty")%>個</div>
                <br>
        </div>
            <div align="center">
                <a href="Search?query=<%=request.getAttribute("query")%>&page=<%=request.getAttribute("page")%>">検索結果に戻る</a>
            </div>
            <br>
            <%=kagoyume.Helper.top()%>
        </div>
    </body>
</html>
