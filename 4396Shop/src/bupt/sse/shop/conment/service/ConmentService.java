package bupt.sse.shop.conment.service;

import java.util.List;

import bupt.sse.shop.conment.dao.ConmentDao;
import bupt.sse.shop.conment.vo.Conment;
public class ConmentService {
	private ConmentDao conmentDao;
	
	public void setConmentDao(ConmentDao conmentDao) {
		this.conmentDao = conmentDao;
	}

	public List<Conment> findByPid(Integer pid) {
		return conmentDao.findByPid(pid);
	}
	
	public void save(Conment conment){
		conmentDao.save(conment);
	}
}
