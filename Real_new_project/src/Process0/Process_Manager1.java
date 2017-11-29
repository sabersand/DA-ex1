package Process0;

import java.rmi.Naming;

import RMI_Interface.RMI_Interface;




public class Process_Manager1{
	
	public static void main(String[] args) {
		
		
		try {
		
			  RMI_Interface service1=new Process(1);
			  Naming.bind("process1", service1);
			  
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("process success!");
		
	
	}

}