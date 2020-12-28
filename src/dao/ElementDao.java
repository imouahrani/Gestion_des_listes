/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import model.Element;
import org.sql2o.Connection;


/**
 *
 * @author Sarra
 */
public class ElementDao extends Dao{
    
    Date date = new Date();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();


    //String actuDate = date.getDate().toString();
    
    public ElementDao(){
    
    }
 
    
    
   public List<Element> getElements( int idList ){
        try (Connection con2 = sql2o.open()) {
            final String query = "SELECT * FROM ELEMENT WHERE ID_LIST = :idList";
            return con2.createQuery(query).addParameter("idList", idList).executeAndFetch(Element.class);
        }
   }
    
   public Element getElementbyId( int idElem ){
        try (Connection con2 = sql2o.open()) {
            final String query = "SELECT * FROM ELEMENT WHERE ID_ELEMENT = :idElem";
            return con2.createQuery(query).addParameter("idElem", idElem).executeAndFetchFirst(Element.class);   
        }
   }
   
   public void deleteElement( int idElem ){
        try (Connection con2 = sql2o.open()) {
            String query = "DELETE FROM ELEMENT WHERE ID_ELEMENT = :idElem";
            con2.createQuery(query).addParameter("idElem", idElem).executeUpdate();
            con2.commit();
        }
    }
    
    public void addElement( int idList, String name, String description   ){
        try (Connection con2 = sql2o.open()) {
            String query = "INSERT INTO ELEMENT (ID_LIST , NAME , DESCRIPTION,  CREATION_DATE , UPDATE_DATE )VALUES(:idList, :name, :description, :dateCrea, :dateUpd)";

            con2.createQuery(query).addParameter("idList", idList).addParameter("name", name).addParameter("description", description).addParameter("dateCrea", dtf.format(localDate)).addParameter("dateUpd", dtf.format(localDate)).executeUpdate();
            con2.commit();
        }
    }
   
    public void updateElement( int idElement, String name, String description   ){
        try (Connection con2 = sql2o.open()) {
            String query = "UPDATE ELEMENT SET NAME = :name , DESCRIPTION = :description, UPDATE_DATE = :dateUpd WHERE ID_ELEMENT = :idElement";

            con2.createQuery(query).addParameter("idElement", idElement).addParameter("name", name).addParameter("description", description).addParameter("dateUpd", dtf.format(localDate)).executeUpdate();
            con2.commit();
        }
    }
    
    
    
    
   
   
   
    
   
   
   
   
   
   
}
