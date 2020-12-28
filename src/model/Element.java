/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Sarra
 */
public class Element {
    
    private int id_element;
    private int id_list;
    private String name ;
    private String description ;
    private Date creation_date;
    private Date update_date;
    
    
    public Element(){
    
    }
    
    public Element(int idE, int idL, String name, Date creDate, Date updDate, String desc){
       
        this.id_element = idE;
        this.id_list = idL;
        this.name = name;
        this.creation_date = creDate;
        this.update_date = updDate;
        this.description = desc;
    }
    
    public int getIdElement(){
        return this.id_element;
    }
    
    public int getIdList(){
        return this.id_list;
    }
    
    public String getName(){
        return this.name;
    }
     public String getDescription(){
        return this.description;
    }
    
    public Date getCreationDate(){
        return this.creation_date;
    }
    
    public Date getUpdateDate(){
        return this.update_date;
    }
    
    
    
    
    
    
}
