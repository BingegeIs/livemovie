package bingege.movie.api.manage.user;

import bingege.movie.api.AbstractBaseTest;
import bingege.movie.api.manage.user.payload.AddUserQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractBaseTest {

    @Test
    public void add() throws Exception {
        AddUserQuery request = AddUserQuery.builder()
                .nickname("aaa")
                .password("123456")
                .username("aaa").build();

        mvc.perform(query(post("/api/user/"), request))
                .andExpect(jsonPath("$.data.nickname").value("aaa"));
    }
}