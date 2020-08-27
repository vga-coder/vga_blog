package mvc.vga.attachfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mvc.vga.contents.ContentsProcInter;
import mvc.vga.tool.Tool;
import mvc.vga.tool.Upload;

@Controller
public class AttachfileCont {
  @Autowired
  @Qualifier("mvc.vga.attachfile.AttachfileProc")
  private AttachfileProcInter attachfileProc;
  
  public AttachfileCont(){
    System.out.println("--> AttachfileCont created.");
  }
  
  // http://localhost:9090/resort/attachfile/create.do
  /**
   * ��� ��
   * @return
   */
  @RequestMapping(value="/attachfile/create.do", method=RequestMethod.GET )
  public ModelAndView create(int contentsno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/attachfile/create"); // webapp/attachfile/create.jsp
    
    return mav;
  }
  
  /**
   * ���� ���
   * @param request
   * @param attachfileVO
   * @param cateno �Խ��� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value = "/attachfile/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request,
                                           AttachfileVO attachfileVO,
                                           int cateno) {
    
    ModelAndView mav = new ModelAndView();

    int contentsno = attachfileVO.getContentsno(); // �θ�� ��ȣ
    String fname = ""; // ���� ���ϸ�
    String fupname = ""; // ���ε�� ���ϸ�
    long fsize = 0;  // ���� ������
    String thumb = ""; // Preview �̹���
    int upload_count = 0; // ����ó���� ���ڵ� ����
    
    String upDir = Tool.getRealPath(request, "/attachfile/storage"); // ���� ���
    // ���� ������ ����� fnamesMF ��ü�� ������.
    List<MultipartFile> fnamesMF = attachfileVO.getFnamesMF();  // ���� ���
    int count = fnamesMF.size(); // ���� ���� ����
    if (count > 0) { // ������ ������ �ִٸ�
      for (MultipartFile multipartFile:fnamesMF) { // ���� ����
        fsize = multipartFile.getSize();  // ���� ũ��
        if (fsize > 0) { // ���� ũ�� üũ
          fname = multipartFile.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
          // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
          fupname = Upload.saveFileSpring(multipartFile, upDir); 
          
          if (Tool.isImage(fname)) { // �̹������� �˻�
            // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
            thumb = Tool.preview(upDir, fupname, 120, 80); 
          }
        }
        AttachfileVO vo = new AttachfileVO();
        vo.setContentsno(contentsno);
        vo.setFname(fname);
        vo.setFupname(fupname);
        vo.setThumb(thumb);
        vo.setFsize(fsize);
       
        upload_count = upload_count + attachfileProc.create(vo); // ���� 1�� ��� ���� dbms ����
      }
    }    
    mav.addObject("upload_count", upload_count);
    mav.addObject("contentsno", attachfileVO.getContentsno());
    
    mav.setViewName("redirect:/attachfile/create_msg.jsp");
    return mav;
  }
  
  /**
   * ���
   * http://localhost:9090/ojt/attachfile/list.do
   * 
   * @return
   */
  @RequestMapping(value = "/attachfile/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<AttachfileVO> list = attachfileProc.list();
    mav.addObject("list", list);

    mav.setViewName("/attachfile/list");

    return mav;
  }
  
  /**
   * ÷�� ���� 1�� ���� ó��
   * 
   * @return
   */
  @RequestMapping(value = "/attachfile/delete.do", 
                             method = RequestMethod.GET)
  public ModelAndView delete_proc(HttpServletRequest request,
                                                int attachfileno) {
    ModelAndView mav = new ModelAndView();

    // ������ ���� ������ �о��.
    AttachfileVO attachfileVO = attachfileProc.read(attachfileno);
    
    String upDir = Tool.getRealPath(request, "/attachfile/storage"); // ���� ���
    Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1���� Thumb ���� ����
    
    // DBMS���� 1���� ���� ����
    attachfileProc.delete(attachfileno);
        
    List<AttachfileVO> list = attachfileProc.list();
    mav.addObject("list", list);
    
    mav.setViewName("redirect:/attachfile/list.do"); 

    return mav;
  }
  
  /**
   * ZIP ���� �� ���� �ٿ�ε�
   * @param request
   * @param contentsno ���� ����� �����ö� ����� �۹�ȣ
   * @return
   */
  @RequestMapping(value = "/attachfile/downzip.do", 
                             method = RequestMethod.GET)
  public ModelAndView downzip(HttpServletRequest request, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    // �۹�ȣ�� �ش��ϴ� ���� ��� ����
    List<AttachfileVO> attachfile_list = attachfileProc.list_by_contentsno(contentsno);
    
    // ���� ����� ���ϸ� ����
    ArrayList<String> files_array = new ArrayList<String>();
    for(AttachfileVO attachfileVO:attachfile_list) {
      files_array.add(attachfileVO.getFupname());
    }
    
    String dir = "/attachfile/storage";
    String upDir = Tool.getRealPath(request, dir); // ���� ���
    
    // ����� ���ϸ�, ���� ������ �ٿ�ε��� �浹 ó��
    String zip = "download_files_" + Tool.getRandomDate() + ".zip"; 
    String zip_filename = upDir + zip;
    
    String[] zip_src = new String[files_array.size()]; // ���� ������ŭ �迭 ����
    
    for (int i=0; i < files_array.size(); i++) {
      zip_src[i] = upDir + "/" + files_array.get(i); // ���� ��� ����      
    }
 
    byte[] buffer = new byte[4096]; // 4 KB
    
    try {
      ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zip_filename));
      
      for(int index=0; index < zip_src.length; index++) {
        FileInputStream in = new FileInputStream(zip_src[index]);
        
        Path path = Paths.get(zip_src[index]);
        String zip_src_file = path.getFileName().toString();
        // System.out.println("zip_src_file: " + zip_src_file);
        
        ZipEntry zipEntry = new ZipEntry(zip_src_file);
        zipOutputStream.putNextEntry(zipEntry);
        
        int length = 0;
        // 4 KB�� �о buffer �迭�� ������ ���� ����Ʈ���� length�� ����
        while((length = in.read(buffer)) > 0) {
          zipOutputStream.write(buffer, 0, length); // ����� ����, ���뿡���� ���� ��ġ, ����� ����
          
        }
        zipOutputStream.closeEntry();
        in.close();
      }
      zipOutputStream.close();
      
      File file = new File(zip_filename);
      
      if (file.exists() && file.length() > 0) {
        System.out.println(zip_filename + " ���� �Ϸ�");
      }
      
//      if (file.delete() == true) {
//        System.out.println(zip_filename + " ������ �����߽��ϴ�.");
//      }
 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
 
    // download ���� ����
    mav.setViewName("redirect:/download2?dir=" + dir + "&filename=" + zip + "&downname=" + zip);    
    
    return mav;
  }
  
  
}



