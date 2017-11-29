package RMI_Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Process0.Message_Object;


public interface RMI_Process_Interaction extends Remote {
	public  void Receive_message(Message_Object msg) throws RemoteException;
}
