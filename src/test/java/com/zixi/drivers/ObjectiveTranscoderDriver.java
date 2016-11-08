package com.zixi.drivers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class ObjectiveTranscoderDriver extends BroadcasterLoggableApiWorker implements TestDriver
{

	public String testIMPL(String serverName, String userPass, String login_ip, String port) throws IOException {
		 
		 System.out.println("Connecting to " + serverName +" on port " + port);
	    
		 Socket client = new Socket(serverName, Integer.parseInt(port) );
	    
	     System.out.println("Just connected to " + client.getRemoteSocketAddress());
	     
	     OutputStream outToServer = client.getOutputStream();
	     DataOutputStream out = new DataOutputStream(outToServer);
	     out.writeUTF("connect");
	     InputStream inFromServer = client.getInputStream();
	     DataInputStream in = new DataInputStream(inFromServer);
	     String ret = in.readUTF();
	     System.out.println("Server says " + ret);
	     client.close();
		
		return ret;
	}

}
