package Process0;

import java.io.Serializable;
import java.util.ArrayList;

public class Message_Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4751693112889110125L;
	int message;
	ArrayList<Message_Recording> s_buffer=new ArrayList<Message_Recording>();
	int[] timestamp=new int[3];
	public Message_Object(int m, ArrayList<Message_Recording> s, int[] t) {
		message=m;
		
		for(int i=0;i<s.size();i++) {
			Message_Recording msgr=new Message_Recording(s.get(i).destination,s.get(i).timestamp);
			s_buffer.add(msgr);
		}
		
		timestamp[0]=t[0];
		timestamp[1]=t[1];
		timestamp[2]=t[2];
	}
	
}
