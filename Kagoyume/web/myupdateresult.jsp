<%-- 
    Document   : myupdateresult
    Author     : user1
    ・IDなどを受け取り、DBを更新。
    ・「以上の内容で更新しました。」と、フォームで入力された値を表示
--%>
<%@page session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.UserData"%>
<% 
    UserData ud = (UserData)request.getAttribute("ud");
    String errstr = request.getAttribute("cannotuse").toString();
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新完了</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
            <%if(errstr.equals("")){%>
            <h1>アカウント情報更新完了</h1>
                <table class="pure-table pure-table-bordered">
                    <tr>
                        <td style="font-weight: bold">名前:</td>
                        <td><%=ud.getName()%></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">パスワード:</td>
                        <td>セキュリティのため表示されません</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">メールアドレス:</td>
                        <td><%=ud.getMail()%></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">住所:</td>
                        <td><%=ud.getAddress()%></td>
                    </tr>
                </table>
            <br>
            以上の内容で更新しました。<br>
            <br>
            <%=kagoyume.Helper.mydata()%>
            <%}else{%>
            <%=errstr%><br>
            <a href="Myupdate">変更ページへ戻る</a>
            <%}%>
        </div>
    </body>
</html>

