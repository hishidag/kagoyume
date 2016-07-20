<%-- 
    Document   : registrationconfirm
    Author     : user1
    ○フォームで入力された文字や選択を表示し、「上記の内容で登録いたします。よろしいですか？」と表示。
    　「はい」でregistrationcomplete「いいえ」でregistrationに値を保持したまま(戻った時にフォーム入力済みになっている)遷移
    ○もし不足していた場合はどの項目のデータが不足しているのかを表示。registrationに値を保持したまま遷移するリンクを表示
--%>

<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="kagoyume.UserData"%>
<%
    UserData ud = (UserData)request.getAttribute("ud");
    Boolean flag = (Boolean)request.getAttribute("cannotuse");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規登録内容確認</title>
    </head>
    <body>
        <%=kagoyume.Helper.logheader()%>
        <div class="main">
        <%if(ud.checkForm().size() == 0 && !flag){%>
        <h1>新規登録内容確認</h1>
        <h2 style="margin-bottom: 5px">こちらの内容でよろしいですか？</h2><br>
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
        <div style="width: 30%">
            <form style="float: right" action="Registrationcomplete" method="post">
                <input type="hidden" value="<%=request.getAttribute("ac")%>" name="ac">
                <input type="submit" value="はい" name="regist">
            </form>
            <form style="float: left" action="Registration" method="post">
                <input type="hidden" name="reinput" value="reinput">
                <input type="submit" value="変更する">
            </form>
        </div>
        <%}else{%>
            <%if(flag){out.println("そのユーザー名は他の方が使用しています<br>");}%>
            <%for(String str : ud.checkForm()){%>
                <%=str%>が未記入です<br>
            <%}%>
            <br>
        <form action="Registration" method="post">
            <input type="hidden" name="reinput" value="reinput">
            <input type="submit" value="変更する" >
        </form>            
        <%}%>
        <br>
        <br>
        <%=kagoyume.Helper.top()%>
        </div>
    </body>
</html>
