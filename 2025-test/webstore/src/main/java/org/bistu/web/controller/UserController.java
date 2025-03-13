package org.bistu.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.domain.ResultInfo;
import org.bistu.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.bistu.web.domain.UserBo;

@RestController
@Slf4j
@RequestMapping("/webstore")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/users")
    public ResultInfo<UserBo> createOne(@RequestBody UserBo userBo)  {
        log.info("create one :{}", userBo);

        UserBo newUserBo = this.userService.createOne(userBo);
        log.info("Created Userbo:{}", newUserBo);

        return ResultInfo.success(newUserBo);
    }
}
