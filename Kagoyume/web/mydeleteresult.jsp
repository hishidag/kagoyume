<%-- 
    Document   : mydeleteresult
    Author     : user1
    ・ここにアクセスした段階で、IDによる削除が実行される(外部キー制約により直接DELETEは出来ないので、削除フラグを0から1に変更する)
    ・「削除しました」という一文が表示される
--%>

<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除完了</title>
    </head>
    <body>
        <%=kagoyume.Helper.logheader()%>
        <div class="main">
            <h1>アカウント情報削除完了</h1>
            削除しました
            <br>
            <br>
            <%=kagoyume.Helper.top()%>
        </div>
    </body>
</html>
