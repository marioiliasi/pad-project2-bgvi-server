package com.pad.bgvi.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pad.bgvi.common.model.Article;
import com.pad.bgvi.common.rmi.Shop;

public class ShopImpl extends UnicastRemoteObject implements Shop{

	public ShopImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1116459233034373505L;

    public List<Article> getArticles() throws RemoteException {
        List<Article> list = new ArrayList<Article>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bgvi", "root",
                    "p4ssw0rd");
            PreparedStatement ps = connection.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Article a = new Article();
                a.setName(rs.getString(1));
                a.setType(rs.getInt(2));
                a.setDescription(rs.getString(3));
                a.setImage(rs.getString(4));
                a.setPrice(rs.getDouble(5));
                a.setQuantity(rs.getInt(6));
                list.add(a);
            }
            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
}
