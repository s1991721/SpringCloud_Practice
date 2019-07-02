package com.ljf.order.repository;

import com.ljf.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mr.lin on 2019/6/20
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
