package org.bistu.web.webstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.dao.entity.SchoolInfo;
import org.bistu.web.webstore.domain.ResultInfo;
import org.bistu.web.webstore.domain.SchoolInfoBo;
import org.bistu.web.webstore.service.ISchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/webstore/annotation")
public class SchoolInfoController {
    @Autowired
    private ISchoolInfoService schoolInfoService;


    @GetMapping("/schools")
    public ResultInfo<List<SchoolInfoBo>> getOnes() {
        List<SchoolInfoBo> schoolInfoBoList = this.schoolInfoService.getOnes();

        return ResultInfo.success(schoolInfoBoList);
    }

    @GetMapping("/schools/{id}")
    public ResultInfo<SchoolInfoBo> getOne(@PathVariable Long id) {
        log.info("id:{}", id);
        SchoolInfoBo schoolInfoBo = schoolInfoService.getOne(id);
        return ResultInfo.success(schoolInfoBo);
    }

    @PostMapping("/schools")
    public ResultInfo<SchoolInfoBo> addSchool(@RequestBody SchoolInfoBo schoolInfoBo) {
        log.info("schoolInfo:{}", schoolInfoBo);
        SchoolInfoBo newSchoolBo = schoolInfoService.createOne(schoolInfoBo);
        return ResultInfo.success(newSchoolBo);
    }

    @PutMapping("/schools/{id}")
    public ResultInfo<Boolean> updateSchool(@PathVariable Long id, @RequestBody SchoolInfoBo schoolInfoBo) {
        log.info("schoolInfo:{}", schoolInfoBo);
        Boolean resultStatus = schoolInfoService.updateOne(id, schoolInfoBo);
        return ResultInfo.success(resultStatus);
    }

    @DeleteMapping("/schools/{id}")
    public ResultInfo<Boolean> deleteSchool(@PathVariable Long id) {
        log.info("id:{}", id);
        Boolean resultStatus =schoolInfoService.deleteOne(id );

        return ResultInfo.success(resultStatus);
    }
}