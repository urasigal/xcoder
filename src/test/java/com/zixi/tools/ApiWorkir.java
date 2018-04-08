package com.zixi.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zixi.drivers.ReceiverInputStatisticDriver;
import com.zixi.testing.ReceiverInputStatisticTest;

import static com.zixi.globals.Macros.*;

public class ApiWorkir {

	protected JSONObject json = null;
	protected final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";
	private String tester = null;
	protected HttpURLConnection con;
	protected 			HttpURLConnection 	httpConnection;
	protected String URL = null;
	// HTTP GET request
	
	
	public String sendGet(String url, String id, int mode, String[] responseCookieContainer, String HOST, Object caller, String uiport) {
		StringBuffer response = new StringBuffer();
		try {
			URL destUrl 		= new URL(url);
			httpConnection 		= (HttpURLConnection) destUrl.openConnection();
			// Setup parameters and general request properties that you may need before connecting.
			///////////////////////////////////////////////////////////////////////////////////////
			httpConnection.setReadTimeout(80000);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			httpConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			httpConnection.setRequestProperty("Host", HOST + ":" + uiport);
			httpConnection.setRequestProperty("User-Agent", USER_AGENT);
			httpConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpConnection.setRequestProperty("Referer", "http://" + HOST + ":" + uiport + "/index.html");
			httpConnection.setRequestProperty(StringUtils.substringBetween(responseCookieContainer[0], "=", "%"), StringUtils.substringAfter(responseCookieContainer[0], "%3D"));
			httpConnection.setRequestProperty("Cookie", responseCookieContainer[1] + "; " + responseCookieContainer[0]);
			///////////////////////////////////////////////////////////////////////////////////////
			
			InputStream inputStream = null;
			
			try {
				if ("gzip".equals(httpConnection.getContentEncoding())) {
					inputStream = new GZIPInputStream(httpConnection.getInputStream());
				}else
				if("deflate".equals(httpConnection.getContentEncoding()))
				{
					inputStream = new InflaterInputStream(httpConnection.getInputStream(), new Inflater());
				}
				else {
					inputStream = httpConnection.getInputStream();
				}
			} catch (IOException e) {
				
					InputStream es;
					if ("gzip".equals(httpConnection.getContentEncoding())) {
						es = new BufferedInputStream(new GZIPInputStream(httpConnection.getErrorStream()));
					}
					else {
						es = new BufferedInputStream(httpConnection.getErrorStream());
					}

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					try {
						// read the response body
						byte[] buf = new byte[1024];
						int read = -1;
						while ((read = es.read(buf)) > 0) {
							baos.write(buf, 0, read);
						}
					} catch (IOException e1) {
						System.out.println( "IOException when reading the error stream. Igored");
					}

					// close the errorstream
					es.close();

					throw new IOException("Error while reading from " + httpConnection.getRequestMethod() + ": [" + httpConnection.getResponseCode() + "] "
							+ httpConnection.getResponseMessage() + "\n" + new String(baos.toByteArray(), "UTF-8"), e);
				
			}
			/////////////////////////////////////////////////////////////////////////////////////
			if(inputStream == null)
				return null;  
			
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			if (mode == RECEIVERIDMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				JSONArray streams = json.getJSONArray("streams");

				for (int i = 0; i < streams.length(); i++) {
					json = streams.getJSONObject(i);

					if (json.get("name").toString().equals(id)) {
						System.out.println(json.get("name").toString());
						return json.get("id").toString();
					}
				}
				System.out.println("no such a stream to delete");
				return "no such a stream to delete";
			}

			if (mode == PUSHMODE) {
				inputLine = response.toString();
				int indx = inputLine.indexOf("(");
				inputLine = (inputLine.substring(indx + 1,
						inputLine.indexOf(");")));
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}

			
			if (mode == ADD_TRANSCODER_PROFILE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}
			
			if (mode == PUSHINMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}
			
			if (mode == RECEIVER_UDP_OUT_MODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("message").toString();
			}
			

			if (mode == PULLMODE) {
				inputLine = response.toString();
				int indx = inputLine.indexOf("(");
				inputLine = (inputLine.substring(indx + 1,
						inputLine.indexOf(");")));
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}

			if (mode == UDPMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}

			if (mode == RECEIVERMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("message").toString();
			}

			if (mode == RECEIVERDELETIONMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("message").toString();
			}

			// under construction
			if (mode == RECEIVERSTATISTICMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.getJSONObject("data").get("bitrate").toString();
			}

			if (mode == UDPOUTMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				tester = json.getString("msg");
				if (tester.endsWith("Output " + id + " added.")) {
					return tester = "good";
				}
			}

			if (mode == JSONMODE) {
				ArrayList<String> inputsStreamNames = new ArrayList();
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				JSONArray inputStreamsJsonArrayObj = json
						.getJSONArray("streams");
				int numberOfElementsInInputStreamsJsonArrayObj = inputStreamsJsonArrayObj
						.length();
				for (int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++) {
					json = inputStreamsJsonArrayObj.getJSONObject(i);
					inputsStreamNames.add(json.get("id").toString());
				}
				tester = "good";
			}
			
			if(mode == SET_RTMMP_AUTO_REMOTE)
			{
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.getJSONObject("http_outs").getInt("rtmp_auto_out") + "";
				
			}
			
			if (mode == 77) {
				return inputLine = response.toString();
			}

			if (mode == PUSHOUTMODE) {
				String wholeResult;
				String[] splittedResults;
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				System.out.println("Debug printing   -- "
						+ json.get("msg").toString());
				wholeResult = json.get("msg").toString();
				splittedResults = wholeResult.split(",");
				return splittedResults[0];
			}
			

		} catch (Exception e) {
			String exceptionTest = e.getMessage();
			System.out.println("bug ------------- " + exceptionTest + "Request is "   + URL );
		}
		finally {
			httpConnection.disconnect();
		}
		return response.toString();
	}

}