package com.ccff.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 图片处理工具类
 */
public class ImageUtil {
    //basePath：为当前项目类路径
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    /**
     * 该方法完成对图片的裁剪、加水印、保存到约定的图片存储路径中的一系列操作
     * @param thumbnailsInputStream：输入流
     * @param fileName：图片文件名
     * @param targetAddr：图片目录的相对路径
     * @return 图片保存后的相对位置
     */
    public static String generateThumbnails(InputStream thumbnailsInputStream, String fileName, String targetAddr){
        //生成真正保存的图片文件的唯一文件名
        String realFileName = getRandomFileName();
        //生成真正保存的图片文件的扩展名
        String extension = getFileExtension(fileName);
        //创建目标路径所涉及到的目录
        makeDirPath(targetAddr);
        //生成真正保存的图片的相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnailsInputStream)
                    .size(200,200)
                    .watermark(Positions.TOP_RIGHT,ImageIO.read(new File(basePath+"/watermark1.jpg")),0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return  fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @return
     */
    public static String getRandomFileName() {
        //获取随机五位数
        int rannum = random.nextInt(89999+10000);
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /*public static void main(String[] args) throws IOException {
        System.out.println(basePath);
        Thumbnails.of(new File(basePath+ "/maidi1.jpg"))
            .size(900,900)
            .watermark(Positions.TOP_RIGHT,ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
            .outputQuality(0.8f)
            .toFile(basePath+"/maidi2.jpg");
    }*/
}
