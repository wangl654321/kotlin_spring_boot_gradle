package com.wang

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/***
 *
 *
 * 描    述：
 *
 * 创 建 者：@author wl
 * 创建时间：2019/5/2710:50
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
@MapperScan("com.wang.dao")
@SpringBootApplication
open class KotlinApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinApplication::class.java, *args)
}
