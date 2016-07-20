<%-- 
    Document   : index
    Author     : user1
    トップページ。ルートはここである
    ○このシステムの簡単な説明が記載されている。テキストは自由
    ○キーワード検索フォームが設置されている。検索の遷移先はsearchで、GETメソッド。未入力ならエラーを表示
    ・ヘッダーの判定
--%>
<%@page session="false" %>
<%@page import="kagoyume.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/pure.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごいっぱいのゆめ</title>
    </head>
    <body>
        <%=kagoyume.Helper.header(request)%>
        <div class="main">
        <h1>かごいっぱいのゆめ</h1>
        <form action="Search">
            <div style="float:left">
                <span>
                    商品検索：<input style="float: next;height: 32px;" type="search" placeholder="検索" name="query">
                    <input id="search" type="image" alt="検索" src="img/search.jpeg" style="float: right;margin-left: 2px">
                </span>
            </div>
        </form>
        <br>
        <br>
        <br>
        <p>
        ショッピングサイトを使っている時、こんな経験ありませんか？<br>
        <br>
        「あれいいな」「これいいな」「あっ、関連商品のこれもいい」「20%オフセールだって！？　買わなきゃ！」・・・<br>
        そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。「こんなに買ってたのか・・・しょうがない。いくつか減らそう・・・」<br>
        <br>
        仕方がありません。無駄遣いは厳禁です。でも、一度買うと決めたものを諦めるなんて、ストレスじゃあありませんか？<br>
        できればお金の事なんか考えずに好きなだけ買い物がしたい・・・。<br>
        <br>
        このサービスは、そんなフラストレーションを解消するために生まれた<br>
        『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』『ECサイト』です
        </p>        
        </div>
    </body>
</html>
