package RMI_Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_Interface extends Remote{


  public	void Say() throws RemoteException;
  public    void Send_message(int m, String processname,int transfertime) throws RemoteException;
  public    void Print_s_buffer() throws RemoteException;
}
