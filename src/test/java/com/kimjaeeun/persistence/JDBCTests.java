package com.kimjaeeun.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
   static {
      try {
    	  //forName() : 물리적인 클래스 파일명을 인자로 넣어주면 이에 해당하는 클래스를 반환
         Class.forName("oracle.jdbc.driver.OracleDriver");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
   
   @Test
   public void testConnectioin() {
      try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BOOK_EX", "BOOK_EX")) {
         log.info(conn);
         
      }
      catch(Exception e) {
         fail(e.getMessage());
      }
   }
}
