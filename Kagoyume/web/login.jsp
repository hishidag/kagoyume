<%-- 
    Document   : login
    Author     : user1
    ログイン管理ページ
    ・どのページからも遷移できる。ログインしているかいないかで処理が分岐する
    ・ログインしていない状態(各ページの「ログイン」というリンクから)で遷移してきた場合は、
    　ユーザー名とパスワードを入力するフォームが表示される。また、「新規会員登録」というリンクも表示される。
    ・ログインに成功すると、その情報をログイン状態を管理できるセッションに書き込み、
    　そのまま直前まで閲覧していたページに遷移する
    ・ログインしている状態で(各ページの「ログアウト」というリンクから)遷移してきた場合は、
    　ログアウト処理を行う(セッションの破棄、クッキーに保存されたセッションIDを破棄)その後topへ
    ・ユーザーデータの削除フラグが1の場合は削除されたユーザーとして処理すること
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン</title>
    </head>
    <body>
        <%=kagoyume.Helper.logheader()%>
        <div class="main">
        <h1>ログイン</h1>
        <form action="Login" method="post">
            <table class="pure-table pure-table-bordered">
                <tr>
                    <td style="font-weight: bold">ユーザー名:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">パスワード:</td>
                    <td><input type="password" name="passwd"></td>
                </tr>
            </table>
            <br>
            <input type="hidden" name="url" value="<%=request.getAttribute("url")%>">
            <button type="submit" name="login">ログイン</button>            
        </form>
        <br>
        <%if(request.getAttribute("logerr") != null){
            out.println(request.getAttribute("logerr"));
        }%>
        <br>
        <br>
        <a href="Registration">新規登録の方はこちら</a>
        </div>
    </body>
</html>
