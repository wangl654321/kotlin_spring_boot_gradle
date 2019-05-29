package com.wang.service

/***
 *
 *
 * 描    述：对账文件解析批量保存
 *
 * 创 建 者：@author wl
 * 创建时间：2019/4/169:24
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
interface YyPayService {

    /**
     * 对账文件解析批量保存
     */
    fun batchInsert(subList: List<Map<String, String>>): Int
}
