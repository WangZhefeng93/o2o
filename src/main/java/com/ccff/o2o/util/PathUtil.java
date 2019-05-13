package com.ccff.o2o.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator"); //获取对应系统的文件分隔符

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

    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",separator);
    }
}
