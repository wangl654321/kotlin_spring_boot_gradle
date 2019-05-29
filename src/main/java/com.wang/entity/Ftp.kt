package com.wang.entity

/***
 *
 *
 * 描    述：
 *
 * 创 建 者：@author wl
 * 创建时间：2019/3/2822:22
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
class Ftp {

    /**
     * ip地址
     */
    internal var ipAddr: String? = null

    /**
     * 端口号
     */
    internal var port: Int? = null

    /**
     * 用户名
     */
    internal var userName: String? = null

    /**
     * 密码
     */
    internal var pwd: String? = null

    /**
     * 路径
     */
    internal var path: String? = null

    /**
     * 本地下载路径
     */
    internal var localPath: String? = null

    /**
     * 文件名称
     */
    internal var fileName: String? = null

    /**
     * 私钥key
     */
    internal var privateKey: String? = null


}
