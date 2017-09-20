package com.tradevan.etc.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tradevan.etc.entity.DataLoader;
import com.tradevan.etc.jpa.model.EtcPBSRoads;

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
    	
    	//for test
    	Map<String, Object> conds = new HashMap<String, Object>();
		conds.put("startDts", "2017-09-20 22:40:38.177");
		conds.put("endDts", "2017-09-20 23:45:38.177");
    	
		Object data = dataLoader.getData(conds);
		
		if(data==null) {
			return null;
		}
		
		if(data instanceof List) {
			return data;
		}
		
		if(data instanceof EtcPBSRoads) {
			return ((EtcPBSRoads)data).getResult();
		}
		
		return data;
    }

}
