<%-- 
    Document   : buycomplete
    Author     : user1
    ・購入完了ページ
    ・総購入金額を更新
    ・購入データを保存
    ・「購入が完了しました」と表示
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList"
        import="kagoyume.CartData"%>
<%
    ArrayList<CartData> cart = (ArrayList<CartData>)request.getAttribute("cart");
    int total = Integer.parseInt(request.getAttribute("total").toString());
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入管理</title>
    </head>
    <body>
        <%=kagoyume.Helper.logheader()%>
        <div class="main">
        購入が完了しました
        <ol style="padding-left:2%;padding-right: 1%">
            <%for(CartData cd : cart){%>
            <li class="pure-u-1">
                <div class="pure-u-4-5" style="font-weight: bold">
                    <a style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                        <img alt="<%=cd.getItemcode()%>" src="<%=cd.getImgurl()%>"> 
                    </a>
                    <a class="pure-u-3-5" style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                        <p><%=cd.getItemname()%></p>
                    </a>
                </div>
                <div align="right" class="pure-u-1-5" style="margin-left:-1%;margin-top: 5%;">
                    <div style="font-weight: bold;">
                        価格:<span style="color:red;">&yen;<%=cd.getPrice()%></span>
                    </div>
                </div>
                <div align="right" style="font-weight:bold;margin-top:-1em">
                    個数:<%=cd.getQuantity()%>個  小計:&yen;<%= cd.getPrice() * cd.getQuantity() %>
                </div>
                <hr>
            </li>
            <%}%>
        </ol>
        <div align="right">
            配送方法:<%=kagoyume.BuyData.typeToString(Integer.parseInt(request.getAttribute("type").toString()))%>
            <div style="font-weight: bold;font-size: large">総額:&yen;<%=total%></div><br>
        </div>
        <div align="center">
        <%=kagoyume.Helper.top()%>
        </div>
        </div>
    </body>
</html>
