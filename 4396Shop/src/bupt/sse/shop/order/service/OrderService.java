package bupt.sse.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.order.dao.OrderDao;
import bupt.sse.shop.order.dao.OrderItemDao;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
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
	
	
	
	//后台分页查询订单
	public PageBean<Order> findByPageUid(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置煤业显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置记录数
		int totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		
		return orderDao.findOrderItem(oid);
	}

	public PageBean<OrderItem> findBySidPage(Integer sid, Integer page) {
		PageBean<OrderItem> pageBean=new PageBean<OrderItem>();
		//设置当前页数
		pageBean.setPage(page);
		//设置煤业显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置记录数
		int totalCount=orderDao.findByCountSid(sid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		int begin=(page-1)*limit;
		List<OrderItem> list=orderDao.findByPageSid(sid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
		
}
