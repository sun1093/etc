package com.tradevan.etc.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MapService {

	/**
	 * show map view
	 * 
	 * @return
	 */
	@RequestMapping("/showMap")
	public String showMap(Model model) {
		return "etc_map";
	}
}
