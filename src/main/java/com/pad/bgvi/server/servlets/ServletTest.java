package com.pad.bgvi.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pad.bgvi.server.utils.DBConnection;

public class ServletTest extends HttpServlet {

	public static final String CHARACTER_ENCODING = "utf-8";
	public static final String CONTENT_TYPE = "application/json";

	private static final long serialVersionUID = -8539893109234949296L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//http://localhost:8080/test?name=ana2 - intra pe link-ul asta dupa ce pornesti aplicatia
		String name = req.getParameter("name");
		
		if(name == null || name.length() == 0) {
			resp.sendError(401);
			return;
		}
		
		/*jsonObject.put("name", "Bia");
		jsonObject.put("age", "21");
		jsonObject.put("height", 165);
		jsonArray.put(jsonObject);*/
		
		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		String query = "select * from articol where name_a = ?";
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			JSONObject jsonObject;
			JSONArray jsonArray = new JSONArray();
			int quantity;
			int price;
			while(rs.next()) {
				jsonObject = new JSONObject();
				price = rs.getInt(2);
				quantity = rs.getInt(5);
				jsonObject.put("price", price);
				jsonObject.put("quantity", quantity);
				jsonArray.put(jsonObject);
			}
			PrintWriter writer = resp.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
