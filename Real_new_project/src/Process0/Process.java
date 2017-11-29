package Process0;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import RMI_Interface.RMI_Interface;
import RMI_Interface.RMI_Process_Interaction;






public class Process extends UnicastRemoteObject implements  RMI_Interface,RMI_Process_Interaction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int processid;
	int[] timestamp= {0,0,0};
	ArrayList<Message_Recording> s_buffer;
	ArrayList<Message_Object> b_buffer;
	
	public Process(int id) throws RemoteException {
	  processid=id;
	  s_buffer=new ArrayList<Message_Recording>();
	  b_buffer=new ArrayList<Message_Object>();
	}
	
	public  synchronized void Incretimestamp() {
		(timestamp[processid])++;
	}
	
	
	public synchronized void Send_message(int m, String processname,int transfertime) {
		Incretimestamp();
		Message_Object msg=new Message_Object(m,s_buffer,timestamp);
			Runnable h=new Hermes(msg,processname,transfertime);
			Thread myThread=new Thread(h);
			myThread.start();
			try {
			}catch(Exception e) {
				
			}
			int destination=Judge_Destination(processname);
			Message_Recording message_recording=new Message_Recording(destination,timestamp);
			for(int i=0;i<this.s_buffer.size();i++) {
				if(this.s_buffer.get(i).destination==destination)
					this.s_buffer.remove(i);
			}
			this.s_buffer.add(message_recording);
			
			
			
		System.out.println("call successfull!");
		
	}
	
	public int Judge_Destination(String processname) {
		if(processname.equals("process0")) return 0;
		else if(processname.equals("process1")) return 1;
		    else if(processname.equals("process2")) return 2;
		    else return 2;
		
	}
	
	public boolean Smaller_than(int[] a,int[] b) {
	//	System.out.print(b[2]);
	if (a[0]<=b[0]&&a[1]<=b[1]&&a[2]<=b[2])
		return true;
		else return false;
	
	}
	
	public boolean Delivery_enable(ArrayList<Message_Recording> s) {
		boolean t=true;
	//	System.out.print(s.size());
		for(int i=0;i<s.size();i++)
		{
			if(s.get(i).destination==processid&&!Smaller_than(s.get(i).timestamp,timestamp))
				t=false;    		
		}
		return t;
	}
	
	public int[] max(int[]a,int[]b) {
		if(Smaller_than(a,b)) 
			return b;
			else return a;
		
	}
	
	public synchronized void Message_Delivery(int m, ArrayList<Message_Recording> sb,int[] t) {
		int[] replace=new int[3];
		boolean find=false;
		Message_Recording replace_message;
		Incretimestamp();
		for(int i=0;i<3;i++) {
			if (timestamp[i]<=t[i])
				timestamp[i]=t[i];
				
		}

		System.out.printf("%d,%d,%d \n",timestamp[0],timestamp[1],timestamp[2]);
		System.out.printf("%d  ",m);
//		for(int i=0;i<sb.size();i++)
//		{
//			System.out.printf("{ %d,[%d,%d,%d]}",sb.get(i).destination,sb.get(i).timestamp[0],sb.get(i).timestamp[1],sb.get(i).timestamp[2]);
//		}
		System.out.printf("\n");
	//	System.out.printf("%d",s_buffer.size());
	//	Incretimestamp();
		for(int i=0;i<sb.size();i++) {
			for(int j=0;j<s_buffer.size();j++) {
				if(sb.get(i).destination==s_buffer.get(j).destination) {
					s_buffer.remove(j);
					replace[0]=max(sb.get(i).timestamp,s_buffer.get(j).timestamp)[0];
					replace[1]=max(sb.get(i).timestamp,s_buffer.get(j).timestamp)[1];
					replace[2]=max(sb.get(i).timestamp,s_buffer.get(j).timestamp)[2];
					replace_message=new Message_Recording(processid,replace);
					s_buffer.add(replace_message);
					find=true;
				}
			}
			if(find==false) {
				s_buffer.add(sb.get(i));
			}
			find=false;
		}
	}
	
	public synchronized void Receive_message(Message_Object msg)  {
	//	System.out.print(msg.s_buffer.size());
	//	System.out.print(s_buffer.size());
	//	if (msg.message==3)
	//		System.out.printf("%d\n",msg.message);
//		for(int i=0;i<msg.s_buffer.size();i++)
//		{
//			System.out.printf("{ %d,[%d,%d,%d]}   ",msg.s_buffer.get(i).destination,msg.s_buffer.get(i).timestamp[0],msg.s_buffer.get(i).timestamp[1],msg.s_buffer.get(i).timestamp[2]);
//		}
		
		if( Delivery_enable(msg.s_buffer)) {
			Message_Delivery(msg.message,msg.s_buffer,msg.timestamp);
			for(int i=0;i<b_buffer.size();i++) {
				if(Delivery_enable(b_buffer.get(i).s_buffer)) {
					Message_Delivery(b_buffer.get(i).message,b_buffer.get(i).s_buffer,b_buffer.get(i).timestamp);
					b_buffer.remove(i);
				}
			}
			
		}
		else 
		b_buffer.add(msg);
//		System.out.printf("%d",b_buffer.size());
		for(int i=0;i<b_buffer.size();i++)
			{
				for(int j=0;j<b_buffer.get(i).s_buffer.size();j++)
				System.out.printf("{ %d,[%d,%d,%d]}  \n ",b_buffer.get(i).s_buffer.get(j).destination,msg.s_buffer.get(j).timestamp[0],msg.s_buffer.get(j).timestamp[1],msg.s_buffer.get(j).timestamp[2]);
			}
	}
	
	
	public void Say() {
		System.out.println("qunimade");
	}
	
	public void Print_s_buffer(){
		for(int i=0;i<s_buffer.size();i++)
			System.out.println(s_buffer.get(i).destination);
	}
}











