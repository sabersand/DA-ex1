package Process0;

import java.rmi.Naming;

import java.rmi.RemoteException;
import RMI_Interface.RMI_Interface;




public class Process_Manager {
	
	public static void main(String[] args) {
		
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		
		
		try {
		
			  RMI_Interface service0=new Process(0);
		//	  service0.Say();
		//	  service0.processid==1;
			  Naming.bind("process0", service0);
			  
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("process success!");
		
	
	}

}