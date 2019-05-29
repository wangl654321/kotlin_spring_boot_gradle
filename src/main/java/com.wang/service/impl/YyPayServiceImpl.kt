package com.wang.service.impl

import com.wang.dao.YyPayMapper
import com.wang.service.YyPayService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

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
@Service
open class YyPayServiceImpl : YyPayService {

    @Resource
    lateinit var yyPayMapper: YyPayMapper

    /**
     * 对账文件解析批量保存
     *
     * @param subList
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    override fun batchInsert(subList: List<Map<String, String>>): Int {
        return yyPayMapper.batchInsert(subList)
    }
}
