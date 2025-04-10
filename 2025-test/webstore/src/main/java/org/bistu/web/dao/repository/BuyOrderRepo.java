package org.bistu.web.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bistu.web.dao.entity.BuyOrder;
import org.bistu.web.dao.mapper.BuyOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BuyOrderRepo extends ServiceImpl<BuyOrderMapper, BuyOrder> {
}
