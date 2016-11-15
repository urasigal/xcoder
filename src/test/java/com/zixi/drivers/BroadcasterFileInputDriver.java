package com.zixi.drivers;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.zixi.drivers.drivers.BroadcasterSystemDriver;
import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.FileManagerTools;

import static com.zixi.globals.Macros.*;


public class BroadcasterFileInputDriver extends BroadcasterLoggableApiWorker implements TestDriver{

	public String testIMPL(String userName,
			String userPass, String login_ip, String uiport, String type,
			String id, String matrix, String max_outputs, String mcast_out, String time_shift, String old, String fast_connect,
			String kompression, String enc_type, String enc_key, String path) {

		testParameters = new TestParameters("userName" + userName, "userPass" + userPass, "login_ip" + login_ip, "uiport" + uiport, "type" + type, "id" + id,
				"matrix" + matrix, "max_outputs" + max_outputs, "mcast_out" + mcast_out, "time_shift" + time_shift, "old" + old,
				"fast_connect" + fast_connect, "kompression" + kompression, "enc_type" + enc_type, "enc_key" + enc_key, "path" + path);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);

		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/add_stream.json?type=" +  type +  "&id=" + id + "&matrix=" + matrix + "&max_outputs=" + max_outputs + 
				"&mcast_out=" + mcast_out + "&time_shift=" + time_shift + "&old=" + old + "&fast-connect=" + fast_connect + "&kompression=" + kompression + 
				"&enc-type=" + enc_type + "&enc-key=" + enc_key + "&path=" + path, "", UDPMODE, responseCookieContainer, login_ip, this, uiport);
	}

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String id, String on) 
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);
		return apiworker.sendGet("http://" + login_ip + ":" + uiport + "/zixi/edit_stream.json?id=" + id + "&on=" + on, "", UDPMODE, responseCookieContainer, login_ip, this, uiport);
	}
	
	public String testIMPLRec(String userName, String userPass, String login_ip,
			String uiport, String id, String on) throws InterruptedException 
	{
		BroadcasterSystemDriver broadcasterSystemDriver = new BroadcasterSystemDriver();
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);
		String ret =  apiworker.sendGet("http://" + login_ip + ":" + uiport + "/set_live_recording.json?id=" + id + "&on=" + on, "", 77, responseCookieContainer, login_ip, this, uiport);
		
		File file = FileManagerTools.createFile("src/test/resources/cpu/" + id);
		for(int i = 0; i < 120; i++)
		{
			String cpuLoad = null;
			try {
				cpuLoad = broadcasterSystemDriver.getCpuFromBroadcaster(userName, userPass, login_ip, uiport);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("CPU Load is " + cpuLoad); 
			
			if (file != null)
			{
				try(PrintWriter output = new PrintWriter(new FileWriter("src/test/resources/cpu/" + id,true))) 
				{
				    output.println(cpuLoad);
				} 
				catch (Exception e) {}
			}
			Thread.sleep(1000);
		}
		//return ret;
		return "good";
	}
}
