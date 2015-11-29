package com.demo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.jdbc.Sql;

import com.demo.controller.DeepThoughtController;

// TODO: 1. Run the unit test with SpringJUnit4ClassRunner
// TODO: 2. Reference application context configurations for the test
// TODO: 6. list SQL files to be loaded using @Sql
// @Sql("schema.sql")
public class ApplicationTests {
	private static Logger log = LoggerFactory.getLogger(ApplicationTests.class);
	
	// TODO: 3. Copy the DatabaseConfig configuration to the nested class here 
	// and override the database contents for the test  
	// @Configuration
	//public static class TestDatabaseConfig {


	// TODO: 4. Autowire DeepThoughtController into the unit test
	private DeepThoughtController controller;
	
	
	@Sql(scripts={"schema.sql", "ApplicationTests.sql"})
	@Test
	public void testErmittleDieAntwort() {
		log.info("testErmittleDieAntwort()");
		
		// TODO: 5. compare DeepThoughtController answer with "Test"
		// assertEquals("Wrong Test", controller.ermittleDieAntwort().getAntwort());
	}


	// TODO: 7. Rely on not dirtying the context
	// @DirtiesContext
	// @Sql("schema.sql")
	// @Sql
	// @Test
	public void testErmittleDieAntwort2() {
		log.info("testErmittleDieAntwort2()");
		
		//assertEquals("Test", controller.ermittleDieAntwort().getAntwort());
	}

}
