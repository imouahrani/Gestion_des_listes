/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Dao {
    
    private static final String JDBC_DRIVER = "h2.Driver";
    private static final String DB_NAME = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";


    //private Statement stmt = null;	
    //private PreparedStatement stm = null;
    protected  Sql2o sql2o ;
    protected  Connection con ;
    
    public Dao(){
    
    try{
		
            this.sql2o = new Sql2o(DB_NAME, USER, PASS );
            this.con =  sql2o.open();

            //this.con = DriverManager.getConnection(DB_NAME, USER, PASS);
            //this.stmt = con.createStatement();
            //stm = con.prepareStatement("select NAME from USER where ID_USER = ?") ;
            //stm.setInt(1, 1) ;

            //ResultSet rs = stm.executeQuery() ;
            //rs.first() ;


        }catch( Exception e )
        {
                System.err.println("Error occured : " + e.getMessage());
        }

    }
    
    public void DBcreation() throws SQLException{
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS USER(ID_USER INT  AUTO_INCREMENT, PSEUDO VARCHAR(50), PASSWORD INT, PRIMARY KEY(ID_USER))";
        this.write(sql);

        sql = "CREATE TABLE IF NOT EXISTS LIST(ID_LIST INT NOT NULL AUTO_INCREMENT, ID_USER INT, TITLE VARCHAR(50), DESCRIPTION VARCHAR(50), PRIMARY KEY(ID_LIST), FOREIGN KEY(ID_USER) REFERENCES USER(ID_USER))";
        this.write(sql);

        sql = "CREATE TABLE IF NOT EXISTS ELEMENT (ID_ELEMENT INT NOT NULL AUTO_INCREMENT, ID_LIST INT, NAME VARCHAR(50),DESCRIPTION VARCHAR(50),CREATION_DATE DATE, UPDATE_DATE DATE,PRIMARY KEY(ID_ELEMENT), FOREIGN KEY(ID_LIST) REFERENCES LIST(ID_LIST) )";
        this.write(sql);
        // Remember to call commit() when a transaction is opened,
        // default is to roll back.
        //con.commit();
        //con.rollback();

        if( con != null )
        {
                System.out.println("connection established");

        }

    }

    public void DBsupression() throws SQLException{
        
        String sql;
        sql = "DROP TABLE IF EXISTS USER;";
        this.write(sql);

        sql = "DROP TABLE IF EXISTS LIST;";
        this.write(sql);

        sql = "DROP TABLE IF EXISTS ELEMENT;";
        this.write(sql);
    }

    public void write(String sql ) throws SQLException{

        try (Connection con1 =  sql2o.beginTransaction()) {

                con1.createQuery(sql).executeUpdate();
                con1.commit();
        }

    }
    
    
    
}
