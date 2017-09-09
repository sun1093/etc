package com.tradevan.boot;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Srping boot server
 * 
 * @author 2775
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class BootServer {

	private static Logger logger = LoggerFactory.getLogger(BootServer.class);

	private static boolean isRunning = true;

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) throws InterruptedException {
		int port = 8085;
		if (args != null && args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		System.out.println("Start Boot Server Port = " + port);

		// 設定port
		HashMap<String, Object> props = new HashMap<String, Object>();
		props.put("server.port", port);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				logger.debug("ShutdownHook: Server Stop...");
				isRunning = false;
			} 
		});
		System.out.println("Server is starting...!");

		ConfigurableApplicationContext context = new SpringApplicationBuilder().sources(BootServer.class)
				.properties(props).run(args);

		while (isRunning) {
			Thread.sleep(3000L);
		}

		BootServer.stop();
		logger.debug("Server is Stoped!");
		System.exit(0);
	}

	public static void stop() {
		try {
			logger.debug("Server is stopping..");
			
			context.close();
		} catch (Exception e) {
			logger.error("Failed to Stop Server!", e);
			e.printStackTrace();
		}
	}

}
