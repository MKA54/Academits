package com.example.servingwebcontent;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoadOperator() throws Exception {
        this.mockMvc.perform(get("/operator"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Список заданий для прозвона клиентов.")))
                .andExpect(content().string(containsString("Фильтр по номеру заказа")));
    }

    @Test
    public void operatorJobList() throws Exception {
        this.mockMvc.perform(get("/operator"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.xpath("/html/body/table").nodeCount(1))
                .andExpect(MockMvcResultMatchers.xpath("/html/body/table/thead/tr/th").nodeCount(2))
                .andExpect(MockMvcResultMatchers.xpath("/html/body/table/tbody/tr/th").nodeCount(6));
    }

    @Test
    public void ordersFilter() throws Exception {
        this.mockMvc.perform(post("/filter").param("filter", "111"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.xpath("/html/body/table").nodeCount(1))
                .andExpect(MockMvcResultMatchers.xpath("/html/body/table/thead/tr/th").nodeCount(2))
                .andExpect(MockMvcResultMatchers.xpath("/html/body/table/tbody/tr/th").nodeCount(2));
    }

    @Test
    public void addOrder() throws Exception {
        this.mockMvc.perform(post("/").param("add", "444"))
                .andDo(print());
    }
}