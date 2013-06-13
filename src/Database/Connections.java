package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;


public class Connections 
{

   public Connection connection;
   
   public static Connections instance=null; 
        
     void showError(String error) 
        { 
            JOptionPane.showMessageDialog(null,error);
        }

    
    private  Connections() 
	{
	instance=this;
        
        try 
		    
         {   //driver pt conexiunea mysql la jdbc(java date base connection)
             Class.forName("com.mysql.jdbc.Driver").newInstance(); 
         }
         
         catch (Exception e) 
         {
             showError("Unable to find and load driver");
             System.exit(1);
         }     
         try 
          //conectarea la baza de date   
	 {
	     connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=root");
              System.out.println("Connected to database");
	 }
	 catch(SQLException e) 
	 {   //in caz de eroare
	     showError("DATABASE ERROR!!!");
	 }
    }
    
    /*/ constructorul getInstance()
     * aici se testeaza daca  instanţă este “instantieted” sau nu 
     */
    public static Connections getInstance()
    {
        if (instance==null)
            instance=new Connections();
        return instance;
    }
        
    // adaugare produs (title,Brand) 
     public  void add(String t,String a) 
	{
		try 
		{
			Statement stmt =  connection.createStatement();
			stmt.executeUpdate("INSERT into products " +"(title, Brand) "+"values ('"+t+"', '"+a+"')") ;
                        showError("The product was added succesfully!");
		} 
                catch (SQLException ex) 
                {
			showError("Failure in adding the product to database!!!");
		}
                
                
	}
  //stergere produs
     public void delete_title(String del) 
	{
		
		try 
		{
                    Statement stmt = connection.createStatement();	
                    stmt.executeUpdate(" DELETE from products where title ='" +del+ "' limit 30"); 
                    showError("The product title has been deleted!");
		 } 
		catch (SQLException ex) 
		{
			showError("Failed to delete!");
		}
	}
// stergere Brand
     public void delete_Brand(String del) 
	{
		
		try 
		{
                	Statement stmt = connection.createStatement();	
                        stmt.executeUpdate("DELETE from products where Brand ='" +del+ "' limit 30"); 
                        showError("The product title has been deleted!");
		 } 
		catch (SQLException ex) 
		{
			showError("Failed to delete!");
		}
	}
// modificare produs
	public void update_title(String init, String fin) 
	{
		try 
		{
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate(" Update products set " +
					" title='"+fin+"' " +
					" where title='"+init+"'"); 
                        showError("The product's title has been updated!");
		
		} 
                catch (SQLException ex) 
                {
			showError("Failed to update!");
		}
		
	}
  //modificare Brand      
        public void update_Brand(String init, String fin) 
	{
		try 
		{
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate(" Update products set " +
					" brand='"+fin+"' " +
					" where brand='"+init+"'"); 
                       
                        showError("The product's brand has been updated!");
		
		} 
                catch (SQLException ex) 
                {
			showError("Failed to update!!");
		}
		
	}
	
   //Log In
 public int log_in(String user, String password)
  {
    int i=0;
    try 
     {
            Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT user,password FROM admin"
                                                  +  " where user='"+user+"'"
                                                  +  " and password='"+password+"'" );
		    
             while (rs.next()) 
               {
                i=i+1;
               }	
      }
     catch (SQLException ex) 
	     {
		   showError("DATABASE ERROR!!!");     
	     }  
    return i;
 }
     
}
