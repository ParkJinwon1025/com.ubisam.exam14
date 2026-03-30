package com.ubisam.exam14.api.factories;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam14.domain.Factory;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class FactoryHandler {

    @HandleBeforeRead
    public void HandleBeforeRead(Factory f, Specification<Factory> spec) throws Exception {
        // 로그 코드 작성
        // 테스트에서는 System.out.println을 사용하지만 실무에서는 LOG 사용
        System.out.println("[HandleBeforeRead]" + f);
        JpaSpecificationBuilder<Factory> query = JpaSpecificationBuilder.of(Factory.class);
        query.where().and().eq("factoryName", f.getFactoryName())
                .and().eq("factoryDescription", f.getFactoryDescription()).build(spec);
    }

    @HandleAfterRead
    public void afterRead(Factory f, Serializable r) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterRead] " + f);
        System.out.println("[HandleafterRead] " + r);
    }

    @HandleBeforeCreate
    public void beforeCreate(Factory f) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeCreate] " + f);
    }

    @HandleBeforeSave
    public void beforeSave(Factory f) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeSave] " + f);
    }

    @HandleBeforeDelete
    public void beforeDelete(Factory f) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeDelete] " + f);
    }

    @HandleAfterCreate
    public void afterCreate(Factory f) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterCreate] " + f);
    }

    @HandleAfterSave
    public void afterSave(Factory f) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterSave] " + f);
    }

    @HandleAfterDelete
    public void afterDelete(Factory f) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterDelete] " + f);
    }

}
