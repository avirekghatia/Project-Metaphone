<%@page import="java.io.File"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="myMetaphone.Input"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>MUSEEK</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="style.css" rel="stylesheet" type="text/css" />
          <script type="text/javascript">
             function isNull(id)
             {
                 var txt = document.frm2.search_query;
                 if(txt.value != "" && txt.value != '\n' )
                 {
                     document.getElementById(id).innerHTML="";
                //     alert("m here1");
                     return true;
                 }
                 else
                 {
                     document.getElementById(id).innerHTML="Please enter a valid query!!";
                 //    alert("m here 2");
                     return false;
                 }
                     
                 
             }
            
        </script>
    </head>
    <body background="C:\Users\Ankita\Desktop\metaphone_project\images\music-notes-52-song.jpg" >

        
        
        
        
        <%
            String query = request.getParameter("search_query");
            float approx;
            Input i = new Input();
            i.appxpercent = 0.8f;
           %>

           
           
           
           
           <%--
           String approxQuery = request.getParameter("approximation");
           if(approxQuery.equals(""))
               i.appxpercent = (float) 0.8;
           else
            {


                try{
                i.appxpercent = Float.parseFloat(approxQuery);

                if(i.appxpercent < 0.5)
                {
                    i.appxpercent = (float) 0.8;
                }
                else if(i.appxpercent >1)
                {
                    i.appxpercent =  (float) 1;
                }
                }catch(NumberFormatException ne)
                {
                    System.out.println("wrong number entered");
                    i.appxpercent = (float) 0.8;
                }
            }
           --%>
           
                  <div id="container_result_page"> 
            <!--heading -->
            <div id="header">
                <div id="title">
                    <div id="sitetitle">Museek</div>

                </div>
            
                <br><br>
                        
                <form action="result.jsp" method="get" name="fmr2" onsubmit="return isNull('form_error')"> 
                <!--    <span id="sitetitle"> Mussek</span> -->
                    
                <!--    <input type="text" name="search_query" id="search_input_result_page" value="<%//=query%>"/> -->
                   <input type="text" name="search_query" id="search_input_result_page" value="<%=query%>"/>
                    
                    <input type="submit" name="submit" value="Search" id="search_button_result_page"/>
                    <br/>
                    <p align="center"><span id="form_error"></span></p>
                </form>
         </div> 
            <!-- heading ends -->
            
            <!-- border -->
            <div id="menu_result_page">
            </div>

            <!-- border ends -->
 
            <div id="content">
           
           <div id="left_column">
           
        <%
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
                                 %>
                                 
                    <div class="left_col_box">
                        <div class="blog_box">
                           <!-- <frame id="frame1"> -->
                            <h3>    
                                <a href="file:///<%=loc%>" target="myframe">
                                <a href=""   target="myframe" >     <%=result%> </a></h3>
                      <!--      </frame> -->
                        </div>
                    </div>
                
                                 
                                 <%
                    }while(rs.next());
                  //  resultTxt.setText(result);

                }

                else
                { %>
                    <div class="left_col_box">
                        
                           <!-- <frame id="frame1"> -->
                            <h3>
                                <div id="text">   No result found. </div>
                            </h3>
                      <!--      </frame> -->
                      
                    </div>
         <%       }
            }
            else
            { %>
                <div class="left_col_box">
                            <h3>
                                <%
                                    if(i.isLongQuery== true){
                                %>
                                <div id="text"   >    Too long query !! </div>
                                <% }
                                    else
                                    {
                                        
    %>
                                <div id="text"   >    Enter a valid query !! </div>
                                <% } %>
                            </h3>
                      
                    </div>
         <% 
                                        }
        %>
        
           </div>
        <div id ="right_column">
                    <div class="right_panel_fullwidth">
                     <!--   <div style='display:none'>
                            <iframe src="http://www.google.com" name="myframe"></iframe></div> -->
                   
                    </div>
        </div>
            </div>
           
            
            
            
            <div>

                <br></br><br/><br/><br></br><br></br><br></br>  

            </div>
            
            
            
            <!--footer -->
            <div id="footer_result_page">  
                Project By: Ankita Agrawal | Avirek Ghatia | Bhavya Dwivedi<br/>
                    Computer Engineering Department
            </div>
            <!-- footer ends -->
        </div>
    </body>
</html>
