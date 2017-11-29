package Process_Control;
import java.rmi.*;

import RMI_Interface.RMI_Interface;


public class Process_Manager {
	
	public static void main(String args[]) {
		try {
			RMI_Interface service0=(RMI_Interface)Naming.lookup("rmi://localhost:1099/process0");
			RMI_Interface service1=(RMI_Interface)Naming.lookup("rmi://localhost:1099/process1");
			RMI_Interface service2=(RMI_Interface)Naming.lookup("rmi://localhost:1099/process2");
			service0.Send_message(2, "process2",9000);
		    service0.Send_message(1,"process1",0);
		    Thread.sleep(2000);
		    service1.Send_message(3,"process2",0);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	
		
		
	}

}
