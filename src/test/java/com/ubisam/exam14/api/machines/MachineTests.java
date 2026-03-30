package com.ubisam.exam14.api.machines;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
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
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam14.domain.Machine;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class MachineTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MachineDocs docs;

    @Autowired
    private MachineRepository machineRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // CRUD - C
        mockMvc.perform(post("/api/machines").content(docs::newEntity, "기계명1", "4")).andExpect(is2xx()).andDo(print())
                .andDo(result(docs::context, "entity1"));

        // CRUD - R
        String uri = docs.context("entity1", "$._links.self.href");
        mockMvc.perform(get(uri)).andExpect(is2xx());

        // CRUD - U
        Map<String, Object> entity = docs.context("entity1", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity, "machineName1234")).andExpect(is2xx())
                .andDo(print());

        // CRUD - D
        mockMvc.perform(delete(uri)).andExpect(is2xx());
    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {

        List<Machine> result;
        boolean hasResult;

        List<Machine> machineList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            machineList.add(docs.newEntity("machineName" + i, i + "", "원산지" + i));
        }

        machineRepository.saveAll(machineList);

        // 1. 이름으로 Read
        JpaSpecificationBuilder<Machine> query = JpaSpecificationBuilder.of(Machine.class);
        query.where().and().eq("machineName", "machineName11");
        result = machineRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "machineName11".equals(u.getMachineName()));
        assertEquals(true, hasResult);

        // 2. Type으로 Read
        JpaSpecificationBuilder<Machine> query1 = JpaSpecificationBuilder.of(Machine.class);
        query1.where().and().eq("machineType", 33);
        result = machineRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> 33 == u.getMachineType());
        assertEquals(true, hasResult);

        // 3. 원산지로 Read
        JpaSpecificationBuilder<Machine> query2 = JpaSpecificationBuilder.of(Machine.class);
        query2.where().and().eq("machineCountry", "원산지40");
        result = machineRepository.findAll(query2.build());
        hasResult = result.stream().anyMatch(u -> "원산지40".equals(u.getMachineCountry()));
        assertEquals(true, hasResult);

    }

    // Search
    @Test
    public void contextLoads2() throws Exception {

        List<Machine> machineList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            machineList.add(docs.newEntity("machineName" + i, i + "", "원산지" + i));
        }

        machineRepository.saveAll(machineList);

        // 1. 이름으로 Search
        mockMvc.perform(get("/api/machines/search/findByMachineName").param("machineName", "machineName23"))
                .andExpect(is2xx());

        // 2. 타입으로 Search
        mockMvc.perform(get("/api/machines/search/findByMachineType").param("machineType", "10"))
                .andExpect(is2xx());

        // 3. 원산지로 Search
        mockMvc.perform(get("/api/machines/search/findByMachineCountry").param("machineCountry", "원산지33"))
                .andExpect(is2xx());

        // 4. 페이지네이션 - 10개씩 4페이지
        mockMvc.perform(get("/api/machines").param("size", "10"))
                .andExpect(is2xx());

        // 5. 정렬 - name, desc
        mockMvc.perform(get("/api/machines").param("sort", "machineName,desc"))
                .andExpect(is2xx());

    }

}
