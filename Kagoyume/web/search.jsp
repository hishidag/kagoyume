<%-- 
    Document   : search
    Author     : user1
    検索結果ページ
    ・topから検索により遷移できる。YahooショッピングAPIに直接検索キーワードを渡し、その結果を受け取り＆表示している
    ・検索キーワード、検索結果数を表示
    ・縦のリスト型に表示。サムネイルと、その横に商品名、金額が載っている。クリックでitemへ
    ・結果は上位20件まで
--%>
<%@page session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.ItemData"
        import="java.util.*"%>
<%
    ArrayList<ItemData> array = (ArrayList<ItemData>)request.getAttribute("searchresults");
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h4>検索結果</h4>
        <b style="background-color: skyblue;">検索ワード:<%=request.getParameter("query")%> 検索結果数:<%=request.getAttribute("totalresults")%>件</b>
        <br>
        <div class="pure-g">
        <% int i = 0;%>
        <%for(ItemData item: array){%>
        <div class="pure-u-1-5">
            <div style="margin:1em">
                <a class="pure-img" href="Item?itemcode=<%=item.itemcode%>"><img src="<%=item.imageurl%>"></a><br>                
                <a href="Item?itemcode=<%=item.itemcode%>" style="float:bottom;font-size: small;font-weight: bold"><%=item.name%></a><br>
                <div style="font-size:small">☆:<%=item.favorite%>/<%=item.reviewer%>人<br></div>
                <b style="color:red">&yen;<%=item.price%></b>
            </div>
        </div>
        <%i++;%>
        <%if(i % 5 == 0){out.println("<hr>");}%>
        <%}%>
        </div>       
        <br>
        <br>
        <%=kagoyume.Helper.pages(request)%>
        <br>
        <br>
        <div align="center">
            <%=kagoyume.Helper.top()%>
        </div>
        <br>
        </div>        
    </body>
</html>
