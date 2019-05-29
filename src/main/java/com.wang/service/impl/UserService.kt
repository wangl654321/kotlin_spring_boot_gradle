package com.wang.service.impl

import com.debug.service.IUserService
import com.wang.dao.UserMapper
import com.wang.entity.UserInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
open class UserService : IUserService {

    @Resource
    lateinit var userMapper: UserMapper

    override fun findUserById(userId: Int): UserInfo {
        return userMapper.findUserById(userId)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun saveUser(user: UserInfo) {
        userMapper.saveUser(user)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateUser(user: UserInfo) {
        userMapper.updateUser(user)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun removeUserById(user: UserInfo) {
        userMapper.removeUserById(user)
    }
}