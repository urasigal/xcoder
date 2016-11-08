package com.zixi.testing;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;
import com.zixi.drivers.*;
import com.zixi.ssh.SshJcraftClient;

public class ObjectiveTranscoderTest extends BaseTest{
	
	private SshJcraftClient sshJcraftClient;
	
	@BeforeClass
	public void testInit() {
		sshJcraftClient = new SshJcraftClient();
		testDriver = new ObjectiveTranscoderDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "port", "sshUser", "sshPassword", "sshPort", "command" , "command2", "testid" })
	@Test
	public void objectiveTest(String userName, String userPass, String login_ip, String port, 
							String sshUser, String sshPassword, String sshPort, String command, String command2, String testid) throws InterruptedException, IOException, JSchException {

		this.testid = testid;
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		//this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		testParameters = buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "sshUser", "sshPassword", "sshPort", "command", "command2", "testid"  }, 
						new String[] {userName, userPass, login_ip, port, sshUser, sshPassword, sshPort, command, command2, testid });
		
		sshJcraftClient.performCommand(sshUser, sshPassword, login_ip, sshPort, command);
		sshJcraftClient.performCommand(sshUser, sshPassword, login_ip, sshPort, command2);
		Thread.sleep(120000);
		Assert.assertEquals(
				((ObjectiveTranscoderDriver) testDriver).testIMPL(userName, userPass, login_ip, port), "connect");
		
	}
}
