package com.kimjaeeun.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kimjaeeun.mapper.SalesMapper;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class SalesServiceImpl implements SalesService {
   private SalesMapper salesMapper;

   @Override
   public List<Map<String, Object>> getSalesListBy(String year) {
      return salesMapper.getSalesListBy(year);
   }
}