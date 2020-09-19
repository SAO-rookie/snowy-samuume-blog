package com.snowy_samuume.tool;


import com.snowy_samuume.entity.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @auther snowy
 * @date 2020/7/11 - 22:29
 */
public class FastDFSUtils {
    /*
    *  根据配置文件初始化连接
    * */
    static {
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
    /*
    *   获取TrackerServer并且获取Storage里的信息
    * */
    private static TrackerServer getTrackerClient() throws IOException {
        //创建TrackerClient客户端对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient对象获取TrackerServer信息
        return trackerClient.getConnection();
    }

    /*
    *   获取StorageClient对象
    * */
    private static StorageClient getStorageClient() throws IOException {
        return new StorageClient(getTrackerClient(),null);
    }

    /**
     * 文件上传
    * @Author: snowy
    * @Date: 2020/7/11
    * @Param:
    * @return:
    */
    public static String[] fileUpload(FastDFSFile dfsUntil){
        //获取文件作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] =new NameValuePair(dfsUntil.getAuthor());

        String[] uploadResults = null;
        try {
            uploadResults =  getStorageClient().upload_file(dfsUntil.getContent(),dfsUntil.getExt(),meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResults;
    }

    /***
     * 获取文件信息
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        try {
            FileInfo file_info = getStorageClient().get_file_info(groupName, remoteFileName);
            return file_info;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }


    /***
     * 文件下载
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     * @return
     */
    public static InputStream downFile(String groupName,String remoteFileName){
        try {
            byte[] bytes = getStorageClient().download_file(groupName, remoteFileName);
            return new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 文件删除实现
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */

    public static boolean deleteFile(String groupName,String remoteFileName){
        try {
            int i = getStorageClient().delete_file(groupName, remoteFileName);
            return i>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return false;
    }

    /***
     * 获取组信息
     * @param groupName :组名
     */
    public static StorageServer getStorages(String groupName){

        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            return   trackerClient.getStoreStorage(trackerServer,groupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 文件上传
    * */
    public static  String uploadTool(MultipartFile file){
        FastDFSFile fastDFSFile = null;
        try {
            fastDFSFile = new FastDFSFile(
                    file.getOriginalFilename(),
                    file.getBytes(),
                    StringUtils.getFilenameExtension(file.getOriginalFilename())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] paths = fileUpload(fastDFSFile);
        String path  ="http://182.92.208.221:8080/"+paths[0]+"/"+paths[1];
        return path;
    }



}

