/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import products.TableModel;


/**adugare si modificare titlu
 * @param add
 *@param update_title
 * @author Andrei
 */
public class TestConnection2 {

    /**update_title
     *
     */
    @Test
   public void add_update_title(){
   Database.Connections con;
    con=Database.Connections.getInstance();
    TableModel tm= new TableModel();
    tm.initVectors_admin();
    int col = tm.selectAll();
    
    con.add("telefon","Nokia");
    tm.selectAll();
    assert(tm.selectAll() == col +1 );
    
    con.update_title("telefon","mobile");
    tm.selectAll();
    assert(tm.selectAll() == col +1 );
    /*/
     * in final title: telefon se va schimba in mobile
    /*/
}
}
