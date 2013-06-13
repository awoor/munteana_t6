/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import org.junit.Test;
import products.TableModel;

/**test add_delete
 *@param add
 * @param delete_title
 * @author Andrei
 */
public class TestConnections {
 
    /**Test pentru adaugare 
     * si stergere
     * am facut cite o adaugare si o stergere  penru a nu modifica baza de date
     */
    @Test
  public void addDelete() 
  {
    Database.Connections con;
    con=Database.Connections.getInstance();
    TableModel tm= new TableModel();
    tm.initVectors_admin();
    int col = tm.selectAll();
    
    con.add("titlu1","brand1");
    tm.selectAll();
    assert(tm.selectAll() == col +1);
    col = tm.selectAll();
    con.delete_title("titlu1");
 
    assert(tm.selectAll() == col - 1);
    
       
    
  }
   
 
}