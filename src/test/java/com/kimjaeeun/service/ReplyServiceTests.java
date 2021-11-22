package com.kimjaeeun.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimjaeeun.domain.Criteria;
import com.kimjaeeun.domain.ReplyCriteria;
import com.kimjaeeun.domain.ReplyVo;
import com.kimjaeeun.sample.SampleTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {
	@Setter @Autowired
	private ReplyService service;
	
	@Test
	public void testClass(){
		log.info(service);
		log.info(service.getClass().getSimpleName());
	}
	
	
	@Test
	public void testExist(){
		assertNotNull(service);
	}
	
	@Test
	public void testGetList(){
		service.getList(new ReplyCriteria(),294L).forEach(log::info);
	}
	@Test
	public void testRegister(){
		ReplyVo replyVo=new ReplyVo();
		replyVo.setReply("서비스 테스트 등록글 제목 트랜1");
		replyVo.setReplyer("서비스 테스터1");
		replyVo.setBno(294L);
		service.register(replyVo);
	}
	@Test
	public void testGet(){
		log.info(service.get(10L));
	}
	@Test
	public void testModify(){
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply("서비스 테스트 수정글 제목");
		replyVo.setReplyer("서비스 테스트 수정글 제목");
		replyVo.setRno(5L);
		service.modify(replyVo);
	}
	
	@Test
	public void testRemove(){
		log.info(service.remove(4L));
		
	}
}
