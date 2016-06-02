package com.model;

public class User {
	
	    private int userid;
	    private String firstName="";
	    private String lastName="";
	    private String email="";
	    private String city="";
	    private String telephone="";
	    
	    public int getUserid() {
	        return userid;
	    }
	    public void setUserid(int userid) {
	        this.userid = userid;
	    }
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    @Override
	    public String toString() {
	        return "User [userid=" + userid + ", firstName=" + firstName
	                + ", lastName=" + lastName + ", city=" + city + ", email="
	                + email +", telephone=" + telephone + "]";
	    }    

}
