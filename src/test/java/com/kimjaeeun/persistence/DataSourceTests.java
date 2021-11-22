package com.kimjaeeun.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import com.kimjaeeun.sample.SampleTests;

//빈 테스트하는 파일
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
   @Autowired @Setter
   private DataSource dataSource;
   @Autowired @Setter
   private SqlSessionFactory sqlSessionFactory;
   
   @Test
   public void testExist() {
      assertNotNull(dataSource);
      log.info(dataSource);
   }
   //스프링에 빈으로 등록된 DateSource를 이용해서 Connection을 제대로 처리할 수 있는지 확인
   @Test
   public void testConnection() {
      try(Connection conn = dataSource.getConnection()) {
         log.info(conn);
      }catch (SQLException e) {
         fail(e.getMessage());
      }
   }
   
   @Test
   public void testMybatis(){
	   SqlSession session = sqlSessionFactory.openSession();
	   Connection conn= session.getConnection();
	   log.info(conn);
   }
}