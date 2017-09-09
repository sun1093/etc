package com.tradevan.etc.entity;

import java.util.Map;

public interface DataLoader {

	/**
	 * 取得資料
	 * 
	 * @param conds
	 * @return
	 */
	public Object getData(Map<String, Object> conds);
}
