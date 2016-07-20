package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>かごいっぱいのゆめ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.print(kagoyume.Helper.header(false));
      out.write("\n");
      out.write("        <h1>かごいっぱいのゆめ</h1>\n");
      out.write("        <p>\n");
      out.write("        ショッピングサイトを使っている時、こんな経験ありませんか？　\n");
      out.write("        「あれいいな」「これいいな」「あっ、関連商品のこれもいい」「20%オフセールだって！？　買わなきゃ！」・・・\n");
      out.write("        そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。「こんなに買ってたのか・・・しょうがない。いくつか減らそう・・・」\n");
      out.write("        仕方がありません。無駄遣いは厳禁です。でも、一度買うと決めたものを諦めるなんて、ストレスじゃあありませんか？　\n");
      out.write("        できればお金の事なんか考えずに好きなだけ買い物がしたい・・・。\n");
      out.write("        このサービスは、そんなフラストレーションを解消するために生まれた\n");
      out.write("        『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』『ECサイト』です\n");
      out.write("        </p>\n");
      out.write("        <form action=\"Search\">\n");
      out.write("            商品検索：<input type=\"text\" name=\"query\">\n");
      out.write("            <input type=\"submit\" value=\"検索\">\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
