/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;

import org.sql2o.Connection;
import java.util.List;





/**
 *
 * @author Sarra
 */
public class UserDao extends Dao {
    
    public UserDao(){
    
    
    }
    
    
  public void addUser(String username, int password){
      
      try (Connection con2 = sql2o.open()) {
            String query = "INSERT INTO USER (PSEUDO , PASSWORD )VALUES(:username, :password)";

            con2.createQuery(query).addParameter("username", username).addParameter("password", password).executeUpdate();
            con2.commit();
        }
  }  
  
  public List<User> getUsers(){
    try (Connection con2 = sql2o.open()) {
      final String query = "SELECT  * FROM USER";

      return con2.createQuery(query).executeAndFetch(User.class);
    }
}
 
  public User verification(String userName , int password){
      
    try (Connection con2 = sql2o.open()) {
        final String query = "SELECT  * FROM USER WHERE PSEUDO = :userName and PASSWORD = :password ";
        return con2.createQuery(query).addParameter("userName", userName).addParameter("password", password).executeAndFetchFirst(User.class);
     }
      
  
  
  }
  
  
  
  
  
}
