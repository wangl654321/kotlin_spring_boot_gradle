package com.wang.dao


import com.wang.entity.UserInfo

interface UserMapper {

    //根据id获取用户信息
    fun findUserById(questionId: Int): UserInfo

    //保存用户信息
    fun saveUser(user: UserInfo)

    //修改用户信息
    fun updateUser(user: UserInfo)

    //删除用户信息
    fun removeUserById(user: UserInfo)
}