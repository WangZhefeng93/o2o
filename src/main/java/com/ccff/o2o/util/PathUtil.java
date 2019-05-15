package com.ccff.o2o.util;

/**
 * 文件路径处理工具类
 */
public class PathUtil {
    //获取对应系统的文件分隔符
    private static String separator = System.getProperty("file.separator");

    /**
     * 获取不同系统中图片存放的根目录
     * @return 返回字符串类型的路径
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/JetBrains/o2oDevImg/";
        }else {
            basePath = "/home/cc_Commander/o2oDevImg/";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }

    /**
     * 完成店铺注册（添加店铺）功能时需要上传店铺缩略图，
     * 该方法实现将上传的图片保存在图片存储的子路径中（每个商铺以商铺ID区分）。
     * basePath+imagePath：代表了该图片存储的绝对路径
     * @param shopId：商铺ID
     * @return 返回存储商铺缩略图的子路径
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",separator);
    }
}
