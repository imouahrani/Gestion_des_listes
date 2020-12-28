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
public class Lists {
    
    private int id_list;
    private  int id_user;
    private  String title;
    private  String description;
    
    public Lists(){
    
    }
    public Lists(int id_l, int id_u, String title, String desc){
       
        this.id_list = id_l;
        this.id_user = id_u;
        this.title = title;
        this.description = desc;
    
    }
    
    public int getIdList(){
        return this.id_list;
    }
    
    
    public int getIdUser(){
        return this.id_user;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
            return this.description;
    }
    
    
    
    
    
    
}

