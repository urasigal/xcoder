package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.*;

import com.zixi.drivers.*;


public class FeederUdpOutToFfmpegTest extends BaseTest{
	
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new FeederUdpOutToFfmpegDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name", "mip",
			"port", "ip", "prog", "chan", "oh",
			"op", "onic", "ottl", "osmooth" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String name, String mip,
			String port, String ip, String prog, String chan, String oh,
			String op, String onic, String ottl, String osmooth,String testid) throws InterruptedException {
		this.testid = testid;
		
		testParameters = buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "name", "mip",
				"port", "ip", "prog", "chan", "oh",
				"op", "onic", "ottl", "osmooth" ,"testid"}, 
				
				new String[] { userName, userPass, login_ip, uiport, name, mip,
				port, ip, prog, chan, oh,
				op, onic, ottl, osmooth ,testid });
		
		
		Assert.assertEquals(
				((FeederUdpOutToFfmpegDriver) testDriver).testIMPL(
						 userName,  userPass,  login_ip,  uiport,  name,  mip,
						 port,  ip,  prog,  chan,  oh,
						 op,  onic,  ottl,  osmooth), "UDP output added.");
	}

}
