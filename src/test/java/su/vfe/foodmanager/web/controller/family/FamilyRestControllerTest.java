package su.vfe.foodmanager.web.controller.family;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import su.vfe.foodmanager.service.FamilyService;
import su.vfe.foodmanager.web.controller.AbstractControllerTest;
import su.vfe.foodmanager.web.json.JsonUtil;
import java.util.Arrays;
import java.util.Collections;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static su.vfe.foodmanager.FamilyTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FamilyRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = FamilyRestController.REST_URL + '/';

    @Autowired
    private FamilyService service;

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + FAMILY1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(FAMILY1)));
    }

    //TODO: Change status check after implementing Exception handling
    @Test
    public void getNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + WRONG_ID))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(Arrays.asList(FAMILY1_WITH_USERS, FAMILY2_WITH_USERS))));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + FAMILY2_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("items", "users").isEqualTo(Collections.singletonList(FAMILY1));
    }

    @Test
    public void deleteNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + WRONG_ID))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }
}
