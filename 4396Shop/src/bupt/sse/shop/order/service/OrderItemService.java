package bupt.sse.shop.order.service;

import bupt.sse.shop.order.dao.OrderItemDao;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;

public class OrderItemService {

	private OrderItemDao orderItemDao;
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	public OrderItem findByTid(Integer itemid) {
		return orderItemDao.findByTid(itemid);
	}

	public void update(OrderItem curItem) {
		orderItemDao.update(curItem);
	}
}
