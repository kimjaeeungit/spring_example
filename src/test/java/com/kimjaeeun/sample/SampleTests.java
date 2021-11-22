package com.kimjaeeun.sample;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//현재 테스트 코드가 스프링을 실행하는 역할을 할것
@RunWith(SpringJUnit4ClassRunner.class) 
//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록(스프링 빈으로 등록)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//Lombok을 이용해 로그를 기록하는 Logger를 변수로 생성
@Log4j
public class SampleTests {
	@Setter 
	//해당 인스턴스 변수가 스프링으로부터 자동으로 주입해 달라는 표시
	@Autowired
	private Restaurant restaurant;
	static final Logger logger = Logger.getLogger(SampleTests.class);
	
	//JUnit에서 테스트 대상을 표시하는 어노테이션
	@Test 
	public void testExist(){
		//assertNotNull :: restairamt변수가 null이 아니어야만 테스트가 성공
		assertNotNull(restaurant);
		log.info(restaurant);
		log.info("=====================");
		log.info(restaurant.getChef());
		
		logger.info(restaurant);
		
		//테스트 결과가 의미하는 바>>
		//1)테스트 코드가 실행되기 위해서 스프링 프레임워크가 동작
		//2)동작하는 과정에서 필요한 객체들이 스프링에 등록
		//3)의존성 주입이 필요한 객체는 자동으로 주입이 이루어짐
	}
}
