package org.bistu.web.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bistu.web.dao.entity.SchoolInfo;
import org.bistu.web.dao.mapper.SchoolInfoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolInfoRepository extends ServiceImpl<SchoolInfoMapper, SchoolInfo> {
}
