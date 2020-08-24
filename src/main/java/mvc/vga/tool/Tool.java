// version 0.7
package mvc.vga.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class Tool {
  /**
   * FileUpload 1.2, 1.3 �ѱ� ��ȯ
   * @param str
   * @return
   */
  public static synchronized String toKor(String str) {
    String corean = null;
    try {
      corean = new String(str.getBytes("ISO-8859-1"), "UTF-8");
      // corean= new String(str.getBytes("ISO-8859-1"), "KSC5601");
      // corean= new String(str.getBytes("ISO-8859-1"), "euc-kr");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return corean;
  }
  
  /**
   * �̹������� �˻�
   * @param file ���ϸ�
   * @return
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
    if (file != null) {
      file = file.toLowerCase();
      if (file.endsWith("jpg") || file.endsWith("jpeg") || file.endsWith(".png") || file.endsWith("gif")) {
        sw = true;
      }
    }
    return sw;
  }
  
  /**
   * byte ���� ���޹޾� �ڷ��� ������ �����մϴ�.
   * @param size
   * @return 1000 �� 1000 Byte
   */
  public static synchronized String unit(long size){
    String str = "";
    
    if (size < 1024){ // 1 KB ����
      str = size + " Byte";
    }else if (size < 1024 * 1024){ // 1 MB ����
      str = (int)(Math.ceil(size/1024.0)) + " KB"; // 1048576 ���� ������ KB
    }else if (size < 1024 * 1024 * 1024){ // 1 GB ����
      str = (int)(Math.ceil(size/1024.0/1024.0)) + " MB";
    }else if (size < 1024L * 1024 * 1024 * 1024){ // 1 TB ����
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0)) + " GB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024){ // 1 PT ����
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0)) + " TB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024){ // 1 EX ����
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0/1024.0)) + " PT";
    }
    
    return str;
  }
  
  /**
   * �̹��� ����� �����Ͽ� ���ο� Preview �̹����� �����մϴ�.
   <pre>
   ��뿹): Tool.preview(folder ��, ���� ���ϸ�, 200, 150)
   </pre>
   * @param upDir ���� �̹��� ����
   * @param _src ���� ���ϸ�
   * @param width ������ �̹��� �ʺ�
   * @param height  ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @return src.jpg ������ �̿��Ͽ� src_t.jpg ������ �����Ͽ� ���ϸ� ����
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
 
    File src = new File(upDir + "/" + _src); // ���� ���� ��ü ����
    String srcname = src.getName(); // ���� ���ϸ� ����
 
    // ���� ���ϸ� ����, mt.jpg -> mt �� ����
    String _dest = srcname.substring(0, srcname.indexOf("."));
 
    // ��� �̹��� ���� /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");
 
    Image srcImg = null;
 
    String name = src.getName().toLowerCase(); // ���ϸ��� �����Ͽ� �ҹ��ڷ� ����
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("jpeg") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����
        int srcWidth = srcImg.getWidth(null); // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1; // ��� �̹��� ũ�� �ʱ�ȭ
 
        if (width == SAME) { // width�� ���� ���
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // ���ο� width�� �Ҵ�
        }
 
        if (height == SAME) { // ���̰� ���� ���
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // ���ο� ���̷� �Ҵ�
        }
 
        // ������ ���� ũ�� ���
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }
 
        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);
 
        pg.grabPixels();
 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
 
        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);
 
        System.out.println(dest.getName() + " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
 
    return dest.getName();
  }
  
  /**
   * ���� ������ �������� �˻�
   * @param file
   * @return true: ���� ����, false: ���� �Ұ���
   */
  public static synchronized boolean isAvailable(String file) {
    boolean sw = false;
 
    if (file != null) { // ���ڿ��� �ִٸ�
      file = file.toLowerCase(); // �ҹ��ڷ� ��ȯ, jsp, java, php, asp, aspx�� ���α׷��� Ȯ���� ����
      if (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png")
          || file.endsWith(".gif") || file.endsWith(".zip") || file.endsWith(".pdf")
          || file.endsWith(".hwp") || file.endsWith(".txt") || file.endsWith(".ppt")
          || file.endsWith(".pptx")
          || file.endsWith(".mp3") || file.endsWith(".mp4")
          ) {
        sw = true;
      } else if (file.trim().length() == 0) { // �۸� ����ϴ� ���
        sw = true;
      }
    }
    return sw;
  }

  /**
   * ���ڿ��� ���̰� length ���� ũ�� "..."�� ǥ���ϴ� �޼ҵ�
   * @param str ���� ���ڿ�
   * @param length ����� ���ڿ� ����
   * @return Ư�� ������ ���ڿ�
   */
  public static synchronized String textLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0,  length) + "..."; // ����: 0 ~ length - 1
      }
    } else {
      str = "";
    }
    
    return str;
  }
 
  /**
   * HTML Ư�� ������ ����
   * https://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references    
   * @param str
   * @return
   */
  public static synchronized String convertChar(String str) {
    str = str.replace("&", "&amp;");
    str = str.replace("<", "&lt;");
    str = str.replace(">", "&gt;");
    str = str.replace("'", "&apos;");
    str = str.replace("\"", "&quot;");
    str = str.replace("\r\n", "<BR>");
    str = str.replace(" ", "&nbsp;");
    return str;
  }
  
  /**
   * ���ڿ��� null�̸� ""���� ����
   * @param str
   * @return
   */
  public static synchronized String checkNull(String str) {
    if (str == null) {
      str = "";
    }
    
    return str;
  } 
  
  /**
   * ���ڿ��� null�̸� ""���� ����
   * @param str
   * @return
   */
  public static synchronized String checkNull(Object str) {
    if (str == null) {
      str = "";
    }
    
    return (String)str;
  }
  
  /**
   * ���� ����
   * @param folder ������
   * @param fileName ���ϸ�
   * @return true: ���� ����, false: ���� ����
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean sw = false;
    try {
      if (folder != null && fileName != null) { // ���� �ִ��� Ȯ��
        File file = new File(folder + "/" + fileName); 
        if (file.exists() && file.isFile()) { // �����ϴ� �������� �˻�
          sw = file.delete();  // ���� ����
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    return sw;
  }

  /**
   * ������ �Է¹޾� ���� ��θ� �����մϴ�. 
   * ��) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir ���� ��θ� ���� ������
   * @return ���� ��� ����
   * @throws IOException
   */
  public static synchronized String getRealPath(HttpServletRequest request, String dir) {
    String path = "";
    
    try{
      path = request.getRealPath(dir) + "/";  
      // System.out.println("Upload path: " + path);
    }catch(Exception e){
      System.out.println(e.toString());
    }
 
    return path;
  }
  
  /**
   * ���� ����
   * @param fname
   * @return
   */
  public static synchronized boolean deleteFile(String fname) {
    File file = new File(fname);
    boolean ret = false;
    
      if (file.exists()){
        ret = file.delete();
      }
    
    return ret;
  }
 
  /**
   * ���� ������ �ٿ�ε��� �浹 ó��
   * 2019-12-06_123020_100
   * @return
   */
  public static String getRandomDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
    String date = sdf.format(new Date());
    date = date + "_" + (int)(Math.random()*1000);
 
    return date;
  }
  
}






