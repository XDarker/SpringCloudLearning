package com.xdarker.security.repository;

import com.xdarker.security.entity.User;
import com.xdarker.security.log.AuditLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 16:05
 */
@Repository
public interface AuditLogRepository extends JpaSpecificationExecutor<User>, CrudRepository<AuditLog, Long> {

}
