package com.tradevan.etc.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatRS {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
     * 
     * @return String that will be returned as a text/plain response.
     */
    @RequestMapping(method = RequestMethod.GET, value = {"", "/", "index"})
    public String index() {
        return ping_Get();
    }
	
	/**
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "pingGet")
	public String ping_Get() {
		logger.info("pingGet");
		return "pong, Service is ok..";
	}

	/**
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@RequestMapping(method = RequestMethod.POST, value="pingPost")
	public String ping_post() {
		logger.info("pingPost");
		return "pong, Service is ok..";
	}
}
