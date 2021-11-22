package com.kimjaeeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
import	com.kimjaeeun.domain.SampleVo;
import com.kimjaeeun.domain.Ticket;

//Json 형태로 객체 데이터를 반환
@RestController
@RequestMapping("/sample2")
@Log4j
public class Sample2Controller {
	//@GetMapping GET 요청 방식의 API를 만들때 사용
   @GetMapping(value= "getText", produces="text/plain; charset=utf-8")
   public String getText() {
      log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
      return "안녕하세요";
   }
   
   @GetMapping(value ="getSample", produces={MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
   public SampleVo getSample() {
      return new SampleVo(112, "스타", "로드");
   }
   
   @GetMapping("getList")
   public List<SampleVo> getList(){
	   return IntStream.range(1,10).mapToObj(i->new SampleVo(i,i+"First",i+ "Last")).collect(Collectors.toList());
   }
   
   @GetMapping("getMap")
   public Map<String, SampleVo> getMap() {
      Map<String, SampleVo> map = new HashMap<>();
      map.put("First", new SampleVo(111, "그루트", "주니어"));
      return map;
   }
   
//   @GetMapping("product/{cat}/{pid}")
//   public String[] getPath (@PathVariable("category") String cat, @PathVariable Integer pid
//		   ,String test
//		   ,SampleVo vo){
//	   return new String[]{"category : "+cat, "productId: " + pid};
//   }
   @GetMapping("product/{cat}/{pid}")
   public String[] getPath (@PathVariable("category") String cat,
		   @PathVariable("productId") Integer pid){
	   return new String[]{"category : "+cat, "productId: " + pid};
   }
   @PostMapping("ticket")
   public Ticket convert(@RequestBody Ticket ticket){
	   log.info("convert...::"+ticket);
	   return ticket;
   }
}