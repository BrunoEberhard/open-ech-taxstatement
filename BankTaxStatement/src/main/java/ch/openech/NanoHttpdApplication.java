package ch.openech;

import java.io.IOException;

import org.minimalj.application.Application;
import org.minimalj.application.DevMode;
import org.minimalj.frontend.Frontend;
import org.minimalj.frontend.impl.json.JsonFrontend;
import org.minimalj.frontend.impl.nanoserver.MjWebDaemon;
import org.minimalj.frontend.impl.nanoserver.MjWebSocketDaemon;

import fi.iki.elonen.NanoHTTPD;

public class NanoHttpdApplication {
	private static final boolean SECURE = true;
	private static final int TIME_OUT = 5 * 60 * 1000;
	
	private static boolean useWebSocket = Boolean.valueOf(System.getProperty("MjUseWebSocket", "false"));
		
	private static NanoHTTPD start() throws IOException {
		int port = Integer.valueOf(System.getenv("PORT"));
		if (port > 0) {
			System.out.println("Start web " + (useWebSocket ? "socket" : "") + " frontend on " + port);
			NanoHTTPD daemon = useWebSocket ? new MjWebSocketDaemon(port, false) : new MjWebDaemon(port, false);
			daemon.start(TIME_OUT);
			return daemon;
		} else {
			return null;
		}
	}
	
	private static void stop(NanoHTTPD daemon) {
		if (daemon != null) {
			System.out.println("Stop web frontend on " + daemon.getListeningPort());
			daemon.stop();
		}
	}
	
	public static boolean useWebSocket() {
		return useWebSocket;
	}
	
	public static void main(final String[] args) throws Exception {
		Frontend.setInstance(new JsonFrontend());
		Application.initApplication(args);
		
		System.out.println("Dev mode: " + DevMode.isActive());
		
		NanoHTTPD daemon = null;
        try {
        	daemon = start();
    		while (true) {
    	        try {
    	            Thread.sleep(1000);
    	        } catch (Throwable ignored) {
    	        }
    		}
        } finally {
//        	stop(secureDaemon);
//        	stop(daemon);
        }
	}

}
