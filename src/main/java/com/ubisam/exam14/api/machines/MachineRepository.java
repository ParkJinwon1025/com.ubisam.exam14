package com.ubisam.exam14.api.machines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ubisam.exam14.domain.Machine;
import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long>, JpaSpecificationExecutor<Machine> {

    List<Machine> findByMachineName(String machineName);

    List<Machine> findByMachineType(Integer machineType);

    List<Machine> findByMachineCountry(String machineCountry);

}
