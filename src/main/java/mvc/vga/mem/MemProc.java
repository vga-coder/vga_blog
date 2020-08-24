package mvc.vga.mem;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mvc.vga.mem.MemProc")
public class MemProc implements MemProcInter {
	@Autowired
	private MemDAOInter memDAO;

	@Override
	public int checkID(String mem_id) {
	 int cnt = 0;
	 cnt = this.memDAO.checkID(mem_id);
	 return cnt;
	}

	@Override
	public int create(MemVO memVO) {
	 int cnt = 0;
	 cnt = this.memDAO.create(memVO);
	 return cnt;
	}

	@Override
	public List<MemVO> list() {
	 List<MemVO> list = this.memDAO.list();
	 return list;
	}
	
	@Override
	public int delete(int memno) {
	 int cnt = 0;
	 cnt = this.memDAO.delete(memno);
	 return cnt;	
	}
	
	@Override
	public MemVO readById(String mem_id) {
	  MemVO memVO = this.memDAO.readById(mem_id);
	  return memVO;	
	}
	
	@Override
	public MemVO read(int memno) {
	  MemVO memVO = this.memDAO.read(memno);
	  return memVO;	
	}
	  
	@Override
	public int login(HashMap<Object, Object> map) {
	  int cnt = 0;
	  cnt = this.memDAO.login(map);
	  return cnt;
	}

	@Override
	public int passwd_check(HashMap<Object, Object> map) {
	  int cnt = 0;
	  cnt = this.memDAO.passwd_check(map);
	  return cnt;
	}

	@Override
	public int passwd_update(HashMap<Object, Object> map) {
	  int cnt = 0;
	  cnt = this.memDAO.passwd_update(map);
	  return cnt;
	}

	@Override
	public int contact_update(MemVO memVO) {
	  int cnt = 0;
	  cnt = this.memDAO.contact_update(memVO);
	  return cnt;
	}

	@Override
	public int mem_img_update(MemVO memVO) {
	  int cnt = 0;
	  cnt = this.memDAO.mem_img_update(memVO);
	  return cnt;
	}	
	
}
