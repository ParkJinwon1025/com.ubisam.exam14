package com.ubisam.exam14.api.machines;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam14.domain.Machine;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class MachineDocs extends MockMvcRestDocs {

    public Machine newEntity(String... entity) {

        Machine m = new Machine();
        m.setMachineName(entity.length > 0 ? entity[0] : super.randomText("machineName"));
        m.setMachineType(entity.length > 1 ? Integer.valueOf(entity[1]) : super.randomInt());
        m.setMachineCountry(entity.length > 2 ? entity[2] : super.randomText("machineCountry"));
        return m;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String name) {
        entity.put("machineName", name);
        return entity;
    }

}
