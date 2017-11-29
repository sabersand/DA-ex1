package Process0;

import java.io.Serializable;

public class Message_Recording implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1239495766413646284L;
	public int destination;
	public int[] timestamp=new int[3];
	public Message_Recording(int a,int[] b) {
		destination=a;
		timestamp[0]=b[0];
		timestamp[1]=b[1];
		timestamp[2]=b[2];
	}

}
