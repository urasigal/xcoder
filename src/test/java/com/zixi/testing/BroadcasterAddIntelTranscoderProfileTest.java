package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterAddIntelTranscoderProfileTest extends BaseTest {

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterAddTranscoderProfileDriver();
	}

	@Parameters({"userName", "userPass", "login_ip", "uiport", "mode", "profile_name", "enc", "bitrate", "gop", "fixed_gop", "closed_gop", "copy_gop",  
	"performance", "b_frames", "frame_type", "profile", "level", "bitrate_mode",
	"ref_frames", "hrd", "idr_int", "cavlc", "brightness", "contrast", "fps", "width", "height","crf", "tune", "use_hw", "max_bitrate", "testid" })
	@Test
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName, String userPass, String login_ip, String uiport,String mode, String profile_name,
	String enc, String bitrate, String gop, String fixed_gop, String closed_gop, String copy_gop, String performance,
	String b_frames, String frame_type, String profile, String level, String bitrate_mode,
	String ref_frames, String hrd, String idr_int, String cavlc, String brightness, String contrast, String fps,
	String width, String height, String crf, String tune, String use_hw,  String max_bitrate, String testid) throws InterruptedException {

		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
				
		testParameters = buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "mode", "profile_name",
		"enc", "bitrate", "gop", "fixed_gop", "closed_gop", "copy_gop",  "performance",
		"b_frames", "frame_type", "profile", "level", "bitrate_mode", "ref_frames", "hrd", "idr_int", "cavlc", "brightness", "contrast", "fps",
		"width", "height","crf", "tune", "use_hw",  "max_bitrate", "testid"}, 
						
		new String[] {userName, userPass, login_ip, uiport, mode, profile_name,
		enc, bitrate, gop, fixed_gop, closed_gop, copy_gop, performance,
		b_frames, frame_type, profile, level, bitrate_mode, ref_frames, hrd, idr_int, cavlc, brightness, contrast, fps,
		width, height,crf, tune, use_hw,  max_bitrate, testid});
		
		Assert.assertEquals(((BroadcasterAddTranscoderProfileDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, mode, profile_name,
		enc, bitrate, gop, fixed_gop, closed_gop, copy_gop, performance, b_frames, frame_type, profile, level, bitrate_mode,
		ref_frames, hrd, idr_int, cavlc, brightness, contrast, fps, width, height,crf, tune, use_hw, max_bitrate), "Profile added, existing transcoded streams may be restarted.");
	}
}
