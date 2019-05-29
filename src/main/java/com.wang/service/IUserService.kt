package com.debug.service

import com.wang.entity.UserInfo

interface IUserService {

    fun findUserById(userId: Int): UserInfo

    fun saveUser(user: UserInfo)

    fun updateUser(user: UserInfo)

    fun removeUserById(user: UserInfo)
}