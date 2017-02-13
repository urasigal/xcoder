package com.zixi.tools;

import com.zixi.drivers.tools.DriverReslut;
import com.zixi.entities.TestParameters;


public class BroadcasterLoggableApiWorker {
	
	protected 	ApiWorkir 							apiworker 							= new ApiWorkir();
	
	protected 	TestParameters 						testParameters;
	
	protected 	BroadcasterInitialSecuredLogin 		broadcasterInitialSecuredLogin 		= new BroadcasterInitialSecuredLogin();
	
	protected 	String 								responseCookieContainer[] 			= new String[2];
	
	protected 	DriverReslut 						driverReslut 						= 	null;
}
