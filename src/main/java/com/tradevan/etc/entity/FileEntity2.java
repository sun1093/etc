package com.tradevan.etc.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradevan.etc.jpa.model.EtcPBSRoad;
import com.tradevan.etc.jpa.model.EtcPBSRoads;
import com.tradevan.etc.jpa.service.EtcPBSRoadService;

@Component()
@Qualifier("FileEntity2")
@Primary
// @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FileEntity2 implements DataLoader {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	@Qualifier("EtcPBSRoadService")
	private EtcPBSRoadService service;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("classpath:sampleData/highway_event.json")
	private Resource jsonData;

	//若有資料，此段參數可省略============
	private boolean begin = true;
	
	@Override
	public EtcPBSRoads getData(Map<String, Object> conds) {
		// 若有資料，此段開始可省略============
		try {
			if(begin) {
				String data = new String(Files.readAllBytes(jsonData.getFile().toPath()));
				data = data.replaceAll("\"UID\"", "\"uid\"");
				EtcPBSRoads d = mapper.readValue(data, EtcPBSRoads.class);

				service.insert(d);
				
				begin = false;
			}
			
			if(conds==null) {
				return null;
			}
			// 若有資料，此段為止可省略============
			String startDts = conds.get("startDts") == null ? null : (String) conds.get("startDts");
			String endDts = conds.get("endDts") == null ? null : (String) conds.get("endDts");
			
			if(startDts!=null && endDts!=null) {
				List<EtcPBSRoad> nEvent = service.getDataBetweenDts(startDts, endDts);
				EtcPBSRoads roads = new EtcPBSRoads();
				roads.setResult(nEvent);
				return roads;
			}
			return null;
		} catch (IOException e) {
			logger.error("get file from source", e);
		}

		return null;
	}

}
