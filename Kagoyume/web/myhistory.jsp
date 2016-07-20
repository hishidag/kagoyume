<%-- 
    Document   : myhistory
    Author     : user1
    ・これまで購入した商品の履歴が見れる
--%>

<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.*"
        import="kagoyume.BuyData"%>
<%
    LinkedHashMap<String,ArrayList<BuyData>> map = (LinkedHashMap<String,ArrayList<BuyData>>)request.getAttribute("buydata");
    int alltotal = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入履歴</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h1>購入履歴</h1>
        <%if(map.size() == 0){%>
            購入履歴はありません
        <%}else{%>
        <ol style="list-style: none;">            
            <%for(String buytime : map.keySet()){                
                ArrayList<BuyData> array = map.get(buytime);
                int total = 0;
            %>
            <li><h2>購入日時:<%=buytime%>  配送方法:<%=array.get(0).getType()%></h2></li>
                <%for(BuyData bd : array){%>
                <li class="pure-u-1">
                    <div class="pure-u-4-5" style="font-weight: bold">
                        <a style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=bd.getItemcode()%>">
                            <img alt="<%=bd.getItemcode()%>" src="<%=bd.getImgurl()%>"> 
                        </a>
                        <a class="pure-u-3-5" style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=bd.getItemcode()%>">
                            <p><%=bd.getItemname()%></p>
                        </a>
                    </div>
                    <div align="right" class="pure-u-1-5" style="margin-left:-1%;margin-top: 5%;">
                        <div style="font-weight: bold;">
                            価格:<span style="color:red;">&yen;<%=bd.getPrice()%></span>
                        </div>
                    </div>
                    <div align="right" style="font-weight:bold;margin-top:-1em">
                        <%
                            int subtotal = bd.getPrice() * bd.getQuantity();
                            total += subtotal;
                            alltotal += subtotal;
                        %>
                        個数:<%=bd.getQuantity()%>個  小計:&yen;<%=subtotal%>
                    </div>
                    <hr style="border-color:black">
                </li>            
                <%}%>
                <%="<div align=\"right\" style=\"font-weight:bold;font-size:large\">合計:&yen;" + total + "</div>"%>
            <%}%>
        </ol>
        <%}%>
        <div align="middle">
            <h1>これまでの合計金額</h1><br>
            <h1>&yen;<%=alltotal%></h1>
        </div>
        </div>
    </body>
</html>
