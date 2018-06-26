    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author gabriel
 */
public class Conexao {
    public static Connection getConnection() {
        Connection connection = null;
        DataSource ds;

        try {
            Context initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/forum");
            connection = ds.getConnection();
        } catch (NamingException ex) {
            
        } catch (SQLException ex) {
           
        }
        return connection;
    }
}
