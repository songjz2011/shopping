package shopping.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration(value = "src/main/webapp")
//@ContextConfiguration(locations = { "classpath:shopping-context.xml",
//    "classpath:shopping-context-db.xml",
//		"classpath:shopping-mvc.xml" })
public class UserControllerTest {

	//@Resource
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void before() {
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void hello_test() {
//		try {
//			MockHttpServletRequestBuilder builder = get("/users/list");
//			ResultActions actions = mockMvc.perform(builder);
//			actions.andExpect(status().isOk());
//			actions.andDo(print());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
