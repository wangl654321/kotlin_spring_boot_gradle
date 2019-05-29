package com.wang.entity

/***
 *
 *
 * 描    述: yy_pay 甬易对账文件表
 *
 * 创 建 者: @author
 * 创建时间: 2019/05/28 21:49
 * 创建描述:
 *
 * 修 改 者:
 * 修改时间:
 * 修改描述:
 *
 * 审 核 者:
 * 审核时间:
 * 审核描述:
 *
 */
class YyPay {

    /**
     * 主键
     */
    val pid: Int? = null

    /**
     * 商户订单号
     */
    val orderNo: String? = null

    /**
     * 支付流水号
     */
    val flowNo: String? = null

    /**
     * 订单金额
     */
    val orderAmt: Double? = null

    /**
     * 币种
     */
    val curType: String? = null

    /**
     * 支付成功时间
     */
    val tradeTime: String? = null

    /**
     * 订单处理状态(0:未支付,1:已支付；,2:支付失败,31:退款成功（退甬易宝）,37:打款成功(退银行卡))
     */
    val status: String? = null

    /**
     * 订单类型(10:支付,30:退款)
     */
    val type: String? = null

    /**
     * 通道编码
     */
    val bankId: String? = null

    /**
     * 通道名称
     */
    val bankName: String? = null

    /**
     * 交易人姓名
     */
    val customerName: String? = null

    /**
     * 交易人身份证号码
     */
    val customerNo: String? = null

    /**
     * 交易手续费
     */
    val fee: Double? = null

    /**
     * 退款订单号
     */
    val refundNo: String? = null
}