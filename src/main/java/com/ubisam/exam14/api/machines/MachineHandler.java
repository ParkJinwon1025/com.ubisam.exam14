package com.ubisam.exam14.api.machines;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam14.domain.Machine;

@Component
@RepositoryEventHandler
public class MachineHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(Machine m) throws Exception {
        // 로그 코드 작성
        // 테스트에서는 System.out.println을 쓰지만 실제 현장에서는 LOG 사용
        System.out.println("[HandleBeforeCreate] " + m);

    }

    @HandleAfterCreate
    public void afterCreate(Machine m) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterCreate]" + m);

    }

    @HandleBeforeSave
    public void beforeSave(Machine m) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeSave]" + m);

    }

    @HandleAfterSave
    public void afterSave(Machine m) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + m);

    }

    @HandleBeforeDelete
    public void beforeDelete(Machine m) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeDelete]" + m);

    }

    @HandleAfterDelete
    public void afterDelete(Machine m) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + m);

    }

}
