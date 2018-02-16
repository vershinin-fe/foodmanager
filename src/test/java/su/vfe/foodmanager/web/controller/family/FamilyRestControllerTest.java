package su.vfe.foodmanager.web.controller.family;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import su.vfe.foodmanager.web.controller.AbstractControllerTest;
import su.vfe.foodmanager.web.json.JsonUtil;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static su.vfe.foodmanager.FamilyTestData.*;

public class FamilyRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = FamilyRestController.REST_URL + '/';

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + FAMILY1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(FAMILY1)));
    }
}
