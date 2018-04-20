package bupt.sse.shop.order.service;

import org.springframework.transaction.annotation.Transactional;

import bupt.sse.shop.order.dao.OrderDao;
import bupt.sse.shop.order.vo.Order;


@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void save(Order order){
		orderDao.save(order);
	}
}
