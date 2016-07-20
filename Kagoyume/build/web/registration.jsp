<%-- 
    Document   : registration
    Author     : user1
    新規会員登録ページ
    ○loginからのみ遷移
    ○フォームがあり、入力するのは以下の要素
        ユーザー名
        パスワード
        メールアドレス
        住所
    ○registrationconfirmから戻ってきた場合は、値を保持して記入済みにできる
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
        <title>新規登録</title>
    </head>
    <body>
        <%=kagoyume.Helper.logheader()%>
        <div class="main">
        <h1>新規登録</h1>
        <form action="Registrationconfirm" method="post">
            <table class="pure-table pure-table-bordered">
                <tr>
                    <td style="font-weight: bold">ユーザー名:</td>
                    <td><input type="text" name="name" value="<%=ud.getName()%>"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">パスワード:</td>
                    <td><input type="password" name="passwd" value="<%=ud.getPassword()%>"></td>
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
                <input type="submit" value="登録確認へ" name="regist">
        </form>
        </div>
    </body>
</html>
