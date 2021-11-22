package com.kimjaeeun.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kimjaeeun.service.SalesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller @AllArgsConstructor @Log4j
public class SalesController {
	private SalesService salesService;
	
	@ResponseBody @GetMapping("sales/{year}")
	public List<Map<String,Object>> getSalesByYear(@PathVariable String year){
		return salesService.getSalesListBy(year);
	}
	
	@GetMapping("sales/chart/{year}")
	public String chart(@ModelAttribute @PathVariable String year){
		log.info("chart..." +year);
		return "sales/chart";
	}
}
