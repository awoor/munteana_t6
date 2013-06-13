
package products;
/**
 *
 * @Author Andrei
 */
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
/*
 * TableModel – clasa responsabila de logica tabelelor si query-urilor
*/
public class TableModel extends AbstractTableModel
 {
  public Vector data;
  public Vector columnNames ; 
  

  //INTITIALIZE THE TABLE pt admin
  public void initVectors_admin() 
  {
     data = new Vector();
     columnNames = new Vector();
   
     columnNames.addElement("ID");     //ID
     columnNames.addElement("Title"); //Title
     columnNames.addElement("Brand"); //Brand
	    
     Object[][] data = {
		        	{null, null,null},
				{null, null,null},
				{null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
				{null, null,null}};
  }

    //INTITIALIZE THE TABLE pt user
  public void initVectors_user() 
  {
     data = new Vector();
     columnNames = new Vector();
   
     columnNames.addElement("Title");
     columnNames.addElement("Brand");
	    
     Object[][] data = {
		        	{null, null,null},
				{null, null,null},
				{null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
                                {null, null,null},
				{null, null,null}};
  }


  public void setData_admin() 
  {
    Database.Connections con;
    con=Database.Connections.getInstance();
    
    try 
     {
         Statement statement = con.connection.createStatement();
         ResultSet rs = statement.executeQuery("SELECT id,title,Brand FROM products");
         while (rs.next()) 
            {
                 String id=(String)(rs.getString(1));
		 String title=(String)(rs.getString(2));
		 String Brand=(String)(rs.getString(3));
		 
                 data.addElement(id);
                 data.addElement(title);
                 data.addElement(Brand);
                
            }	
      }
     catch (SQLException excep) 
	     {
		   showError("DATABASE ERROR!!!");     
	     }          
  }
 
  
  public void setData_user() 
  {
    Database.Connections con;
    con=Database.Connections.getInstance();
      
    try 
     {
         Statement statement = con.connection.createStatement();
         ResultSet rs = statement.executeQuery("SELECT title,Brand FROM products");
         while (rs.next()) 
            {
                
		 String title=(String)(rs.getString(1));
		 String Brand=(String)(rs.getString(2));
		 
                 data.addElement(title);
                 data.addElement(Brand);
                
            }	
      }
     catch (SQLException excep) 
	     {
		   showError("DATABASE ERROR!!!");     
	     }          
  }
 
  //cautare dupa Brand
  public int search_Brand(String a) 
  {
    Database.Connections con;
    con=Database.Connections.getInstance();
    int i=0; 
    
    try 
     {
         Statement statement = con.connection.createStatement();
         ResultSet rs = statement.executeQuery("SELECT title,Brand FROM products"+ " where Brand='"+a+"'");
		    
         while (rs.next()) 
            {
                
		 String title=(String)(rs.getString(1));
		 String Brand=(String)(rs.getString(2));
		 
                 data.addElement(title);
                 data.addElement(Brand);
                 
                 i=i+1;
                
            }	
      }
     catch (SQLException excep) 
	     {
		   showError("DATABASE ERROR!!!");     
	     }  
    return i;
  }
 
  //cautare dupa Titlu
  public int search_title(String t) 
  {
    Database.Connections con;
    con=Database.Connections.getInstance();
    int i=0; 
    
    try 
     {
         Statement statement = con.connection.createStatement();
         ResultSet rs = statement.executeQuery("SELECT title,Brand FROM products"+ " where title='"+t+"'");
		    
         while (rs.next()) 
            {
                
		 String title=(String)(rs.getString(1));
		 String Brand=(String)(rs.getString(2));
		 
                 data.addElement(title);
                 data.addElement(Brand);
                 
                 i=i+1;
                
            }	
      }
     catch (SQLException excep) 
	     {
		   showError("DATABASE ERROR!!!");     
	     }  
    return i;
  }
 
  //cautare dupa Titlu
  public int selectAll() 
  {
    Database.Connections con;
    con=Database.Connections.getInstance();
    int i=0; 
    
    try 
     {
         Statement statement = con.connection.createStatement();
         ResultSet rs = statement.executeQuery("SELECT title,Brand FROM products");
		    
         while (rs.next()) 
            {
          
		 String title=(String)(rs.getString(1));
		 String Brand=(String)(rs.getString(2));
		 
                 data.addElement(title);
                 data.addElement(Brand);
                 
                 i=i+1;
                
            }	
      }
     catch (SQLException excep) 
	     {
		   showError("DATABASE ERROR!!!");     
	     }  
    return i;
  }
  
  //in caz de erori
 void showError(String error) 
        { 
            JOptionPane.showMessageDialog(null,error);
        }

  
  public int getRowCount() {
    return data.size()/ getColumnCount();
    }

  public int getColumnCount(){
    return columnNames.size();
    }

  public String getColumnName(int columnIndex) {
    String colName = "";

    if (columnIndex <= getColumnCount())
       colName = (String)columnNames.elementAt(columnIndex);

    return colName;
    }
    
  public Class getColumnClass(int columnIndex){
    return String.class;
    }
    
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
    }
    
  public Object getValueAt(int rowIndex, int columnIndex) {
    return (String)data.elementAt
        ( (rowIndex * getColumnCount()) + columnIndex);
    }
    
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    return;
    }
  

}
