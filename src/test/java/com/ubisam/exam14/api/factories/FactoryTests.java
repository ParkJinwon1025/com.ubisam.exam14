package com.ubisam.exam14.api.factories;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam14.domain.Factory;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class FactoryTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FactoryDocs docs;

    @Autowired
    private FactoryRepository factoryRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // CRUD - C
        mockMvc.perform(post("/api/factories").content(docs::newEntity, "factoryName1", "factoryDescription1"))
                .andDo(print()).andExpect(is2xx()).andDo(result(docs::context, "entity1"));

        // CRUD - R
        String uri = docs.context("entity1", "$._links.self.href");
        mockMvc.perform(post(uri)).andExpect(is2xx());

        // CRUD - U
        Map<String, Object> entity = docs.context("entity1", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity, "factoryName1234")).andExpect(is2xx());

        // CRUD - D
        mockMvc.perform(delete(uri)).andExpect(is2xx());
    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {

        List<Factory> result;
        boolean hasResult;

        List<Factory> factoryList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            factoryList.add(docs.newEntity("factoryName" + i, "factoryDescription" + i));
        }
        factoryRepository.saveAll(factoryList);

        // 1. 공장 이름 query
        JpaSpecificationBuilder<Factory> query = JpaSpecificationBuilder.of(Factory.class);
        query.where().and().eq("factoryName", "factoryName33");
        result = factoryRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "factoryName33".equals(u.getFactoryName()));
        assertEquals(true, hasResult);

        // 2. 공장 설명 query
        JpaSpecificationBuilder<Factory> query1 = JpaSpecificationBuilder.of(Factory.class);
        query1.where().and().eq("factoryDescription", "factoryDescription21");
        result = factoryRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "factoryDescription21".equals(u.getFactoryDescription()));
        assertEquals(true, hasResult);
    }

    // Search
    @Test
    public void contextLoads2() throws Exception {
        List<Factory> factoryList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            factoryList.add(docs.newEntity("factoryName" + i, "factoryDescription" + i));
        }
        factoryRepository.saveAll(factoryList);

        // 1. 이름으로 검색
        mockMvc.perform(post("/api/factories/search").content(docs::setSearchName, "factoryName2")).andExpect(is2xx());

        // 2. 설명으로 검색
        mockMvc.perform(post("/api/factories/search").content(docs::setSearchDescription, "factoryDescription22"))
                .andExpect(is2xx());

        // 3. 페이지네이션 - 8개씩 5페이지
        mockMvc.perform(post("/api/factories/search").param("size", "8")).andExpect(is2xx()).andDo(print());

        // 4. 페이지네이션 - 8개씩 5페이지
        mockMvc.perform(post("/api/factories/search").param("sort",
                "factoryName,desc")).andExpect(is2xx()).andDo(print());
    }

}
