package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.ApiWorkir;

public class BroadcasterUdpOutputCreationDriver extends BroadcasterLoggableApiWorker
		implements TestDriver {

	private ApiWorkir streamCreator = new ApiWorkir();

	public String testIMPL(String userName, String userPass, String loin_ip,
			String port, String stream, String streamname, String host,
			String id, String rtp, String fec, String smoothing, String ttl,
			String remux_bitrate, String df, String local_port, String dec_key,
			String type, String rows, String remux_buff, String local_ip,
			String remux_restampdts, String uiport, String remux_pcr,
			String dec_type, String cols) 
	{
		testParameters = new TestParameters("userName:"+ userName, "userPass:"+ userPass, "loin_ip:"+ loin_ip,
				"port:"+ port, "stream:"+ stream, "streamname:"+ streamname, "host:"+ host,
				"id:"+ id, "rtp:"+ rtp, "fec:"+ fec, "smoothing:"+ smoothing, "ttl:"+ ttl,
				"remux_bitrate:"+ remux_bitrate, "df:"+ df, "local_port:"+ local_port, "dec_key:"+ dec_key,
				"type:"+ type, "rows:"+ rows, "remux_buff:"+ remux_buff, "local_ip:"+ local_ip,
				"remux_restampdts:"+ remux_restampdts, "uiport:"+ uiport, "remux_pcr:"+ remux_pcr,
				"dec_type:"+ dec_type, "cols:"+ cols);
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + loin_ip + ":" + uiport + "/login.htm", userName , userPass, loin_ip, uiport);
		
		return streamCreator.sendGet(HTTP + loin_ip + ":" + uiport + params7 + rtype + "=" + type+
				"&" + rid + "=" + id  + "&" + rname + "=" + streamname + "&" + rstream + "=" + stream + "&" +
		rhost + "=" + host + "&" + rport + "=" + port + "&" + rsmoothing + "=" + smoothing + "&" +
		rttl + "=" + ttl + "&" + rdf +  "=" + df + "&" + rlocal_port + "=" + local_port + "&" +
		rlocal_ip + "=" + local_ip + "&" + rdec_type + "=" + dec_type + "&" + rdec_key + "=" + dec_key + "&" + rrtp + "=" + rtp + "&" +
		rfec + "=" + fec + "&" + rrows + "=" + rows + "&" + rcols + "=" + cols + "&" + rremux_bitrate + "=" + remux_bitrate + "&" +
		rremux_pcr + "=" + remux_pcr + "&" + rremux_buff + "=" + remux_buff +  "&" + rremux_restampdts + "=" + remux_restampdts, id, UDPOUTMODE, responseCookieContainer, loin_ip, this, uiport);
	}
	final private static String HTTP = "http://";
    final private static String params1 = ":";
    final private static String params2 = "&";
    final private static String params4 = "=";
    final private static String params5 = "ie_fooler=0.45086039789021015";
    final private static String params6 = "&smoothing=";
    final private static String params7 = "/zixi/add_output.json?";
    
	final private String rfunc = "func";
    final private String rtype = "type";
    final private String rid = "id";
    final private String rname = "name";
    final private String rstream = "stream";
    final private String rhost = "host";
    final private String rport = "port";
    final private String rsmoothing = "smoothing";
    final private String rttl = "ttl";
    final private String rdf = "df";
    final private String rlocal_port = "local-port";
    final private String rlocal_ip = "local-ip";
    final private String rdec_type = "dec-type";
    final private String rdec_key = "dec-key";
    final private String rrtp = "rtp";
    final private String rfec = "fec";
    final private String rrows = "rows";
    final private String rcols = "cols";
    final private String rremux_bitrate = "remux_bitrate";
    final private String rremux_pcr = "remux_pcr";
    final private String rremux_buff = "remux_buff";
    final private String rremux_restampdts = "remux_restampdts";

}
