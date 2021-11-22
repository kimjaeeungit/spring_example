package com.kimjaeeun.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.kimjaeeun.mapper.BoardMapperTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
//Test Controller
//@WebAppConfiguration : Servlet의 ServletContext를 이용하기 위함
@WebAppConfiguration
public class BoardControllerTests {
//	@Autowired @Setter
//	private BoardController controller;
	
	@Autowired @Setter
	private WebApplicationContext ctx;
	private MockMvc mvc;
	
	@Before //@Before가 적용된 메서드는 모든 테스트 전에 매번 실행됨
	public void setup(){
		//가짜로 url과 파라미터등을 부라우저에서 사용하는것처럼 만들어서 Controller를 실행해볼 수 있음
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExist(){
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	//페이지번호에 따른 게시글 목록 출력
	@Test
	public void testList() throws Exception{
		//MockMvcRequestBuilders를 사용해 get방식으로 호출
		//MockMvcRequestBuilders를 사용해 설정한 요청 데이터를 perform()의 인수로 전달
		ModelMap map =mvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "10")
				)
		.andReturn()
		.getModelAndView()
		.getModelMap();
		
		//BoardController의 getList()에서 반환된 결과를 이용해서 Model에 어떤 데이터들이 담겨있는지 확인
		List<?> list= (List<?>)map.get("list");
		list.forEach(log::info);
	}
	
	//게시글 작성
	@Test
	public void testRegister() throws Exception{
		ModelAndView mav =mvc.perform(
				//post 방식으로 데이터 전달 
				MockMvcRequestBuilders.post("/board/register")
				//param이용해서 전달해야 하는 파라미터들 지정
				.param("title", "컨트롤러테스트 글 제목1")
				.param("content","컨트롤러 테스트 글 내용1")
				.param("writer", "컨트롤러 테스터1"))
			.andReturn()
			.getModelAndView();
		
		log.info(mav.getViewName());
	}
	
	//게시글 상세보기
	@Test
	public void testGet() throws Exception{
		ModelMap map =mvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno","263"))
		.andReturn()
		.getModelAndView()
		.getModelMap();
		log.info(map.get("board"));
	}
	
	//게시글 수정
	@Test
	public void testModify() throws Exception{
		ModelAndView mav =mvc.perform(
				MockMvcRequestBuilders.post("/board/modify")
				.param("title", "컨트롤러테스트 글 제목 수정12")
				.param("content","컨트롤러 테스트 글 내용 수정12")
				.param("writer", "컨트롤러 테스터122")
				.param("bno","263"))
			.andReturn()
			.getModelAndView();
		
		log.info(mav.getViewName());
	}
	
	//게시글 삭제
	@Test
	public void testRemove() throws Exception{
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "20"))
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
	}
}
