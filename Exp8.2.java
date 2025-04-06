import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Employee Management</title></head><body>");
        out.println("<h2>Employee Management</h2>");
        out.println("<form method='get'>Search by ID: <input type='text' name='id'/> <input type='submit' value='Search'/></form><br>");

        try (Connection conn = DBConnection.getConnection()) {
            String query;
            if (empId != null && !empId.trim().isEmpty()) {
                query = "SELECT * FROM employees WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(empId));
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        out.println("<p>No employee found with ID: " + empId + "</p>");
                    } else {
                        displayTable(rs, out);
                    }
                }
            } else {
                query = "SELECT * FROM employees";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    displayTable(rs, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }

        out.println("</body></html>");
    }

    private void displayTable(ResultSet rs, PrintWriter out) throws SQLException {
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th><th>Salary</th></tr>");
        while (rs.next()) {
            out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                        rs.getString("name") + "</td><td>" +
                        rs.getString("department") + "</td><td>" +
                        rs.getDouble("salary") + "</td></tr>");
        }
        out.println("</table>");
    }
}
