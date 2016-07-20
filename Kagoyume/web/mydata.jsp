<%-- 
    Document   : mydata
    Author     : user1
    ・登録したユーザー情報が閲覧できる(ユーザーID以外全て)
    ・購入履歴へのリンクあり
    ・登録情報を更新する、削除するボタンあり
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.UserData"%>
<% UserData ud = (UserData)request.getAttribute("ud");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>アカウント情報確認</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h1>アカウント情報</h1><br>
        <table class="pure-table pure-table-bordered">
        <tr>
            <td style="font-weight: bold">名前:</td>
            <td><%=ud.getName()%></td>
        </tr>
        <tr>
            <td style="font-weight: bold">メールアドレス:</td>
            <td><%=ud.getMail()%></td>
        </tr>
        <tr>
            <td style="font-weight: bold">住所:</td>
            <td><%=ud.getAddress()%></td>
        </tr>
        <tr>
            <td style="font-weight: bold">総額:</td>
            <td>&yen;<%=ud.getTotal()%></td>
        </tr>
        <tr>
            <td style="font-weight: bold">登録日:</td>
            <td><%=ud.getNewdate()%></td>
        </tr>        
        </table>
        <br>
        <a href="Myupdate">登録内容を変更する</a><br>
        <br>
        <a href="Mydelete">アカウントを削除する</a><br>
        <br>
        <a href="Myhistory">購入履歴を見る</a>
        </div>
    </body>
</html>
