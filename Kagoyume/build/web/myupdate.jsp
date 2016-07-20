<%-- 
    Document   : myupdate
    Author     : user1
    ・フォームから入力するデータで既にあるデータを更新できる
    ・画面構成はregistrationと同じ。フォーム内に直接記入された状態である。
    　このフォームの内容を書き換えていくことでデータの更新ができる
    ・送信ボタン付き
--%>
<%@page session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.UserData"%>
<% UserData ud = (UserData)request.getAttribute("ud");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録内容更新</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h1>登録内容更新</h1>
        <form class="pure-form" action="Myupdateresult" method="post">
            <table class="pure-table pure-table-bordered">
                <tr>
                    <td style="font-weight: bold" >ユーザー名:</td>
                    <td><input type="text" name="name" value="<%=ud.getName()%>"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">旧パスワード:</td>
                    <td><input type="password" name="passwd"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">新パスワード:</td>
                    <td><input type="password" name="newpasswd"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">新パスワード(確認用):</td>
                    <td><input type="password" name="passwdconfirm"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">メールアドレス:</td>
                    <td><input type="text" name="email" value="<%=ud.getMail()%>"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">住所:</td>
                    <td><input type="text" name="address" value="<%=ud.getAddress()%>"></td>
                </tr>
            </table>
                <br>
                <input type="hidden" name="ac" value="<%=request.getAttribute("ac")%>">
            <button type="submit">更新</button>
        </form>
        <br>
        <%=kagoyume.Helper.mydata()%>
        </div>
    </body>
</html>
