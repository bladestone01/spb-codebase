package org.bistu.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.dao.entity.SchoolInfo;
import org.bistu.web.dao.repository.SchoolInfoRepository;
import org.bistu.web.domain.SchoolInfoBo;
import org.bistu.web.service.ISchoolInfoService;
import org.bistu.web.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SchoolInfoServiceImpl implements ISchoolInfoService {
    @Autowired
    private SchoolInfoRepository schoolInfoRepo;

    @Override
    public List<SchoolInfoBo> getOnes() {
        List<SchoolInfo> schoolInfos = this.schoolInfoRepo.getBaseMapper().getSchools();

        List<SchoolInfoBo> schoolInfoBos = BeanUtil.copyListProperties(schoolInfos, SchoolInfoBo.class);
        return schoolInfoBos;
    }

    @Override
    public SchoolInfoBo getOne(Long id) {
        SchoolInfo schoolInfo = this.schoolInfoRepo.getBaseMapper().getSchoolById(id);

        SchoolInfoBo schoolInfoBo = BeanUtil.copyProperties(schoolInfo, SchoolInfoBo.class);
        return schoolInfoBo;
    }

    @Override
    public SchoolInfoBo createOne(SchoolInfoBo schoolInfoBo) {
        SchoolInfo schoolInfo = BeanUtil.copyProperties(schoolInfoBo, SchoolInfo.class);
        schoolInfoRepo.getBaseMapper().insertOne(schoolInfo);
        SchoolInfoBo createdSchoolInfoBo = BeanUtil.copyProperties(schoolInfo, SchoolInfoBo.class);

        return createdSchoolInfoBo;
    }

    @Override
    public boolean updateOne(Long id, SchoolInfoBo schoolInfoBo) {
        SchoolInfo shoolInfo = BeanUtil.copyProperties(schoolInfoBo, SchoolInfo.class);

        schoolInfoRepo.getBaseMapper().updateSchool2(id, shoolInfo.getName(), shoolInfo.getStudentNum(), shoolInfo.getValid());

        return true;
    }

    @Override
    public boolean deleteOne(String name) {
        schoolInfoRepo.getBaseMapper().deleteByName(name);
        return true;
    }

    @Override
    public boolean deleteOne(Long id) {
        return schoolInfoRepo.removeById(id);
    }
}
