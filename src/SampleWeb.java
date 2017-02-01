import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
/**
 * Servlet implementation class SampleWeb
 */
@WebServlet("")
public class SampleWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SampleWeb() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","root");
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("select * FROM employ");
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<h1> Employee table </h1>");
			String str = "<table border='5'><tr><th>Emp-id </th><th> Name </th><th> Department</th></tr>";
			while(rs.next())
			{
				
				str += "<tr><td>"+rs.getString(1)+" </td><td> "+rs.getString(2)+" </td><td> "+ rs.getString(3)+"</td></tr>";
				
			}
			str += "</table>";
			pw.println(str);
			st.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}