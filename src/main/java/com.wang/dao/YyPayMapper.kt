package com.wang.dao

import com.wang.entity.YyPay

interface YyPayMapper {

    /**
     * 根据主键删除
     *
     * @param pid
     * @return int
     */
    fun deleteByPrimaryKey(pid: Int?): Int

    /**
     * 保存不带标签
     *
     * @param record
     * @return int
     */
    fun insert(record: YyPay): Int

    /**
     * 保存带标签
     *
     * @param record
     * @return int
     */
    fun insertSelective(record: YyPay): Int

    /**
     * 根据主键查询
     *
     * @param pid
     * @return YyPay
     */
    fun selectByPrimaryKey(pid: Int?): YyPay

    /**
     * 更新带标签
     *
     * @param record
     * @return int
     */
    fun updateByPrimaryKeySelective(record: YyPay): Int

    /**
     * 更新不带标签
     *
     * @param record
     * @return int
     */
    fun updateByPrimaryKey(record: YyPay): Int


    /**
     * 对账文件解析批量保存
     */
    fun batchInsert(subList: List<Map<String, String>>): Int
}