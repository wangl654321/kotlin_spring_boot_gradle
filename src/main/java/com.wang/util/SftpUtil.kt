package com.wang.util

import com.jcraft.jsch.*
import com.wang.entity.Ftp
import org.apache.logging.log4j.LogManager
import org.springframework.util.StringUtils
import java.io.*
import java.util.*

/***
 *
 *
 * 描    述：类说明 sftp工具类
 *
 * 创 建 者： @author wl
 * 创建时间： 2019/4/2 15:29
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
class SftpUtil {

    private val logger = LogManager.getLogger()!!

    var sftp: ChannelSftp? = null

    var session: Session? = null

    /**
     * 连接sftp服务器
     */
    fun connect(ftp: Ftp): ChannelSftp? {
        try {
            val jsch = JSch()
            if (null != ftp.privateKey) {
                //设置私钥
                jsch.addIdentity(ftp.privateKey)
            }
            session = jsch.getSession(ftp.userName, ftp.ipAddr, ftp.port!!)
            if (null != ftp.pwd) {
                session!!.setPassword(ftp.pwd)
            }
            val config = Properties()
            config["StrictHostKeyChecking"] = "no"
            session!!.setConfig(config)
            session!!.connect()
            val channel = session!!.openChannel("sftp")
            channel.connect()
            sftp = channel as ChannelSftp
        } catch (e: JSchException) {
            logger.error("连接sftp服务器,异常,用户或密码错误,{}", e)
        }
        return sftp
    }

    /**
     * 关闭连接 server
     */
    @Throws(Exception::class)
    fun logout() {
        if (this.sftp != null) {
            if (this.sftp!!.isConnected) {
                this.sftp!!.disconnect()
                this.sftp = null
                logger.info("sftp,关闭连接")
            }
        }

        if (this.session != null) {
            if (this.session!!.isConnected) {
                this.session!!.disconnect()
                this.session = null
                logger.info("session,关闭连接")
            }
        }
    }


    /**
     * 上传文件
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param basePath     服务器的基础路径
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     */
    @Throws(SftpException::class)
    fun upload(sftp: ChannelSftp, basePath: String, directory: String, sftpFileName: String, input: InputStream) {
        try {
            sftp.cd(basePath)
            sftp.cd(directory)
        } catch (e: SftpException) {
            //目录不存在，则创建文件夹
            val dirs = directory.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var tempPath = basePath
            for (dir in dirs) {
                if (null == dir || "" == dir) {
                    continue
                }
                tempPath += "/$dir"
                try {
                    sftp.cd(tempPath)
                } catch (ex: SftpException) {
                    sftp.mkdir(tempPath)
                    sftp.cd(tempPath)
                }

            }
        }

        //上传文件
        sftp.put(input, sftpFileName)
    }


    /**
     * 下载文件。
     *
     * @param directory 下载目录
     * @param fileName  下载的文件
     * @param localPath 存在本地的路径
     */
    @Throws(Exception::class)
    fun download(ftp: Ftp, directory: String?, localPath: String, fileName: String) {

        connect(ftp)
        try {
            if (directory != null && "" != directory) {
                sftp!!.cd(directory)
            }
            if (StringUtils.isEmpty(fileName)) {
                logger.info("sftp,文件批量下载")
                val vector = listFiles(directory)
                val it = vector.iterator()
                while (it.hasNext()) {
                    val entry = it.next() as ChannelSftp.LsEntry
                    val attrs = entry.attrs
                    if (!attrs.isDir) {
                        val name = entry.filename
                        fileOutputStream(localPath, name)
                    }
                }
            } else {
                logger.info("sftp,单个文件下载")
                fileOutputStream(localPath, fileName)
            }
        } catch (e: Exception) {
            throw Exception()
        } finally {
            logout()
        }
    }


    /**
     * 读取下载文件
     *
     * @param ftp       sftp连接信息
     * @param directory 下载目录
     * @param fileName  下载的文件名称
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun read(ftp: Ftp, directory: String?, fileName: String): List<String> {

        //连接sftp
        connect(ftp)
        //保存读取的参数
        val list = ArrayList<String>()
        var inputStream: InputStream? = null
        try {
            if (directory != null && "" != directory) {
                sftp!!.cd(directory)
            }
            inputStream = sftp!!.get(fileName)
        } catch (e: Exception) {
            logger.error("下载的文件不存在")
        }

        try {
            if (null != inputStream) {
                //缓冲读
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                //读取每行
                var line: String? = ""
                while (null != bufferedReader.readLine()) {
                    line = bufferedReader.readLine()
                    if (null != line && "" != line) {
                        val key = line.replace("\\|".toRegex(), ",")
                        list.add(key)
                    }
                }
                //关闭流
                bufferedReader.close()
                inputStream.close()
            }
        } catch (e: Exception) {
            logger.error("读取下载文件异常,", e)
        } finally {
            logout()
        }
        return list
    }


    /**
     * 单个文件下载
     *
     * @param localPath 本地下载位置
     * @param fileName  下载文件名称
     * @throws IOException
     * @throws SftpException
     */
    @Throws(Exception::class)
    private fun fileOutputStream(localPath: String, fileName: String) {

        var output: OutputStream? = null
        try {
            val file = File("$localPath/$fileName")
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()
            output = FileOutputStream(file)
            sftp!!.get(fileName, output)
            output.close()
        } catch (e: Exception) {
            throw Exception("单个文件下载异常")
        } finally {
            if (output != null) {
                try {
                    output.close()
                } catch (e: IOException) {
                    throw Exception("关闭流异常" + e.message)
                }

            }
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    @Throws(SftpException::class, IOException::class)
    fun download(directory: String?, downloadFile: String): ByteArray {

        if (directory != null && "" != directory) {
            sftp!!.cd(directory)
        }
        val `is` = sftp!!.get(downloadFile)
        return ByteArray(`is`.available())
    }


    /**
     * 删除文件
     *
     * @param directory      要删除文件所在目录
     * @param deleteFileName 要删除的文件
     */
    @Throws(SftpException::class)
    fun delete(directory: String, deleteFileName: String) {
        sftp!!.cd(directory)
        sftp!!.rm(deleteFileName)
    }


    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    @Throws(SftpException::class)
    fun listFiles(directory: String?): Vector<*> {
        return sftp!!.ls(directory)
    }
}