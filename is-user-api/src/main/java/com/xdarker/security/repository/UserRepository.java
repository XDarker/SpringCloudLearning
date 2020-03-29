package com.xdarker.security.repository;

import com.xdarker.security.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 16:05
 */
@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {

    User findByUsername(String username);
}
