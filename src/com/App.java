package com;

import java.awt.FlowLayout;
import javax.swing.JFrame;

import com.view.UserUI;

public class App {

    public static void main(String[] args) {
    	  	
    	JFrame f=new JFrame();
    	f.setTitle("User Management");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        f.getContentPane().add(new UserUI());
        f.setSize(1250, 450);
        f.setVisible(true);     
    }

}