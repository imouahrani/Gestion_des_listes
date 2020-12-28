/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sarra
 */
public class User {
    
        private int ID_USER;
        private String pseudo;
        private int passWord;
        
    public User(){
    }
            
            
    public User(int id , String pseudoUser, int passWordUser){
    
        this.ID_USER = id;
        this.pseudo = pseudoUser;
        this.passWord = passWordUser;
        
    }
    
    public int getId(){
        return this.ID_USER;
    }
    
    public String getPseudo(){
        return this.pseudo;
    }
    
    public int getPassword(){
        return this.passWord;
    }
    
    public String getPasswordToString(){
        return this.passWord+"";
    }
    
    
    
}
