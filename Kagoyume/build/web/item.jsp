<%-- 
    Document   : item
    Author     : user1
    商品詳細ページ
    ・serchまたはcartから遷移できる。商品IDをGETメソッドにより受け渡す
    ・YahooショッピングAPIから取得したデータで、より詳細な情報(概要や評価値)、が表示される
    ・「カートに追加する」ボタンがあり、クリックするとaddに遷移する。
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.ItemData"
        import="javax.servlet.http.HttpSession"%>
<%
    ItemData item = (ItemData)request.getAttribute("itemdata");    
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品詳細</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
            <div style="height: 300px">
                <img alt="<%=item.name%>" src="<%=item.bigimageurl%>" style="float: left;margin-right: 2%"><br>
                <h3 style="margin:1px"><%=item.name%></h3><br>
                <div style="font-size:small;margin-top:-1.5% "><%=item.storename%></div>
                <div>☆:<%=item.favorite%>/<%=item.reviewer%>人</div>
                <hr>
                <div style="color:red;font-weight: bold;font-size: 2em">&yen;<%=item.price%></div><br>
            <form action="Add" method="post">            
                個数:<input type="number" name="qty" value="1" style="width: 3em">個
                <input type="hidden" name="ac" value="<%=request.getAttribute("ac")%>">
                <button type="submit" name="cartin">カートに入れる</button>
            </form>
            </div>
            <div style="a{pointer-events: none}">
                <p><%=item.caption%></p>
                <p><%=item.add1%></p>
                <p><%=item.add2%></p>
                <p><%=item.add3%></p>
            </div>
            <br>
            <div align="center">
                <a href="Search?query=<%=request.getAttribute("query")%>&page=<%=request.getAttribute("page")%>">検索結果に戻る</a>
            </div>
            <br>
            <%=kagoyume.Helper.top()%>
            <br>
        </div>
        
    </body>
</html>
