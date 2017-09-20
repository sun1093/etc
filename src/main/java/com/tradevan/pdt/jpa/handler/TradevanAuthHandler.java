package com.tradevan.pdt.jpa.handler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tradevan.commons.lang.StringUtil;
import com.tradevan.util.TvEncrypt;

@Component
public class TradevanAuthHandler implements AuthHandler {

	private Logger logger = LoggerFactory.getLogger(TradevanAuthHandler.class);

	private String apId;
	private String userId;
	private String id;
	private String password;
	private boolean isEncrypt;

	@Override
	public void init(Properties prop) {
		apId = prop.getProperty("datasource.application.id");
		userId = prop.getProperty("datasource.username", "");
		String authFilePath = prop.getProperty("datasource.auth.file");
		try {
			isEncrypt = Boolean.parseBoolean(prop.getProperty("datasource.encrypted", "false"));
		} catch (Exception e) {
		}

		if (!(authFilePath == null || authFilePath.trim().length() == 0))
			readCfg(authFilePath);
		// logger.debug(toString());
	}

	@Override
	public String toString() {
		String s = null;
		if (isEncrypt) {
			return "TradevanAuthHandler [logger=" + logger + ", apId=" + apId + ", userId=" + userId + ", id=" + id
					+ ", password=" + TvEncrypt.decode(this.password) + ", isEncrypt=" + isEncrypt + "]";
		} else {

			return "TradevanAuthHandler [logger=" + logger + ", apId=" + apId + ", userId=" + userId + ", id=" + id
					+ ", password=" + password + ", isEncrypt=" + isEncrypt + "]";
		}

	}

	public void readCfg(String conf) {
		BufferedReader br = null;
		try {
			try {
				br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(conf)));
			}catch(Exception e) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(conf)));
			}
			String line = null;
			while ((line = br.readLine()) != null)
				if (line.startsWith(apId)) {
					String[] arr = StringUtil.splitStr2Array(line, " ");
					if ((StringUtil.isEmpty(userId)) || (arr[1].equalsIgnoreCase(userId))) {
						setId(arr[1]);
						setPassword(arr[2]);
						break;
					}
				}
		} catch (Exception e) {
			logger.error("read " + conf + " failed! ", e);
			try {
				if (br != null)
					br.close();
			} catch (Exception localException1) {
			}
			System.exit(-1);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception localException2) {
			}
		}
	}

	private void setPassword(String pwd) {
		this.password = pwd;
	}

	private void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getPassword() {
		if (isEncrypt) {
			return TvEncrypt.decode(this.password);
		} else {
			return this.password;
		}
	}
}