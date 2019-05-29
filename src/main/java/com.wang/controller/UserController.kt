package com.wang.controller

import com.debug.service.IUserService
import com.wang.entity.UserInfo
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

/***
 *
 *
 * 描    述：用户信息
 *
 * 创 建 者：@author wl
 * 创建时间：2019/5/2713:56
 * 创建描述：
 *
 * 修 改 者：
 * 修改时间：
 * 修改描述：
 *
 * 审 核 者：
 * 审核时间：
 * 审核描述：
 *
 */
@Controller
class UserController {

    var logger = LogManager.getLogger()!!

    @Resource
    lateinit var userService: IUserService

    @RequestMapping("/getUserById")
    fun getUserById(req: HttpServletRequest, model: Model, @RequestParam(value = "id", required = false) id: String): String {
        logger.info("查询用户信息,{}",id)
        val id: Int = Integer.parseInt(id)
        val userInfo = userService.findUserById(id)
        model.addAttribute("userInfo", userInfo)
        return "user"
    }

    @ResponseBody
    @RequestMapping("/saveUser")
    fun saveUser(): String {
        val u = UserInfo(null, "蔡依林", "河南")
        userService.saveUser(u)
        return "保存成功"
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    fun updateUser(): String {
        val u = UserInfo(3, "赵雅芝")
        userService.updateUser(u)
        return "修改成功"
    }

    @ResponseBody
    @RequestMapping("/removeUserById")
    fun removeUserById(): String {
        val u = UserInfo(4)
        userService.removeUserById(u)
        return "删除成功"
    }
}