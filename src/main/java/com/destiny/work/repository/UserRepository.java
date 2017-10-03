package com.destiny.work.repository;

import com.destiny.work.model.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by Destiny_hao on 2017/9/14.
 */

@Repository
public interface UserRepository extends JpaRepository<TbUserEntity, Long> {

    @Modifying
    @Transactional
    @Query("update TbUserEntity u set u.password=:password, u.updated=:timestamp where u.userName=:userName")
    public void modifyPass(@Param("password") String pwd,
                           @Param("timestamp") Timestamp timestamp,
                           @Param("userName") String userName);

    TbUserEntity findByUserName(String userName);
}
