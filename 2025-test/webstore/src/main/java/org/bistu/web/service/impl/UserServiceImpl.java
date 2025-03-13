package org.bistu.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.dao.entity.User;
import org.bistu.web.dao.repository.UserRepo;
import org.bistu.web.domain.UserBo;
import org.bistu.web.global.exception.SystemException;
import org.bistu.web.service.IUserService;
import org.bistu.web.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bladestone
 * @since 2025-03-12
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserBo createOne(UserBo userBo)  {
        User user = BeanUtil.copyProperties(userBo, User.class);

        this.userRepo.save(user);

        UserBo newUserBo = BeanUtil.copyProperties(user, UserBo.class);

        if (true) {
            log.error("throw a new exception");
            throw new SystemException(10000, "transactionl  exception");
        }

        return newUserBo;
    }
}
