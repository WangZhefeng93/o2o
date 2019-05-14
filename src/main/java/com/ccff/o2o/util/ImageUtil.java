package com.ccff.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    public static String generateThumbnails(File thumbnails, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnails);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnails)
                    .size(200,200)
                    .watermark(Positions.TOP_RIGHT,ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
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
     * @param cFile
     * @return
     */
    private static String getFileExtension(File cFile) {
        String orijinalFileName = cFile.getName();
        return  orijinalFileName.substring(orijinalFileName.lastIndexOf("."));
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

    public static void main(String[] args) throws IOException {
        System.out.println(basePath);
        Thumbnails.of(new File(basePath+ "/maidi1.jpg"))
            .size(900,900)
            .watermark(Positions.TOP_RIGHT,ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
            .outputQuality(0.8f)
            .toFile(basePath+"/maidi2.jpg");
    }
}
