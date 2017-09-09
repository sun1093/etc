package com.tradevan.etc.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileEntity implements DataLoader {

	@Autowired
	private ObjectMapper mapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("classpath:sampleData/highway_event.json")
	private Resource jsonData;

	@Override
	public List getData(Map<String, Object> conds) {

		try {
			String data = new String(Files.readAllBytes(jsonData.getFile().toPath()));
			List result = mapper.readValue(data, List.class);
			return result;
		} catch (IOException e) {
			logger.error("get file from source", e);
		} 

//		InputStream resourceAsStream = null;
//
//		try {
//			resourceAsStream = getClass().getClassLoader().getResourceAsStream(dataSourceFilePath);
//			Map result = mapper.readValue(resourceAsStream, Map.class);
//			return result;
//		} catch (IOException e) {
//			logger.error("get file from " + dataSourceFilePath, e);
//		} finally {
//			if (resourceAsStream != null) {
//				try {
//					resourceAsStream.close();
//				} catch (IOException e) {
//				}
//			}
//		}

		return null;
	}

}
