/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import dao.Dao ;
import dao.ElementDao;
import dao.ListDao;
import dao.UserDao;

import model.Element;
import model.User;
import model.Lists;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.security.MessageDigest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import spark.Spark;
import static spark.Spark.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.Lists;
import spark.HaltException;
import spark.Request;
import spark.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sarra
 */
public class Controler {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    
    static boolean  authetification = false;
    
    public static void main(String[] args) throws SQLException {
            
    
    
        


        /*******************Data Base********************/

        Dao dao = new Dao();
        dao.DBcreation();
        
        /************************************************/
        

       
        Hash hash = new Hash();
        
        /****************************************************/
        
        
        
 
        
        /****************************************************/

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        
        try {
            //cfg.setClassForTemplateLoading(NewMain.class, "/");
            cfg.setDirectoryForTemplateLoading( new File("src/templates"));
            
            //FileTemplateLoader templateLoader = new FileTemplateLoader(new File(""));
            //cfg.setTemplateLoader(templateLoader); 
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }


        // cfg.setIncompatibleImprovements(new Version(2, 3, 20));
         cfg.setDefaultEncoding("UTF-8");
         cfg.setLocale(Locale.US);	
         cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
         cfg.setLogTemplateExceptions(false);
         cfg.setWrapUncheckedExceptions(true);
         
         
         /*************************************************************************/
         

         get("/", (req,res) -> {

            StringWriter writer = new StringWriter();
            try {
                    Map<String, Object> input = new HashMap<String, Object>();
                    Template template = cfg.getTemplate("connexion.ftl");
                    
                    template.process(input, writer);
            } catch (TemplateException | IOException e) {
                HaltException halt = Spark.halt(500);
            }
            return writer;        
        });
         
        post("/profil", (req,res) -> {

          StringWriter writer = new StringWriter();
          try {

                  int id = 7;
                  String userName = "sara";
                  UserDao userDao = new UserDao();
                  
                  Map<String, Object> input = new HashMap<String, Object>();
                  Template template = cfg.getTemplate("profil.ftl");
                  input.put("id", id);
                  input.put("userName", userName);

                  template.process(input, writer);
          } catch (TemplateException | IOException e) {
              HaltException halt = Spark.halt(500);
          }
          return writer;        
      });
         
          post("/connexion" , (Request request, Response respons) -> {
          
            //HttpSession session = request.session().raw();
           // session.setAttribute;
            StringWriter writer = new StringWriter();
            try {             
                    String userName  = request.queryParams("userName") != null ? request.queryParams("userName") : "unknown";
                    String password  = request.queryParams("password") != null ? request.queryParams("password") : "unknown";      
                    
                    System.out.println( userName );


                    int hashPassWord = hash.sha1(password);
                    System.out.println( hashPassWord );

                    Map<String, Object> input = new HashMap<String, Object>();
                    
                    UserDao userDao = new UserDao();
                    List<User> l = userDao.getUsers();
                    boolean b = false;
                    
                    for (int i = 0; i < l.size(); i++) {
			if( l.get(i).getPseudo().equals(userName)){ 
                            
                              if( l.get(i).getPassword() == hashPassWord ){
                                b = true;
                                break;
                            
                            }
                        }
                    }

                    
                    if( b == false ){
                        
                        Template template = cfg.getTemplate("connexionError.ftl");
                        template.process(input, writer);
                        
                    
                    }else{
                        
                        authetification = true;
                        User u = userDao.verification(userName, hashPassWord );
                        ListDao li = new ListDao();
                        int id = u.getId();
                        List<Lists> listes = li.getLists(id);
                        input.put("idUser", id);
                        input.put("systems", listes);
                        
                        Template template = cfg.getTemplate("openUser.ftl");
                        template.process(input, writer);
                    }
                    
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
        });
         
          
          
        get("/logOut" , (request, respons) -> {
            StringWriter writer = new StringWriter();

            try {             
                    authetification = true;    
                    Map<String, Object> input = new HashMap<String, Object>();
                    Template template = cfg.getTemplate("connexion.ftl");
                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
         
         get("/createAccount" , (request, respons) -> {
            StringWriter writer = new StringWriter();

            try {             
                    Map<String, Object> input = new HashMap<String, Object>();
                    Template template = cfg.getTemplate("createAccount.ftl");
                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
         
        post("/createAccount" , (request, respons) -> {
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();

            try {             
   
                    authetification = true;
                    String username = request.queryParams("username") != null ? request.queryParams("username") : "unknown";    
                    String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";    

                    int hashPassWord = hash.sha1(password);
                
                    UserDao userDao = new UserDao();
                    userDao.addUser(username, hashPassWord);
                    
                    User u = userDao.verification(username, hashPassWord  );
                                        
                    ListDao li = new ListDao();
                    int id = u.getId();
                    List<Lists> listes = li.getLists(id);
                    input.put("idUser", id);
                    input.put("systems", listes);

                    Template template = cfg.getTemplate("openUser.ftl");
                    template.process(input, writer);
                    
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        
        
        });
        
         
         


      get("/openUser" , (request, respons) -> {
            StringWriter writer = new StringWriter();
            //System.out.println(request.body());

            try {             
                    //String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";    
                    String id = "7";
                    Map<String, Object> input = new HashMap<String, Object>();
                    //UserDao u = new UserDao();
                    //List<User> l = u.getUsers();
                    
                    ListDao li = new ListDao();
                    List<Lists> l = li.getLists( Integer.parseInt(id) );

                    input.put("systems", l);
                    input.put("idUser", id);
                    Template template = cfg.getTemplate("openUser.ftl");

                template.process(input, writer);
                    //E.delete();
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        
        });
        
        post("/openList" , (request, respons) -> {
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }

            try {             
                    String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";    
                    //UserDao u = new UserDao();
                    //List<User> l = u.getUsers();
                    ListDao li = new ListDao();
                    Lists lis = li.getListbyId( Integer.parseInt(id));
                    String title = lis.getTitle();
                    
                    ElementDao e = new ElementDao();
                    List<Element> l = e.getElements( Integer.parseInt(id) );

                    input.put("systems", l);
                    input.put("title", title);
                    input.put("idList", id);

                    
                    Template template = cfg.getTemplate("openList.ftl");

                    template.process(input, writer);
                    //E.delete();
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        
        
        });
        
        post("/deleteList" , (request, respons) -> {
          
            StringWriter writer = new StringWriter();
            Map<String, Object> input = new HashMap<String, Object>();

            if( authetification == false){
                Template template = cfg.getTemplate("connexion.ftl");
                template.process(input, writer);
                return writer;   
            }
            //System.out.println(request.body());
            try {             
                    String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";      
                    
                    ListDao li = new ListDao();
                    Lists lis = li.getListbyId(Integer.parseInt(id));
                    String title = lis.getTitle();
                    
                    li.deleteList( Integer.parseInt(id) );

                    Template template = cfg.getTemplate("deleteList.ftl");
                    System.out.println(id);

                    input.put("systems",title);

                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
        });
        
        post("/deleteElement" , (request, respons) -> {
          
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }            //System.out.println(request.body());
            try {             
                    String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";                          
                    ElementDao li = new ElementDao();
                    Element lis = li.getElementbyId(Integer.parseInt(id));
                    String name = lis.getName();
                    
                    li.deleteElement( Integer.parseInt(id) );

                    Template template = cfg.getTemplate("deleteElement.ftl");
                    System.out.println(id);

                    input.put("systems",name);

                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
        });
        
        post("/openForm" , (request, respons) -> {
            
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }
            try {             
                    String id = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";    
                    //UserDao u = new UserDao();
                    //List<User> l = u.getUsers();
                    ListDao li = new ListDao();
                    Lists lis = li.getListbyId( Integer.parseInt(id));
                    String title = lis.getTitle();
                    
                    input.put("idList", id);
                    input.put("title", title);


                    Template template = cfg.getTemplate("openForm.ftl");

                    template.process(input, writer);
                    //E.delete();
            } catch (Exception e) {
                Spark.halt(500);
            }
            
            return writer;
        });
        
        
        post("/addNewElement" , (request, respons) -> {
            
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }            //System.out.println(request.body());
            try {             
                    String id = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";   
                    String name = request.queryParams("name") != null ? request.queryParams("name") : "unknown";   
                    String description = request.queryParams("description") != null ? request.queryParams("description") : "unknown"; 
                    
                    ListDao lDao = new ListDao(); 
                    Lists li = lDao.getListbyId(Integer.parseInt(id));
                    String ListTitle = li.getTitle();
                    
                    ElementDao e = new ElementDao();
                    e.addElement(Integer.parseInt(id), name, description);
                    
                    
                    Template template = cfg.getTemplate("addNewElement.ftl");
                    //System.out.println(id);

                    input.put("name",name);
                    input.put("ListTitle",ListTitle);


                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
            
        });
        
        post("/openFormAddList" , (request, respons) -> {
            
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }
            try {             
                    String id = request.queryParams("idUser") != null ? request.queryParams("idUser") : "unknown";      
                    input.put("idUser", id);

                    Template template = cfg.getTemplate("openFormAddList.ftl");

                    template.process(input, writer);
                    //E.delete();
            } catch (Exception e) {
                Spark.halt(500);
            }
            
            return writer;
        });
        
         post("/addNewList" , (request, respons) -> {
            
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }            //System.out.println(request.body());
            try {             
                    String id = request.queryParams("idUser") != null ? request.queryParams("idUser") : "unknown";   
                    String title = request.queryParams("title") != null ? request.queryParams("title") : "unknown";   
                    String description = request.queryParams("description") != null ? request.queryParams("description") : "unknown"; 
                    
                    ListDao lDao = new ListDao(); 
                    lDao.addList(Integer.parseInt(id), title, description);
                    
                    
                    
                    Template template = cfg.getTemplate("addNewList.ftl");
                    //System.out.println(id);

                    input.put("title",title);

                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
            
        });
        
        post("/openFormUpdateElement" , (request, respons) -> {
            
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }
            try {             
                    String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";    
                    //UserDao u = new UserDao();
                    //List<User> l = u.getUsers();
                    ElementDao eDao = new ElementDao();
                    Element e = eDao.getElementbyId(Integer.parseInt(id));
                    String name = e.getName();
                    String description = e.getDescription();
                    System.out.println(description);
                    
                    input.put("idElement", id);
                    input.put("name", name);
                    input.put("description", description);



                    Template template = cfg.getTemplate("openFormUpdateElement.ftl");

                    template.process(input, writer);
                    //E.delete();
            } catch (Exception e) {
                Spark.halt(500);
            }
            
            return writer;
        });
        
        post("/updateElement" , (request, respons) -> {
            
           StringWriter writer = new StringWriter();
           Map<String, Object> input = new HashMap<String, Object>();
           
           if( authetification == false){
               Template template = cfg.getTemplate("connexion.ftl");
               template.process(input, writer);
               return writer;   
           }            //System.out.println(request.body());
            try {             
                   String id = request.queryParams("idElement") != null ? request.queryParams("idElement") : "unknown";   
                   String name = request.queryParams("name") != null ? request.queryParams("name") : "unknown";   
                   String description = request.queryParams("description") != null ? request.queryParams("description") : "unknown"; 
                    
                    System.out.println(id);
                    System.out.println(name);
                    System.out.println(description);


                    ElementDao e = new ElementDao();
                    e.updateElement(Integer.parseInt(id), name, description);
                    
                    
                    Template template = cfg.getTemplate("updateElement.ftl");
                    //System.out.println(id);

                    input.put("name",name);


                    template.process(input, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
            
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
