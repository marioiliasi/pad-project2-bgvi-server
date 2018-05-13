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

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.pad.bgvi.server.model.Product;
import com.pad.bgvi.server.utils.DBConnection;
import com.pad.bgvi.server.utils.HibernateUtil;

public class ProductsServlet extends HttpServlet {

	public static final String CHARACTER_ENCODING = "utf-8";
	public static final String CONTENT_TYPE = "application/json";

	private static final long serialVersionUID = -8539893109234949296L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");

		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		String query = "select name, category_type, description, image, price, quantity from products where deleted = 0 ";
		if (type != null && type.length() > 0) {
			query += "and category_type = ?";
		}
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(query);
			if (type != null && type.length() > 0) {
				stmt.setString(1, type);
			}
			rs = stmt.executeQuery();
			JSONObject jsonObject;
			JSONArray jsonArray = new JSONArray();
			long quantity;
			double price;
			String name;
			String img;
			String description;
			int categoryType;
			while (rs.next()) {
				jsonObject = new JSONObject();
				name = rs.getString(1);
				categoryType = rs.getInt(2);
				description = rs.getString(3);
				img = rs.getString(4);
				price = rs.getDouble(5);
				quantity = rs.getLong(6);

				jsonObject.put("name", name == null ? "" : name);
				jsonObject.put("type", categoryType);
				jsonObject.put("description", description == null ? "" : description);
				jsonObject.put("image", img);
				jsonObject.put("price", price);
				jsonObject.put("quantity", quantity);
				jsonArray.put(jsonObject);
			}
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Credentials", "true");
			resp.addHeader("Access-Control-Allow-Methods", "GET, POST");
			resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

			resp.setContentType(CONTENT_TYPE);
			resp.setCharacterEncoding(CHARACTER_ENCODING);
			PrintWriter writer = resp.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String tmp;
			int quantity = -1;
			double price = -1;
			String name = "";
			byte[] img = null;
			String description = "";
			int categoryType = -1;

			/*
			 * name = req.getParameter("name"); description =
			 * req.getParameter("description");
			 * 
			 * tmp = req.getParameter("price"); if(tmp != null && tmp.length() > 0) { price
			 * = Double.parseDouble(tmp); }
			 * 
			 * tmp = req.getParameter("type"); if(tmp != null && tmp.length() > 0) {
			 * categoryType = Integer.parseInt(tmp); } tmp = req.getParameter("image");
			 * if(tmp != null && tmp.length() > 0) { img = tmp.getBytes(); }
			 */

			Product p;

			JSONTokener tokener = new JSONTokener(req.getInputStream());
			JSONArray array = new JSONArray(tokener);
			Session session = HibernateUtil.getSessionFactory().openSession();
			long id = 0;
			if (array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					quantity = (Integer) obj.get("quantity");
					id = (Integer) obj.get("id");
					
					p = (Product) session.get(Product.class, id);

					if (p.getQuantity() >= quantity) {
						// creating transaction object
						Transaction t = session.beginTransaction();

						p.setCategoryType(categoryType);
						p.setQuantity(p.getQuantity() - quantity);

						session.persist(p);
						t.commit();
					}
				}
			}
			session.close();
			resp.setStatus(200);

		} catch (Throwable t) {
			t.printStackTrace();
			resp.setStatus(417);
		}
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST");
		resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding(CHARACTER_ENCODING);
		
	}
}
