package org.bistu.web.webstore.service;


import org.bistu.web.webstore.domain.SchoolInfoBo;

import java.util.List;

public interface ISchoolInfoService {

    public List<SchoolInfoBo> getOnes();


    public SchoolInfoBo getOne(Long id);

    public SchoolInfoBo createOne(SchoolInfoBo schoolInfoBo);


    public boolean updateOne(Long id, SchoolInfoBo schoolInfoBo);

    public boolean deleteOne(Long id);

    public boolean deleteOne(String name);
}