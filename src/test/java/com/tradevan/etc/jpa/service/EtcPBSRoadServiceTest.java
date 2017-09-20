package com.tradevan.etc.jpa.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradevan.boot.BootServer;
import com.tradevan.etc.entity.DataLoader;
import com.tradevan.etc.jpa.model.EtcPBSRoad;
import com.tradevan.etc.jpa.model.EtcPBSRoads;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootServer.class)
public class EtcPBSRoadServiceTest {

	private static final int List = 0;

	// DI
	@Autowired
	@Qualifier("EtcPBSRoadService")
	private EtcPBSRoadService service;

	@Autowired
	@Qualifier("FileEntity2")
	private DataLoader dataLoader;

	@Autowired
	private ObjectMapper mapper;

	/**
	 * 絕對找不到資料
	 */
	@Test
	public void testGetDayUserProfNoData() {
		List<EtcPBSRoad> dayUserProf = service.getDataBetweenDts("20160913", "20160914");
		Assert.assertTrue(dayUserProf != null);
		Assert.assertEquals(dayUserProf.size(), 0);
	}

	@Test
	public void insertData() throws JsonParseException, JsonMappingException, IOException {
		Object data = dataLoader.getData(null);
		Assert.assertTrue(data == null);

		Map<String, Object> conds = new HashMap<String, Object>();

		conds.put("startDts", "2017-09-20 22:40:38.177");
		conds.put("endDts", "2017-09-20 23:45:38.177");
		
		data = dataLoader.getData(conds);
		
		Assert.assertTrue(data instanceof EtcPBSRoads);
		
		EtcPBSRoads d = (EtcPBSRoads) data;
		
		System.out.println(d.getResult().size());
		//
		//
		// EtcPBSRoad road = d.getResult().get(0);
		//
		// road.setId(road.getUID());
		// System.out.println(road);
		//
		// service.insert(d);
		//
		// EtcPBSRoad nEvent = service.findById(road.getUID());
		// Assert.assertNotNull(nEvent);
		;
	}

}
