package test;
import java.sql.*;
import java.util.*;
public class DemoApp1 {
	public static void main(String[] args) {
		try {
			Scanner s= new Scanner(System.in);
			System.out.println("=====Choice=====");
			System.out.println("1.AddProduct\n2.ViewAllProduct\n3.ViewProductByCode\n4.UpdateProduct\n5."
					+ "DeleteProduct");
			System.out.println("===Enter Choice===");
			int choice= Integer.parseInt(s.nextLine());
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","AZAD","Azad");
			
			switch (choice)
			{
			case 1:
				PreparedStatement ps1=con.prepareStatement("insert into product44 values(?,?,?,?)");
				System.out.println("Enter Product id:");
				String pid=s.nextLine();
				System.out.println("Enter Product Name:");
				String pname=s.nextLine();
				System.out.println("Enter Product Price:");
				float pprice=s.nextFloat();
				System.out.println("Enter Product qty:");
				int pqty=s.nextInt();
				ps1.setString(1,pid);
				ps1.setString(2,pname);
				ps1.setFloat(3,pprice);
				ps1.setInt(4,pqty);
				int k=ps1.executeUpdate();
				if(k>0)
				{
					System.out.println("Data Inserted Successfull....");
				}
				break;
				
			case 2:
				PreparedStatement ps2=con.prepareStatement("select* from product44");
				ResultSet rs=ps2.executeQuery();
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				}  // end of loop
				break;
				
			case 3:
				System.out.println("Enter Product Id:");
				String pid2=s.nextLine();
				PreparedStatement ps3=con.prepareStatement("select * from product44 where pid=?");
				ps3.setString(1, pid2);
		        ResultSet rs2=ps3.executeQuery();
		        if(rs2.next())
		        {
		        	System.out.println("Product:="+rs2.getString(1));
		        }  // end of if
		        else 
		        {
		        	System.out.println("Invalid Product id......");
		        }
		        break;
		        
			case 4:
				PreparedStatement ps4=con.prepareStatement("update product44 set pqty=pqty+?"
						+ " where pid=?");
				System.out.println("Enter Product id:");
				int pid3=s.nextInt();
				ps4.setInt(1, pid3);
				ResultSet rs3=ps4.executeQuery();
				if(rs3.next())
				{
					PreparedStatement ps5=con.prepareStatement("update product44 set pqty=pqty+?"
							+ " where pid=?");
					System.out.println("old qty:"+rs3.getFloat(4));
					System.out.println("Enter New Product Qty:");
					int qty2=Integer.parseInt(s.nextLine());
					ps4.setInt(4, qty2);
					int k1=ps5.executeUpdate();
					if(k1>0) 
					{ 
						System.out.println("Data is Updated.....");
					} // end of if
				}  // end of if
				System.out.println("Invalid Product id....");
				break;
				
			case 5:
				
				
			}  // end of switch
 		}  // end of try
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
