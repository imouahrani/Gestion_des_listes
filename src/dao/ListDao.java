/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Lists;
import org.sql2o.Connection;

/**
 *
 * @author Sarra
 */
public class ListDao extends Dao {
    
    public ListDao(){
    
    }
    
    public List<Lists> getLists( int idUser ){
        try (Connection con2 = sql2o.open()) {
            final String query = "SELECT * FROM LIST WHERE ID_USER = :idUser";
            return con2.createQuery(query).addParameter("idUser", idUser).executeAndFetch(Lists.class);
        }
    }
    
     public void deleteList( int idList ){
        try (Connection con2 = sql2o.open()) {
            
            String query = "DELETE FROM ELEMENT WHERE ID_lIST = :idList";
            con2.createQuery(query).addParameter("idList", idList).executeUpdate();
            con2.commit();
            
            query = "DELETE FROM LIST WHERE ID_lIST = :idList";
            con2.createQuery(query).addParameter("idList", idList).executeUpdate();
            con2.commit();
        }
    }
     
     public Lists getListbyId( int idList ){
        try (Connection con2 = sql2o.open()) {
            final String query = "SELECT * FROM LIST WHERE ID_LIST = :idList";
            return con2.createQuery(query).addParameter("idList", idList).executeAndFetchFirst(Lists.class);
            
        }
   }
      
   public void addList( int idUser, String title, String description   ){
        try (Connection con2 = sql2o.open()) {
            String query = "INSERT INTO LIST (ID_USER , TITLE , DESCRIPTION )VALUES(:idUser, :title, :description)";

            con2.createQuery(query).addParameter("idUser", idUser).addParameter("title", title).addParameter("description", description).executeUpdate();
            con2.commit();
        }
   }
     
     
     
     
    
    
    
}
