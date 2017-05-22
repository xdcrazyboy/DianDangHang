package com.alisure.tool.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * 用于处理图片
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreImage {

	/**
	 * 缩略图片，设定高度
	 * 
	 * @param beforePath
	 * @param afterPath
	 * @param setHeight
	 */
	public static void thumbnailImageSetHeight(String beforePath,
			String afterPath, int setHeight) throws Exception {

		File fi = new File(beforePath);
		File fo = new File(afterPath);
		AffineTransform transform = new AffineTransform();
		BufferedImage bis = ImageIO.read(fi);
		int width = bis.getWidth();
		int height = bis.getHeight();

		double n = 1;
		if (height > setHeight) {
			n = (double) height / (double) setHeight;
		}

		int setWidth = (int) ((double) width / n);
		double sx = (double) setWidth / width;
		double sy = (double) setHeight / height;
		transform.setToScale(sx, sy);

		BufferedImage bid = new BufferedImage(setWidth, setHeight,
				BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D gd2 = bid.createGraphics();
		gd2.drawImage(bis, transform, null);
		gd2.dispose();

		ImageIO.write(bid, "jpeg", fo);
	}

	/**
	 * 缩略图片，设定宽度
	 * 
	 * @param beforePath
	 * @param afterPath
	 * @param setWidth
	 */
	public static void thumbnailImageSetWidth(String beforePath,
			String afterPath, int setWidth) throws Exception {
		File fi = new File(beforePath);
		File fo = new File(afterPath);
		AffineTransform transform = new AffineTransform();
		BufferedImage bis = ImageIO.read(fi);
		int width = bis.getWidth();
		int height = bis.getHeight();

		double n = 1;
		if (width > setWidth) {
			n = (double) width / (double) setWidth;
		}

		int setHeight = (int) ((double) width / n);
		double sx = (double) setWidth / width;
		double sy = (double) setHeight / height;
		transform.setToScale(sx, sy);

		BufferedImage bid = new BufferedImage(setWidth, setHeight,
				BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D gd2 = bid.createGraphics();
		gd2.drawImage(bis, transform, null);
		gd2.dispose();

		ImageIO.write(bid, "jpeg", fo);
	}

	/**
	 * 按比例缩略图片
	 * 
	 * @param beforePath
	 * @param afterPath
	 * @param n
	 */
	public static void thumbnailImageSetRatio(String beforePath,
			String afterPath, int n) throws Exception {
		File fi = new File(beforePath);
		File fo = new File(afterPath);
		AffineTransform transform = new AffineTransform();
		BufferedImage bis = ImageIO.read(fi);

		int width = bis.getWidth();
		int height = bis.getHeight();
		int setHeight = (int) ((double) height / n);
		int setWidth = (int) ((double) width / n);

		double sx = (double) setWidth / width;
		double sy = (double) setHeight / height;
		transform.setToScale(sx, sy);

		BufferedImage bid = new BufferedImage(setWidth, setHeight,
				BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D gd2 = bid.createGraphics();
		gd2.drawImage(bis, transform, null);
		gd2.dispose();

		ImageIO.write(bid, "jpeg", fo);
	}

    /**
     * 从网络中下载图片
     * @param url
     * @param path
     * @return
     */
	public static boolean downImage(String url, String path){
        try {
            URLConnection connection = new URL(url).openConnection();
            String contentType = connection.getHeaderField("Content-Type");

            /*如果不是图片说明发生错误*/
            if(!contentType.contains("image")){
                System.out.println("Content-Type:"+ contentType);
                return false;
            }

            System.out.println(contentType);
            InputStream inputStream = new DataInputStream(connection.getInputStream());

            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inputStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(path);
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
