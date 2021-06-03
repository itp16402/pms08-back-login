package org.pms.samlogin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pms.samlogin.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=RegistrationControllerTest",
        "spring.jmx.default-domain=RegistrationControllerTest"})
public class RegistrationControllerTest extends BasicWiremockTest {

    @Ignore
    @Test
    public void register() throws Exception {

        UserDto userDto = UserDto.builder()
                .username("apatsimas")
                .password("p@t$!9!4")
                .firstName("ΑΝΤΡΕΑΣ")
                .lastName("ΠΑΤΣΙΜΑΣ")
                .email("andreas-patsim@hotmail.com")
                .build();

        this.mockMvc.perform(
                post(CONTEXT_PATH + "/register").contextPath(CONTEXT_PATH)
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
