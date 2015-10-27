package uno.cod.platform.server.rest.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uno.cod.platform.server.Server;
import uno.cod.platform.server.core.config.JpaConfig;
import uno.cod.platform.server.core.config.SecurityConfig;
import uno.cod.platform.server.core.dto.user.UserCreateDto;
import uno.cod.platform.server.core.repository.UserRepository;
import uno.cod.platform.server.rest.RestUrls;

import static org.junit.Assert.assertNotNull;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Server.class)
public class UserControllerTest {
    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("build/generated-snippets");

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private UserRepository userRepository;

    ObjectMapper mapper;
    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void createUser() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setNick("testnick");
        userCreateDto.setEmail("test@ŧest.at");
        userCreateDto.setPassword("testitest");

        mockMvc.perform(post(RestUrls.USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userCreateDto))
                ).andExpect(status()
                .isCreated())
                .andDo(document("index", requestFields(
                        fieldWithPath("nick").type(JsonFieldType.STRING).description("The user's nick name"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("The user's email address"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("The user's password")
                        )));
        assertNotNull(userRepository.findByUsername("testnick"));

    }

}