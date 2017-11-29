package Process0;

import java.rmi.Naming;

import RMI_Interface.RMI_Interface;




public class Process_Manager2{
	
	public static void main(String[] args) {
		
		
		try {
		
			  RMI_Interface service2=new Process(2);
			  Naming.bind("process2", service2);
			  
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("process success!");
		
	
	}

}