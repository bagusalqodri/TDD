package com.id.bsp.tdd.TDD;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import static com.jayway.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.id.bsp.tdd.TDD.model.UserModel;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TddApplication.class) 
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TddApplicationTests {
	@Value("${server.port}")
	int port;
	
	@Autowired
	WebApplicationContext context;
	
	private MockMvc mockmvc;
	
	@Before
	public void setup() {
		this.mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void redirectHomeTest() throws Exception{
		
		String getHome = new StringBuilder().append("/").toString();
		
		mockmvc.perform(get(getHome))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andDo(print());
	}
	
	@Test
	public void getContractTest() throws Exception {
		
		String getContractDuration = new StringBuilder().append("/api/tdd/{id}").toString();
		
		int id = 5;
		UserModel user = new UserModel();
		user.setId(id);
		user.setName("Bagus Al-Qodr");
		user.setContractDuration(10);
		
		Gson gson = new Gson();
		
		mockmvc.perform(get(getContractDuration,id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json(gson.toJson(user)))
		.andDo(print())
		.andReturn();
	}
	

}

