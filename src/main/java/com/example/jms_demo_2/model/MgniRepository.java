package com.example.jms_demo_2.model;

import com.example.jms_demo_2.model.entity.Mgni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MgniRepository extends JpaRepository<Mgni, String> , JpaSpecificationExecutor<Mgni> {

    @Query(value = "select * from MGNI where MGNI_ID =?1",nativeQuery = true)
    Mgni findMgniById(String id);
}
