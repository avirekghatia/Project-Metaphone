package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import myMetaphone.Input;

public final class result_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("    <head>\n");
      out.write("        <title>MUSEEK</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("          <script type=\"text/javascript\">\n");
      out.write("             function isNull(id)\n");
      out.write("             {\n");
      out.write("                 var txt = document.frm2.search_query;\n");
      out.write("                 if(txt.value != \"\" && txt.value != '\\n' )\n");
      out.write("                 {\n");
      out.write("                     document.getElementById(id).innerHTML=\"\";\n");
      out.write("                //     alert(\"m here1\");\n");
      out.write("                     return true;\n");
      out.write("                 }\n");
      out.write("                 else\n");
      out.write("                 {\n");
      out.write("                     document.getElementById(id).innerHTML=\"Please enter a valid query!!\";\n");
      out.write("                 //    alert(\"m here 2\");\n");
      out.write("                     return false;\n");
      out.write("                 }\n");
      out.write("                     \n");
      out.write("                 \n");
      out.write("             }\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body background=\"C:\\Users\\Ankita\\Desktop\\metaphone_project\\images\\music-notes-52-song.jpg\" >\n");
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        ");

            String query = request.getParameter("search_query");
            float approx;
            Input i = new Input();
            i.appxpercent = 0.8f;
           
      out.write("\n");
      out.write("\n");
      out.write("           \n");
      out.write("           \n");
      out.write("           \n");
      out.write("           \n");
      out.write("           ");
      out.write("\n");
      out.write("           \n");
      out.write("                  <div id=\"container_result_page\"> \n");
      out.write("                      <a href=\"help.html\"><div id=\"help\" align=\"right\"> How to use?</div></a>&nbsp;&nbsp;&nbsp;\n");
      out.write("            <!--heading -->\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <br></br>\n");
      out.write("                <form action=\"result.jsp\" method=\"get\" name=\"fmr2\" onsubmit=\"return isNull('form_error')\"> \n");
      out.write("                    <span id=\"sitetitle_result_page\"> Mussek</span>\n");
      out.write("                    &nbsp;\n");
      out.write("                    &nbsp;\n");
      out.write("                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("                    <input type=\"text\" name=\"search_query\" id=\"search_input_result_page\" value=\"");
      out.print(query);
      out.write("\"/>\n");
      out.write("                    <input type=\"submit\" name=\"submit\" value=\"Search\" id=\"search_button_result_page\"/>\n");
      out.write("                    <br/>\n");
      out.write("                    <p align=\"center\"><span id=\"form_error\"></span></p>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <!-- heading ends -->\n");
      out.write("            \n");
      out.write("            <!-- border -->\n");
      out.write("            <div id=\"menu_result_page\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- border ends -->\n");
      out.write(" \n");
      out.write("            <div id=\"content\">\n");
      out.write("           \n");
      out.write("           <div id=\"left_column\">\n");
      out.write("           \n");
      out.write("        ");

            i.getInput(query);
            if(i.isresult==true){
            int sid = i.sid;
            System.out.println("i got the sid " +sid);
            String result = "";
            String loc = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major","root","");

/*            String sq = "select fname from filelink where fid in (select fid from result where sid = "+sid+" order by rank)";
            ps =  (PreparedStatement) conn.prepareStatement(sq);
            rs = (ResultSet) ps.executeQuery();
*/


            String sq = "select fid from result where sid = "+sid+" order by rank";
            PreparedStatement   ps = (PreparedStatement) conn.prepareStatement(sq);
            ResultSet  rs = (ResultSet) ps.executeQuery();


                if(rs.first())
                {
                   do
                    {

                           int fid = rs.getInt(1);
                  //retriving fid
                           System.out.println(fid);
                                 String sq1 = "select fname, location from filelink where fid="+fid;
                  PreparedStatement               ps1 =  (PreparedStatement) conn.prepareStatement(sq1);
                  ResultSet               rs1 = (ResultSet) ps1.executeQuery();
                                 while(rs1.next())
                                 {     result = rs1.getString(1);
                                         loc = rs1.getString(2);}
                                 loc = loc.replace('/','\\');
                                
                                 
                                // loc="";
                                 
      out.write("\n");
      out.write("                                 \n");
      out.write("                    <div class=\"left_col_box\">\n");
      out.write("                        <div class=\"blog_box\">\n");
      out.write("                           <!-- <frame id=\"frame1\"> -->\n");
      out.write("                            <h3>    \n");
      out.write("                                <a href=\"file:///");
      out.print(loc);
      out.write("\" target=\"myframe\">\n");
      out.write("                                <a href=\"\"   target=\"myframe\" >     ");
      out.print(result);
      out.write(" </a></h3>\n");
      out.write("                      <!--      </frame> -->\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                \n");
      out.write("                                 \n");
      out.write("                                 ");

                    }while(rs.next());
                  //  resultTxt.setText(result);

                }

                else
                { 
      out.write("\n");
      out.write("                    <div class=\"left_col_box\">\n");
      out.write("                        \n");
      out.write("                           <!-- <frame id=\"frame1\"> -->\n");
      out.write("                            <h3>\n");
      out.write("                                <div id=\"text\">   No result found. </div>\n");
      out.write("                            </h3>\n");
      out.write("                      <!--      </frame> -->\n");
      out.write("                      \n");
      out.write("                    </div>\n");
      out.write("         ");
       }
            }
            else
            { 
      out.write("\n");
      out.write("                <div class=\"left_col_box\">\n");
      out.write("                            <h3>\n");
      out.write("                                ");

                                    if(i.isLongQuery== true){
                                
      out.write("\n");
      out.write("                                <div id=\"text\"   >    Too long query !! </div>\n");
      out.write("                                ");
 }
                                    else
                                    {
                                        
    
      out.write("\n");
      out.write("                                <div id=\"text\"   >    Enter a valid query !! </div>\n");
      out.write("                                ");
 } 
      out.write("\n");
      out.write("                            </h3>\n");
      out.write("                      \n");
      out.write("                    </div>\n");
      out.write("         ");
 
                                        }
        
      out.write("\n");
      out.write("        \n");
      out.write("           </div>\n");
      out.write("        <div id =\"right_column\">\n");
      out.write("                    <div class=\"right_panel_fullwidth\">\n");
      out.write("                     <!--   <div style='display:none'>\n");
      out.write("                            <iframe src=\"http://www.google.com\" name=\"myframe\"></iframe></div> -->\n");
      out.write("                   \n");
      out.write("                    </div>\n");
      out.write("        </div>\n");
      out.write("            </div>\n");
      out.write("           \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <div>\n");
      out.write("\n");
      out.write("                <br></br><br/><br/><br></br><br></br><br></br>  \n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <!--footer -->\n");
      out.write("            <div id=\"footer_result_page\">  \n");
      out.write("                Project By: Ankita Agrawal | Avirek Ghatia | Bhavya Dwivedi<br/>\n");
      out.write("                    Computer Engineering Department\n");
      out.write("            </div>\n");
      out.write("            <!-- footer ends -->\n");
      out.write("        </div>\n");
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
