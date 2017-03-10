package core;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "userLogin", urlPatterns = {"/userLogin"})
public class userLogin extends HttpServlet 
{    
    public String username;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");        
        username=request.getParameter("user"); // берем имя пользоватля
        PrintWriter out=response.getWriter();
        
        try
        {
            session=request.getSession();
            session.setAttribute("username",request.getParameter("user")); // передаем имя пользователя
            String username = session.getAttribute("username").toString(); // берем имя пользоватля      
         
            // отображение чат-комнаты
            out.println("<html> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <title>Chat Room</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
            out.println("<script type=\"text/javascript\" src=\"js/script.js\"></script>");
            out.println("</head>");
            out.println("<div class=\"clock\"> \n" +
            "                <form name=\"form_clock\">\n" +
            "                    <p><input id=\"inputdate\" name=\"date\" type=\"text\" name=\"date\" value=\"\" size=\"12\" disabled=\"true\"></p>\n" +
            "                    <p><input id=\"inputtime\" name=\"time\" type=\"text\" name=\"time\" value=\"\" size=\"12\" disabled=\"true\"></p>\n" +
            "                </form> \n" +
            "           </div>");
            out.println("<body onload=\"datetime()\">");
            out.println("<div class=\"page-wrapper\"><center><h2>Привет, ");
            out.println(username + "!");
            out.println("<br>Добро пожаловать в WebChat");
            out.println("</h2><br>");            
            out.println("<form name=\"chatWindow\" action=\"chatWindow\">");
            out.println("<span class=\"refresh\"><a href=\"chatWindow\">Обновить чат-комнату</a></span><br><br>");
            out.println("История сообщений:");
            out.println("<br><br>");
            out.println("<textarea  readonly=\"readonly\" id=\"text_message\" name=\"txtMessage\">");
                  
            // выбираем историю сообщений из базы данных        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/schema_chat","root","1656");              
                Statement st = connection.createStatement();                 
                ResultSet rs = st.executeQuery("select * from message;");
            
                // вывод истории сообщений
                while(rs.next())
                {
                    String messages = rs.getString(2) + " >> " + rs.getString(3);
                    out.println(messages);
                }               
                connection.close();
            }
            catch(Exception exception) 
            {
                System.err.println(exception.getMessage());
            }
            out.println("</textarea><br>");
            out.println("<input placeholder=\"Ваше сообщение\" type=\"text\" id=\"txt_msg\" name=\"txtMsg\" value=\"\" /><input type=\"submit\" value=\"Отправить\" id=\"cmd_msg\" name=\"cmdSend\"/>");
            out.println("</form>");
            out.println("</div></body>");
            out.println("</html>"); 
        }
        catch(Exception e)
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ошибка!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><br><br><br><br><center>Ошибка сервлета</center></h1>");
            out.println("</body>");
            out.println("</html>");
            System.out.println(e);
        }     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    HttpSession session;
}
