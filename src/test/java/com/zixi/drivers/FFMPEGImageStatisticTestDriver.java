package com.zixi.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.testng.Reporter;

import com.zixi.drivers.tools.DriverReslut;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FFMPEGImageStatisticTestDriver extends BroadcasterLoggableApiWorker implements TestDriver {

	private static final String 	hostName      	   = "10.7.0.150";
	private static final int    	portNumber    	   = 4445;
	private static final int    	attempts      	   = 10;
	private static final int    	hlsAttempts   	   = 200;
	private static final String 	fromUser 	 	   = "get";
	private static final int   		negativeAttempts   = 5;
	private Socket 					clientSocket;
	private PrintWriter 			out;
	private BufferedReader 			in;
	private int 					loopCnt;

	public DriverReslut getInpoutStreamDetails()
	{
		// TODO Auto-generated method stub
		String fromServer 	= 	null;
		StringBuffer serverAnswer = new StringBuffer();
		try {
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
            out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			fromServer = in.readLine();
			
			if (fromServer.equals("connected"))
			{
				out.println("analyze");
				fromServer = in.readLine();
				System.out.println("Expected is output " + fromServer);
				
				if(fromServer.equals("output"))
				{
					fromServer = in.readLine();
					System.out.println("Expected is number " + fromServer);
					
					fromServer = in.readLine();
					serverAnswer.append(fromServer);
					System.out.println("Expected is accepted " + fromServer);
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
		}
		finally{
			try {
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driverReslut  = new	DriverReslut("good");
		driverReslut.touchResutlDescription(serverAnswer.toString());
		return driverReslut;
	}
	
	public String testStatistic() 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
	
		try {
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
            out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < attempts; loopCnt ++ )
				{
					out.println(fromUser);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted " + fromServer);
					}
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
		}
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ((sum / attempts) >= 0.9)
		{
			return "good";
		}
		else 
			return "bad";
	}
	
	public String testStatistic(String mode) 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
		try {
			// This class implements client sockets (also called just "sockets"). A socket is an endpoint for communication between two machines.
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
            out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < hlsAttempts; loopCnt ++ )
				{
					out.println(mode);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number, actual is: " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted, actual is: " + fromServer);
					}
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
		}
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ((sum / attempts) >= 0.9)
		{
			return "good";
		}
		else 
			return "bad";
	}
	
	public String testStatistic(boolean isNegative) 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
		try {

			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
             out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < negativeAttempts; loopCnt ++ )
				{
					out.println(fromUser);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number, actual is: " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted, actual is: " + fromServer);
					}
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
		}
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (sum == 0 )
		{
			return "good";
		}
		else 
			return "bad";
	}
}
