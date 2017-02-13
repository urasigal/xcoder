package com.zixi.drivers;

import static com.zixi.globals.Macros.PULLMODE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.zixi.entities.StreamEntity;
import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterSinglePullInStreamCreationDriver extends
		BroadcasterLoggableApiWorker implements TestDriver {
	
	private StreamEntity streamEntity;
	private InputStream input = null;
	private Properties prop = null;
	private ApiWorkir streamCreator = new ApiWorkir();

	final private static String params5 = "ie_fooler=0.45086039789021015";

	final private String rfunc = "func";
	final private String ron = "on";
	final private String rpassword = "password";
	final private String rlatency = "latency";
	final private String rtype = "type";
	final private String rid = "id";
	final private String rmax_outputs = "max_outputs";
	final private String rmcast_out = "mcast_out";
	final private String rmcast_force = "mcast_force";
	final private String rmcast_ip = "mcast_ip";
	final private String rmcast_port = "mcast_port";
	final private String rmcast_ttl = "mcast_ttl";
	final private String rtime_shift = "time_shift";
	final private String rcomplete = "complete";
	final private String rpull_port = "pull-port";
	final private String rsource = "source";
	final private String rfec_overhead = "fec_overhead";
	final private String rfec_latency = "fec_latency";
	final private String rfec_aware = "fec_aware";
	final private String rfec_adaptive = "fec_adaptive";
	final private String rfec_force = "fec_force";
	final private String rnic = "nic";
	final private String rhost0 = "host0";

	
	// Perform a test.
	public String testIMPL(String userName, String userPass, String Host, String loin_ip, String id, String source, String uiport,
	String pull_port, String latency, String fec_latency, String fec_overhead, String mcast_force, String time_shift,
	String nic, String max_outputs, String type, String password, String mcast_port, String complete, String mcast_ip,
	String fec_adaptive, String mcast_ttl, String on, String func,String fec_force, String mcast_out, String propertiesFile) {
		
		testParameters = new TestParameters("userName:" + userName, "userPass:"+ userPass, "Host:" + Host, "loin_ip:" + loin_ip, "id:" + id,
		"source:" + source, "uiport:" + uiport, "pull_port:" + pull_port, "latency:" + latency, "fec_latency:"
		+ fec_latency, "fec_overhead:" + fec_overhead, "mcast_force:" + mcast_force, "time_shift:" + time_shift,
		"nic:" + nic, "max_outputs:" + max_outputs, "type:" + type, "password:" + password, "mcast_port:" + mcast_port, "complete:"
		+ complete, "mcast_ip:" + mcast_ip, "fec_adaptive:" + fec_adaptive, "mcast_ttl:" + mcast_ttl, "on:" + on,
		"func:" + func, "fec_force:" + fec_force, "mcast_out:" + mcast_out, "propertiesFile:" + propertiesFile);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + loin_ip + ":" + uiport + "/login.htm", userName,userPass, loin_ip, uiport);
		
		// this function creates a single pull stream 
		String  res = createPullInWithRandomName(userName, userPass, Host, loin_ip, id, source, uiport, pull_port, latency, fec_latency,
		fec_overhead, mcast_force, time_shift, nic, max_outputs, type, password, mcast_port, complete, mcast_ip, fec_adaptive,
		mcast_ttl, on, func, fec_force, mcast_out);
		
		return res;
	}

	public String createPullInWithRandomName(String userName, String userPass, String Host, String loin_ip, String id, String source,
	String uiport, String pull_port, String latency, String fec_latency, String fec_overhead, String mcast_force,
	String time_shift, String nic, String max_outputs, String type, String password, String mcast_port, String complete,
	String mcast_ip, String fec_adaptive, String mcast_ttl, String on, String func, String fec_force, String mcast_out) {
		
		String request = "http://" + loin_ip + ":" + uiport + "/zixi/add_stream.json?" + rfunc + "=" + func + "&" + rtype + "="
		+ type + "&" + rid + "=" + id + "&" + rmax_outputs + "=" + max_outputs + "&" + rmcast_out + "="
		+ mcast_out + "&" + rmcast_force + "=" + mcast_force + "&" + rmcast_ip + "=" + mcast_ip + "&"
		+ rmcast_port + "=" + mcast_port + "&" + rmcast_ttl + "=" + mcast_ttl + "&" + rtime_shift + "="
		+ time_shift + "&" + rhost0 + "=" + Host + "&" + rpull_port + "=" + pull_port + "&" + rsource
		+ "=" + source + "&" + rpassword + "=" + password + "&" + rlatency + "=" + "6000" + "&" + rnic
		+ "=" + nic + "&" + rfec_overhead + "=" + fec_overhead + "&" + rfec_latency + "=" + fec_latency
		+ "&" + rfec_aware + "=" + "" + "&" + rfec_adaptive + "=" + fec_adaptive + "&" + rfec_force + "="
		+ fec_force + "&" + rcomplete + "=" + complete+ "&" + ron + "=" + on + "&" + params5;
		
		System.out.println(request);
		return streamCreator.sendGet(request, id,PULLMODE, responseCookieContainer, loin_ip, this, uiport);
	}
}
