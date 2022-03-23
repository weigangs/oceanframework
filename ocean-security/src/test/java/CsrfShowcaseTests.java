//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//public class CsrfShowcaseTests {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @Test
//    @WithMockUser
//    public void postTest() throws Exception{
//        mvc
//                .perform(post("/").with(csrf()));
//    }
//}
