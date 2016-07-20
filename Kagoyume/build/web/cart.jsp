<%-- 
    Document   : cart
    Author     : user1
    ・「カートに追加」でクッキーやセッションに保存された登録情報が登録古い順に表示される
    ・商品の写真と名前(リンクつき)、金額を表示。
    ・画面下部に全額の合計金額を表示する
    ・「購入する」ボタンあり
    ・各商品には「削除」のリンクあり。このリンクをクリックすることで、カートから商品を削除する
    ・カートの中身はユーザー毎に切り替えられる→カートの中身をDBに保存してログアウト後も保持できるようにする
    ・ログインしていない状態ならばログインページに遷移、
    　そこでログインに成功した場合、非ログイン状態で「カートに追加」操作をしていた商品はそのユーザー用のカートに移る

--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.CartData"
        import="java.util.ArrayList"%>
<%
    ArrayList<CartData> cart = (ArrayList<CartData>)request.getAttribute("cart");
    int total = Integer.parseInt(request.getAttribute("total").toString());
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h1>カート</h1>
        <%if(cart == null || cart.size() == 0){%>
            カートは空です。
        <%}else{%>
            <ol style="padding-left:2%;padding-right: 1%">
                <%for(CartData cd : cart){%>
                <li class="pure-u-1">
                    <div class="pure-u-4-5">
                        <a style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                            <img alt="<%=cd.getItemcode()%>" src="<%=cd.getImgurl()%>">
                        </a>
                        <a class="pure-u-4-5" style="vertical-align: middle;display: inline-block;font-weight: bold;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                            <p><%=cd.getItemname()%></p>
                        </a>
                    </div>
                    <div align="right" class="pure-u-1-5" style="margin-left: -1%;margin-top: 3%">
                        <b>価格:</b><span style="color:red;font-weight: bold;display:inline">&yen;<%=cd.getPrice()%></span><br>
                        <form action="Cart" method="post">
                            <div>
                                個数:<input type="number" name="qty" value="<%=cd.getQuantity()%>" style="width: 3em">個
                            </div>
                            <input type="hidden" name="changeqty" value="<%=cd.getItemcode()%>">
                            <button type="submit" name="change">変更</button>
                            <input type="hidden" name="deleteitem" value="<%=cd.getItemcode()%>">
                            <button type="submit" name="delete">削除</button>
                        </form>
                    </div>                    
                    <div align="right" style="font-weight: bold;margin-top: -1em">小計:&yen;<%=cd.getQuantity() * cd.getPrice()%></div>
                <hr>
                </li>
                <%}%>
            </ol>
            <div align="right">
                <div style="font-weight: bold;font-size: large">総額:&yen;<%=total%></div><br>
                <form>
                    <button type="submit" name="alldelete">カートを空にする</button>
                </form>
                <br>
                <form action="Buyconfirm">
                    <button type="submit">購入</button>
                </form>                
            </div>    
        <%}%>     
        </div>
    </body>
</html>
