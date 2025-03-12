package org.bistu.web.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bistu.web.webstore.dao.entity.User;
import org.bistu.web.webstore.dao.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo extends ServiceImpl<UserMapper, User> {
}
