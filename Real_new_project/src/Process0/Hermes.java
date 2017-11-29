package Process0;

import RMI_Interface.RMI_Process_Interaction;

public class Hermes implements Runnable {
	private Message_Object message_object;
	public String processname;
	int transfertime;
	
	public Hermes(Message_Object msg, String pcname,int tft ) {
		message_object= msg;
	//	System.out.print(msg.s_buffer.size());
		processname=pcname;
		transfertime=tft;
	}
	public void run() {
		try {
		//	System.out.print(message_object.s_buffer.size());
			Thread.sleep(transfertime);
		//	System.out.print(message_object.s_buffer.size());
			RMI_Process_Interaction destprocess=(RMI_Process_Interaction)java.rmi.Naming.lookup("rmi://localhost:/"+processname);
			destprocess.Receive_message(message_object);
		//	System.out.print(message_object.s_buffer.size());
		//	System.out.print(message_object.timestamp[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
