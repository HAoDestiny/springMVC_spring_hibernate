package com.destiny.work.repository;

import com.destiny.work.model.TbItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Destiny_hao on 2017/9/15.
 */

@Repository
public interface ItemRepository extends JpaRepository<TbItemEntity, Long>{

    ItemRepository findById(Long id);
}
