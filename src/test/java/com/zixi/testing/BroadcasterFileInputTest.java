package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterFileInputTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterFileInputDriver();
	}
	
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "type", "id",
			"matrix", "max_outputs", "mcast_out", "time_shift", "old",
			"fast_connect", "kompression", "enc_type", "enc_key", "path","testid" })
	@Test
	public void broadcasterCreateFileInout(String userName,
			String userPass, String login_ip, String uiport, String type,
			String id, String matrix, String max_outputs, String mcast_out, String time_shift, String old, String fast_connect,
			String kompression, String enc_type, String enc_key, String path, String testid) throws InterruptedException {

		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id",
				"matrix", "max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression", "enc_type", "enc_key", "path","testid"}, 
				
				new String[] {userName, userPass, login_ip, uiport, type, id,
				matrix, max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, enc_key, path, testid });
		
		
		Assert.assertEquals(((BroadcasterFileInputDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, uiport, type, id,
						matrix, max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, enc_key, path),
				"Stream " + "'" + id + "'" + " added.");
	}
	
	
	
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "on","testid" })
	@Test
	public void broadcasterStopFileInout(String userName,
			String userPass, String login_ip, String uiport,
			String id, String on, String testid) throws InterruptedException {
	
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id","on", "testid"}, 
												  new String[] {userName, userPass, login_ip, uiport, id, on, testid });
		
		Assert.assertEquals(((BroadcasterFileInputDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, id, on),
				"Applied new configuration to " + id);
}
	
	
	/// Used in multiple file recording testing.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "on", "cpuFolder", "testid" })
	@Test
	public void broadcasterRecordInout(String userName, String userPass, String login_ip, String uiport,String id, String on, String cpuFolder, String testid)
	throws InterruptedException {
		
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id","on", "cpuFolder", "testid"}, 
		new String[] {userName, userPass, login_ip, uiport, id, on, cpuFolder, testid });
		
		Assert.assertEquals(((BroadcasterFileInputDriver) testDriver).testIMPLRec(userName, userPass, login_ip, uiport, id, on, cpuFolder), "added");
		
	}
}