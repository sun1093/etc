package com.tradevan.etc.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tradevan.etc.entity.DataLoader;

@RestController
public class DataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DataLoader dataLoader;
	
    /**從後端取得資料
     * @return
     */
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = {"get_ad"})
    public Object getAllData() {
    	logger.info("get AllData..");
        return dataLoader.getData(null);
    }

}
