package bupt.sse.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.order.dao.OrderDao;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.utils.PageBean;


@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void save(Order order){
		orderDao.save(order);
	}

	public PageBean<Order> findByPageUid(int uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		pageBean.setLimit(5);
		Integer  totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		if(totalCount % 5 == 0){
			pageBean.setTotalPage(totalCount/5);
		}else {
			pageBean.setTotalPage(totalCount/5+1);
		}
		Integer begin = (page-1)*5;
		List<Order> list = orderDao.findByPageUid(uid,begin,5);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	public void update(Order curOrder) {
		orderDao.update(curOrder);
	}
	
}
