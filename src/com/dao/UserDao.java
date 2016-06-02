package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.model.User;

import com.util.DatabaseUtil;

public  class UserDao {
	
	public boolean checkConnection(){
		 boolean result=false;
		 Connection connection = null;   
         Statement sqlStatement =null;		
         try {
         	connection =DatabaseUtil.getConnection();
         	if (connection!=null){ 		
         		sqlStatement = connection.createStatement();
     			String select = "SELECT 1 FROM users";
     			sqlStatement.executeQuery(select);
     			result = true;
     			}  			
         	}
          catch (Exception e) {
             e.printStackTrace();
         }finally{
             //Close connection
             try{
                if(sqlStatement!=null){
             	   sqlStatement.close();
                }
                if(connection!=null){
             	   connection.close();
                }
             }catch(SQLException se){
             } 
         }
         return result;
	}

    public boolean addUser(User u) {
          boolean result=false;
	      Connection connection = null;   
	      Statement sqlStatement =null;		
	      try {
	    	  connection =DatabaseUtil.getConnection();   	  
	      	if (connection!=null){    		
	      		sqlStatement = connection.createStatement();
	  			String insert = "INSERT INTO users (firstname, lastname , telephone, email,city) "+
	  							"VALUES ('"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getTelephone()+"','"+u.getEmail()+  "','"+u.getCity()+"')";
	  			sqlStatement.executeUpdate(insert);
	  			result=true;
	      	}
	      } catch (Exception e) {
	          e.printStackTrace();
	      }finally{
	          //Close connection
	          try{
	             if(sqlStatement!=null){
	          	   sqlStatement.close();
	             }
	             if(connection!=null){
	          	   connection.close();
	             }
	          }catch(SQLException se){
	          } 
	       }        
	        return result;
    }

    public boolean deleteUser(int userid) {
        boolean result=false;
	      Connection connection = null;   
	      Statement sqlStatement =null;		
	      try {
	    	  connection =DatabaseUtil.getConnection();   	  
	      	if (connection!=null){    		
	      		sqlStatement = connection.createStatement();
	      	    String delete = "DELETE FROM users " +"WHERE user_id = " + userid;
	  			sqlStatement.executeUpdate(delete);
	  			result=true;
	      	}
	      } catch (Exception e) {
	          e.printStackTrace();
	      }finally{
	          //Close connection
	          try{
	             if(sqlStatement!=null){
	          	   sqlStatement.close();
	             }
	             if(connection!=null){
	          	   connection.close();
	             }
	          }catch(SQLException se){
	          } 
	       }        
	        return result;
    }

    public boolean updateUser(User u) {
    	  boolean result=false;
	      Connection connection = null;   
	      Statement sqlStatement =null;		
	      try {
	    	  connection =DatabaseUtil.getConnection();   	  
	      	if (connection!=null){    		
	      		sqlStatement = connection.createStatement();
	      		
	      		String update = "UPDATE users "+
	      					    "SET firstname='"+u.getFirstName()+"', lastname='"+u.getLastName()+"', telephone='"+u.getTelephone()+"', city='"+u.getCity()+"', email='"+u.getEmail()+"' " +
	      		                 "WHERE user_id='"+u.getUserid()+"';";
	      		
	  			sqlStatement.executeUpdate(update);
	  			result=true;
	      	}
	      } catch (Exception e) {
	          e.printStackTrace();
	      }finally{
	          //Close connection
	          try{
	             if(sqlStatement!=null){
	          	   sqlStatement.close();
	             }
	             if(connection!=null){
	          	   connection.close();
	             }
	          }catch(SQLException se){
	          } 
	       }        
	        return result;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Connection connection = null;   
        Statement sqlStatement =null;		
        try {
        	connection =DatabaseUtil.getConnection();
        	if (connection!=null){ 		
        		sqlStatement = connection.createStatement();
    			String select = "SELECT * FROM users";
    			ResultSet rs = sqlStatement.executeQuery(select);
    			 while(rs.next()){
    				 User u = new User();
    				 u.setUserid(rs.getInt("user_id"));
    				 u.setCity(rs.getString("city"));
    				 u.setFirstName(rs.getString("firstName"));
    				 u.setLastName(rs.getString("lastName"));
    				 u.setTelephone(rs.getString("telephone"));				 
    				 u.setEmail(rs.getString("email"));
    				 users.add(u);
    			 }  			
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //Close connection
            try{
               if(sqlStatement!=null){
            	   sqlStatement.close();
               }
               if(connection!=null){
            	   connection.close();
               }
            }catch(SQLException se){
            } 
         }
        return users;
    }
    
    public User getUserById(int userid) {
    	  User u = new User();
          Connection connection = null;   
          Statement sqlStatement =null;		
          try {
          	connection =DatabaseUtil.getConnection();
          	if (connection!=null){ 		
          		sqlStatement = connection.createStatement();
      			String select = "SELECT * FROM users WHERE user_ID='" + userid+"'";
      			ResultSet rs = sqlStatement.executeQuery(select);
      			rs.first();
				u.setUserid(rs.getInt("user_id"));
				u.setCity(rs.getString("city"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setTelephone(rs.getString("telephone"));				 
				u.setEmail(rs.getString("email"));
      			}  			
          	}
           catch (Exception e) {
              e.printStackTrace();
          }finally{
              //Close connection
              try{
                 if(sqlStatement!=null){
              	   sqlStatement.close();
                 }
                 if(connection!=null){
              	   connection.close();
                 }
              }catch(SQLException se){
              } 
           }
          return u;
    }

}