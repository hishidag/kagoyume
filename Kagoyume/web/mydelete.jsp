<%-- 
    Document   : mydelete
    Author     : user1
    ・ユーザー削除確認ページ
    ・対象のレコードの全データを表示したのちに、「このユーザーをマジで削除しますか？」という質問があり、
    　「はい」と「いいえ」が直リンクとして設置してある。「はい」ならmydeleteresultへ、そうでないならトップページへ遷移する
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
        <title>アカウント削除確認</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
            <h1>アカウント削除</h1>
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
        以上のアカウントを削除します。よろしいですか？<br>
        <div style="display: inline-flex">            
            <form action="top.jsp">
                <button type="submit">いいえ</button>
            </form>
            <form action="Mydeleteresult" method="post">
                <input type="hidden" value="<%=request.getAttribute("ac")%>" name="ac">
                <button type="submit">はい</button>
            </form>
        </div>
        <br>
        <br>
        <%=kagoyume.Helper.mydata()%>
        </div>
    </body>
</html>
