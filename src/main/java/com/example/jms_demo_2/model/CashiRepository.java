package com.example.jms_demo_2.model;


import com.example.jms_demo_2.model.entity.Cashi;
import com.example.jms_demo_2.model.entity.CashiRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CashiRepository extends JpaRepository<Cashi, CashiRelationPK> {
    @Query(value = "select * from cashi where CASHI_MGNI_ID =?1 and CASHI_ACC_NO =?2 and CASHI_CCY =?3", nativeQuery = true)
    Cashi findTargetCashi(String mgniId, String accNo, String ccy);

    @Query(value = "select * from CASHI where CASHI_MGNI_ID =?1", nativeQuery = true)
    List<Cashi> findCashiListById(String mgniId);

    @Modifying
    @Query(value = "DELETE FROM cashi WHERE CASHI_MGNI_ID=?1", nativeQuery = true)
    void deleteCashiById(String id);
}
