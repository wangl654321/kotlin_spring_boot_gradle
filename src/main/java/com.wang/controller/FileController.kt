package com.wang.controller

import com.wang.entity.Ftp
import com.wang.service.YyPayService
import com.wang.service.impl.YyPayServiceImpl
import com.wang.util.SftpUtil
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Controller
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*
import javax.annotation.Resource

/***
 *
 *
 * 描    述：
 *
 * 创 建 者：@author wl
 * 创建时间：2019/5/2821:53
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
class FileController {

    private val logger = LogManager.getLogger()!!

    @Resource
    lateinit var yPayService: YyPayService

    /**
     * 对账文件下载
     *
     * @param ftp
     * @return
     */
    @ResponseBody
    @Throws(Exception::class)
    @RequestMapping("/download")
    fun download(ftp: Ftp): Int {

        ftp.ipAddr = "60.12.221.84"
        ftp.port = 21126
        ftp.path = "/duizhangwenjian"
        ftp.userName = "M100002677"
        ftp.pwd = "H04#247F"
        ftp.fileName = "M100002677_20190515.txt"

        logger.info("对账文件下载===>开始")

        var sftpUtil = SftpUtil()
        //文件对应表字段
        val attribute = arrayOf("merchantId", "orderNo", "flowNo", "orderAmt", "curType", "tradeTime", "status", "type", "bankId", "bankName", "customerName", "customerNo", "fee", "refundNo")
        val list = sftpUtil.read(ftp, ftp.path, ftp.fileName!!)
        val listMap = subString(list, attribute)
        val countNum = 1000
        //计算总共有多少条数据
        val size = list.size
        //判断需要循环多少次
        val count = size / countNum
        var subList: List<Map<String, String>>? = null
        for (i in 0..count) {
            //每次初始化subList
            if (i != count) {
                subList = listMap.subList(i * countNum, (i + 1) * countNum)
            } else {
                subList = listMap.subList(i * countNum, size)
            }
            if (subList.isNotEmpty()) {
                yPayService.batchInsert(subList)
            }
        }
        return size
    }

    /**
     *参数封装
     */
    private fun subString(value: List<String>, list: Array<String>): List<Map<String, String>> {
        val listMap = ArrayList<Map<String, String>>()
        value.forEach {
            val split = it.split(",")
            var map = HashMap<String, String>(16)
            for ((id, key) in split.withIndex()) {
                if (!StringUtils.isEmpty(key)) {
                    map[list[id]] = key
                }
            }
            listMap.add(map)
        }
        return listMap
    }
}