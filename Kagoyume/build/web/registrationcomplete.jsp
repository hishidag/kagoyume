<%-- 
    Document   : registrationcomplete
    Author     : user1
    ○プロフィール用のDBに値を挿入。この際、現在時(年日時分)を組み込み関数で取得し、追加。
    ○「以上の内容で登録しました。」とregistrationconfirmのようにフォームで入力された値を表示
    ・「トップページへ戻る」のリンクが、目立つ場所に設置されている
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
        <title>新規登録完了</title>
    </head>
    <body>
        <%=kagoyume.Helper.logheader()%>
        <div class="main">
            <h1>新規登録完了</h1>
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
            </table>
        <br>
        以上の内容で登録しました        
        <br>
        <%=kagoyume.Helper.top()%>
        </div>
    </body>
</html>
