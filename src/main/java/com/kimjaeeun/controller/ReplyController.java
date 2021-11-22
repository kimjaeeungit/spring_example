package com.kimjaeeun.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import com.kimjaeeun.domain.ReplyCriteria;
import com.kimjaeeun.domain.ReplyVo;
import com.kimjaeeun.service.ReplyService;

@RestController
@AllArgsConstructor
@RequestMapping("/replies")
@Log4j
public class ReplyController {
  private ReplyService service;
  
  //댓글 작성
  @PostMapping("new")
  //@RequestBody:컨트롤러에서 데이터를 인자에 할당하는 대표적인 방법
  public String create(@RequestBody ReplyVo vo){
	  log.info("create::"+vo);
	  service.register(vo);
	  log.info(vo);
	  return "success";
  }
  
  //댓글 보기
  @GetMapping("{rno}")
  //@PathVariable : url경로에 변수 넣어주는거
  public ReplyVo get(@PathVariable Long rno){
	  log.info("get..::"+rno);
	  return service.get(rno);
  }
  
  //댓글 수정
  @PutMapping("{rno}")
  public String modify(@PathVariable Long rno,@RequestBody ReplyVo vo){
	  log.info("modify::"+vo);
	  service.modify(vo);
	  return "success";
  }
  
  //댓글 삭제
  @DeleteMapping("{rno}")
  public String remove(@PathVariable Long rno){
	  log.info("remove :: " +rno);
	  service.remove(rno);
	  return "success";
  }
  
  //
  @GetMapping({"{bno}/{amount}/{lastRno}"})
  public List<ReplyVo> getList(@PathVariable Long bno,
		  @PathVariable(required=false)  Optional<Long> lastRno,@PathVariable(required=false) Integer amount){
	  return service.getList(new ReplyCriteria(lastRno.get(),amount), bno);
  }
}