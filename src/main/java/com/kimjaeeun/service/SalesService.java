package com.kimjaeeun.service;

import java.util.List;
import java.util.Map;

public interface SalesService {
	//List<Map<String,Object>>
	List<Map<String,Object>>getSalesListBy(String year);
}
