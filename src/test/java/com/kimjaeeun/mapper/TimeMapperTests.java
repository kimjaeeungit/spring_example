package com.kimjaeeun.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimjaeeun.persistence.DataSourceTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	@Autowired @Setter
	private TimeMapper mapper;
	
	@Test
	public void testExist(){
		assertNotNull(mapper);
		log.info(mapper);
	}
	
	@Test
	public void testGetTime(){
		log.info("testGetTime:"+mapper.getTime());
	}
	
	@Test
	public void testGetTime2(){
		String time = mapper.getTime2();
		log.info("testGetTime2:"+time);
		log.trace("testGetTime2:"+time);
		log.debug("testGetTime2:"+time);
		log.warn("testGetTime2:"+time);
		log.error("testGetTime2:"+time);
		log.fatal("testGetTime2:"+time);
	}
}
