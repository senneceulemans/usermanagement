package com.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.controller.UserBean;
import com.model.User;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class UserUI extends JPanel  {
	
	   private UserBean userBean = new UserBean();
	   
	   private JTextField idField = new JTextField(4);
	   private JTextField firstNameField = new JTextField(15);
	   private JTextField lastNameField = new JTextField(15);
	   private JTextField emailField = new JTextField(15);
	   private JTextField cityField = new JTextField(15);
	   private JTextField telephoneField = new JTextField(9);
	   
	   private JButton newButton = new JButton("New");
	   private JButton editButton = new JButton("Edit");
	   private JButton saveButton = new JButton("Save");
	   private JButton previousButton = new JButton("Previous");
	   private JButton nextButton = new JButton("Next");
	   private JButton deleteButton = new JButton("Delete");
	   private JButton cancelButton = new JButton("Cancel");
	   
	   private JTable usersTable = new JTable();

	   
	   public UserUI() {		
		 //Create the userinterface
		   JScrollPane paneUserTable = new JScrollPane(initTable());
		   paneUserTable.setPreferredSize( new Dimension( 700, 400 ));	   
		   setLayout(new FlowLayout());
		   add(paneUserTable);
		   add(createUserDetailPane());	      
	   }
	   
	   private JTable initTable(){
		   usersTable.setModel(populateTableFirst());
		   usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		   
		   usersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {	
			   
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = usersTable.getSelectedRow();
				if (row != -1){
					setFieldsEnabled(false);
					editButton.setEnabled(true);
					deleteButton.setEnabled(true);
					previousButton.setEnabled(true);
					nextButton.setEnabled(true);
					User u = userBean.getUserFromID(Integer.parseInt((String)usersTable.getModel().getValueAt(row, 0)));
					setFieldData(u);					 
				}	
			}
		});
		   return usersTable;
	   }
	   
	   private JPanel createUserDetailPane(){
		   	JPanel panelUserDetails = new JPanel();
		   	panelUserDetails.setBorder(new TitledBorder (new EtchedBorder(),"User Details"));
		   	panelUserDetails.setLayout(new BorderLayout(5, 5));
		   	panelUserDetails.add(initUserDetailFields(), BorderLayout.NORTH);
		   	panelUserDetails.add(initUserDetailButtons(), BorderLayout.CENTER);
	   		return panelUserDetails;
	   }

	   private JPanel initUserDetailButtons() {
	      JPanel panelButtons = new JPanel();
	      panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
	      
	      panelButtons.add(newButton);
	      newButton.addActionListener(new ButtonHandler());
	      
	      panelButtons.add(editButton);
	      editButton.addActionListener(new ButtonHandler());
	      editButton.setEnabled(false);
	      
	      panelButtons.add(saveButton);
	      saveButton.addActionListener(new ButtonHandler());
	      saveButton.setEnabled(false);
	      
	      panelButtons.add(deleteButton);
	      deleteButton.addActionListener(new ButtonHandler());
	      deleteButton.setEnabled(false);
	      
	      panelButtons.add(previousButton);
	      previousButton.addActionListener(new ButtonHandler());
	      previousButton.setEnabled(false);
	      
	      panelButtons.add(nextButton);
	      nextButton.addActionListener(new ButtonHandler());
	      nextButton.setEnabled(false);
	      
	      panelButtons.add(cancelButton);
	      cancelButton.addActionListener(new ButtonHandler());
	      cancelButton.setEnabled(false);

	      return panelButtons;
	   }

	   private JPanel initUserDetailFields() {
	      JPanel panel = new JPanel();
	      
	      panel.setLayout(new MigLayout());
	      
	      panel.add(new JLabel("ID"), "align label");
	      panel.add(idField, "wrap");
	      idField.setEnabled(false);
	      
	      panel.add(new JLabel("First Name"), "align label");
	      panel.add(firstNameField, "wrap");
	      firstNameField.setEnabled(false);
	      
	      panel.add(new JLabel("Last Name"), "align label");
	      panel.add(lastNameField, "wrap");
	      lastNameField.setEnabled(false);
	      
	      panel.add(new JLabel("Telephone"), "align label");
	      panel.add(telephoneField, "wrap");
	      telephoneField.setEnabled(false);
	    
	      panel.add(new JLabel("City"), "align label");
	      panel.add(cityField, "wrap");   
	      cityField.setEnabled(false);
	          	     	      
	      return panel;
	   }
	    
	   private DefaultTableModel populateTableFirst() {
		   if (!userBean.checkConnection())
		   {
			   JOptionPane.showMessageDialog(null, "Could not connect to Database.", "Error", JOptionPane.ERROR_MESSAGE);
		   }
		   return populateTable();
	   }
	   
	   private void rePopulateTable  (){
		   usersTable.setModel(populateTable());		   
	   }
   
	   private DefaultTableModel populateTable(){
		   
		   DefaultTableModel userTableModel = new DefaultTableModel( new Object[]{ "UserID", "First name", "Last name","Telephone","City","email" }, 0){
			        @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
			   };
		   List<User> users = userBean.getAllUsers();
		   
		   for (int i = 0; i < users.size(); i++) {
	            String[] data = new String[6];
	            if(users.get(i)!= null) {
		            data[0] = Integer.toString(users.get(i).getUserid());
		            data[1] = users.get(i).getFirstName();
	        		data[2] =users.get(i).getLastName();       				
		            data[3] =users.get(i).getTelephone();
		            data[4] =users.get(i).getCity();
		            data[5] =users.get(i).getEmail();
		            userTableModel.addRow(data);	   
	            }
	        }
	       return userTableModel;
	   }
   	   
	   private User getFieldData() {
	      User u = new User();
	      u.setFirstName(firstNameField.getText());
	      u.setLastName(lastNameField.getText());
	      u.setEmail(emailField.getText());
	      u.setTelephone(telephoneField.getText());
	      u.setCity(cityField.getText());  	            
	      return u;
	   }

	   private void setFieldData(User u) {
	      idField.setText(String.valueOf(u.getUserid()));
	      firstNameField.setText(u.getFirstName());
	      lastNameField.setText(u.getLastName());
	      cityField.setText(u.getCity());
	      telephoneField.setText(u.getTelephone());
	      emailField.setText(u.getEmail());
	   }
	   
	   private void setFieldsEnabled(boolean enabled){
 		  firstNameField.setEnabled(enabled);
 		  lastNameField.setEnabled(enabled);
 	      cityField.setEnabled(enabled);
 		  telephoneField.setEnabled(enabled);
 		  emailField.setEnabled(enabled);
 		  cancelButton.setEnabled(enabled);
 		  saveButton.setEnabled(enabled);
 		  newButton.setEnabled(!enabled);
 		  editButton.setEnabled(false);		
 		  deleteButton.setEnabled(false);	
 		  previousButton.setEnabled(false);
 		  nextButton.setEnabled(false);
 	 	}
  
 	   private void clearFields(){
 	      idField.setText("");
	      firstNameField.setText("");
	      lastNameField.setText("");
	      cityField.setText("");
	      telephoneField.setText("");
	      emailField.setText("");
 	 	}
	   
	   private class ButtonHandler implements ActionListener {
		   
	      @Override
	      	public void actionPerformed(ActionEvent e) {
	         
	         switch (e.getActionCommand()) {
	         
	         case "Save":
	        	 saveUserAction();           
	            break;
	            
	         case "Edit":
	        	 editUserAction();           
	            break;
	               
	         case "New":
	        	 newUserAction();
	            break;
	            
	         case "Cancel":
	        	 cancelUserAction();
	            break;
	            
	         case "Delete":
	        	 deleteUserAction();
	            break;
	            
	         case "Next":
	        	 nextUserAction();
	        	 break;
	        	 
	         case "Previous":
	        	 previousUserAction();
	        	 break;

	         default:
	            JOptionPane.showMessageDialog(null,
	            "invalid command");
	         }        
	      }
		}
	   
  	   private void saveUserAction(){
  	 		if (validateUserData()) 
  	 		{
               JOptionPane.showMessageDialog(null,"Firstname and LastName mandatory");            
            }else
            {
            	if(idField.getText().equals(""))
            	{
		            saveCreateUser();             		
            	}else
            	{	            		            			
            		saveUpdateUser();  			            			           		            
            	}
            }
        }

  	   private void saveUpdateUser() {
			User uUpdate = new User();
			uUpdate.setUserid(Integer.parseInt(idField.getText()));
			uUpdate.setFirstName(firstNameField.getText());
			uUpdate.setLastName(lastNameField.getText());
			uUpdate.setEmail(emailField.getText());
			uUpdate.setTelephone(telephoneField.getText());
			uUpdate.setCity(cityField.getText());  	
			if(userBean.updateUser(uUpdate)){
				JOptionPane.showMessageDialog(null,"User updated successfully.");
			}else{
				JOptionPane.showMessageDialog(null, "Could not update user. Check connection.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			rePopulateTable();  
			clearFields();
			setFieldsEnabled(false);
  	   }

  	   private void saveCreateUser() {
			User uCreate = getFieldData();
			if(userBean.create(uCreate)){
				JOptionPane.showMessageDialog(null,"New user created successfully.");
			}else{
				JOptionPane.showMessageDialog(null, "Could not create user. Check connection.", "Error", JOptionPane.ERROR_MESSAGE);
			}	              
			rePopulateTable(); 
			clearFields();
			setFieldsEnabled(false);
  	   }	    	  
	  	   	 	  
  	   private void newUserAction(){
 			clearFields();
        	setFieldsEnabled(true);
  	 	}
  	 	
  	   private void editUserAction(){
        	setFieldsEnabled(true);
  	   }
  	 	
  	   private void deleteUserAction(){
			if(!idField.getText().equals("")){
		        if(userBean.deleteUser(Integer.parseInt(idField.getText()))){
		        	JOptionPane.showMessageDialog(null,"User deleted successfully.");
		        }else{
		        	JOptionPane.showMessageDialog(null, "Could not delete user. Check connection.", "Error", JOptionPane.ERROR_MESSAGE);
		        } 	 			
				rePopulateTable();  
				clearFields();
		        setFieldsEnabled(false);	  	 			
			}
  	   }
  	 	
  	   private void cancelUserAction(){
			clearFields();
	    	setFieldsEnabled(false);
  	   }
  	   
  	   private void nextUserAction(){
  		   int rowCount = usersTable.getRowCount();
  		   int selectedRow = usersTable.getSelectedRow();
  		   //Check if there are more than 1 rows and that a row is selected
  		   if (rowCount > 1 || selectedRow != -1){
  			   if(selectedRow +1 < rowCount){
  				   usersTable.setRowSelectionInterval(selectedRow +1, selectedRow +1);
  			   }
  		   }  		   
  	   }
  	   
  	   private void previousUserAction(){
  		   int rowCount = usersTable.getRowCount();
  		   int selectedRow = usersTable.getSelectedRow();
  		   //Check if there are more than 1 rows and that a row is selected
  		   if (rowCount > 1 || selectedRow != -1){
  			   if(selectedRow > 0){
  				   usersTable.setRowSelectionInterval(selectedRow -1, selectedRow -1);
  			   }
  		   }  		   
  	   }
  	 
  	   private boolean validateUserData() {
	  	  return (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty());
	   }
	  	  
	  	 	
   }


