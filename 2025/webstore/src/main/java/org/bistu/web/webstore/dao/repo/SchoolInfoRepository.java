package org.bistu.web.webstore.dao.repo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bistu.web.webstore.dao.entity.SchoolInfo;
import org.bistu.web.webstore.dao.mapper.SchoolInfoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolInfoRepository extends ServiceImpl<SchoolInfoMapper, SchoolInfo> {

}
