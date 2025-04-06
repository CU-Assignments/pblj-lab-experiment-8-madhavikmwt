// Combined Java Servlet Login Example with HTML and web.xml

// login.html
/*
<!DOCTYPE html>
<html>
<head><title>Login Page</title></head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    Username: <input type="text" name="username" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
*/

// LoginServlet.java
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
public class LoginServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        if("admin".equals(username) && "admin123".equals(password)) {  
            out.println("<h2>Welcome, " + username + "!</h2>");  
        } else {  
            out.println("<h2>Invalid credentials. Please try again.</h2>");  
            out.println("<a href='login.html'>Back to Login</a>");  
        }  
    }  
}

// web.xml
/*
<web-app xmlns="http://java.sun.com/xml/ns/javaee" version="3.0">
    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/login</url-pattern>
    </servlet-mapping>
</web-app>
*/
