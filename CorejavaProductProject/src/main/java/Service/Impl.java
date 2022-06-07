package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import controller.MainController;
import dao.Repo;
import model.Product;

public class Impl implements Repo
{

	public void adddata(Product product) {
		Scanner sc=new  Scanner(System.in);
		System.out.println("Enter Product Code");
		product.setCode(sc.nextInt());
		System.out.println("Enter Product Quantity");
	     product.setQuantity(sc.nextInt());
		System.out.println("Enter Product Category");
		product.setCategory(sc.next());
		System.out.println("Enter Product Name");
		product.setName(sc.next());
		System.out.println("Enter Product Description");
		product.setDescription(sc.next());
		System.out.println("Enter Product Price");
		product.setPrice(sc.nextInt());
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mahidatabase","root","root");
			
			PreparedStatement ps=conn.prepareStatement("Insert into `mahidatabase`.`product` values(?,?,?,?,?,?)");
			ps.setInt(1,product.getCode());
			ps.setInt(2,product.getQuantity());
			ps.setString(3,product.getCategory());
			ps.setString(4,product.getName());
			ps.setString(5,product.getDescription());
			ps.setFloat(6,product.getPrice());
			
			ps.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println(e);
			
		}

		System.out.println("if u want continue add product yes/no");
		String a=sc.next();
		if(a.equals("yes"))
		{
			Impl impl=new Impl();
			impl.adddata(product);
		}
		else
		{
			MainController controller=new MainController();
			controller.main(null);
			
			
		}
	}
		
	

	public void DisplayandBuy(Product product) {
		
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mahidatabase","root","root");
		Statement stmt=con.createStatement();
		String query="SELECT * FROM product";
		ResultSet set=stmt.executeQuery(query);
		int size=100000;
		int i=0;
		int Code[]=new int[size];
		String Name[]=new String[size];
		int Quantity[]=new int[size];
		float Price[]=new float[size];
		String des[]=new String[size];
		String cat[]=new String[size];
		while(set.next())
		{
			int z=set.getInt(1);
			Name[z]=set.getString(4);
			Code[z]=set.getInt(1);
			Price[z]=set.getFloat(6);
			Quantity[z]=set.getInt(2);
			des[z]=set.getString(5);
			cat[z]=set.getString(3);
			System.out.println("CODE-->"+Code[z]+"  ,NAME-->"+Name[z]+" ,PRICE-->"+Price[z]+" ,QUANTITY-->"+Quantity[z]+" ,Descrption-->"+des[z]+" ,Category-->"+cat[z]);
				
			
			
		}
		
		 Scanner sc = new Scanner(System.in);
			System.out.println("How many product you want purchase");
			float bill=0.00f;
			
			int item=sc.nextInt();
			for(i=1;i<=item;i++)
			{
				System.out.println("Enter product code");
			
				int code=sc.nextInt();
				System.out.println("Available Quantity-->"+Quantity[code]);
				if(Quantity[code]>0)
				{
				System.out.println("Price of this product-->"+Price[code]);	
				System.out.println("How much Quantity you want?");
				int quant=sc.nextInt();
				bill=bill+quant*Price[code];
				int uqty=Quantity[code]-quant;
				
				stmt.executeUpdate("UPDATE `mahidatabase`.`product` SET `Quantity` = '"+uqty+"' WHERE (`Code` = '"+code+"')");
				}
				else {
					System.out.println("Stock is closed");
					MainController controller=new MainController();
					controller.equals(null);
				}
			}
			System.out.println("Your Final bill is---:-"+bill+"$");
			System.out.println("Thank you visit again");
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}

	
}

}
		
	

	

	


