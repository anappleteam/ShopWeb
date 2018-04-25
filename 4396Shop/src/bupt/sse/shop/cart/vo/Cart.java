package bupt.sse.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import bupt.sse.shop.product.vo.Product;

//实现序列化接口，避免出现session序列化异常
public class Cart implements Serializable {
	// 购物车属性
	// 购物项集合:Map的key就是商品pid,value:购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>(); 
	//页面中遍历map对象麻烦，只展示map的values即可--->将map的values转成单列的集合
	private double total;
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	// cart对象中有CartItems属性
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// functions:
	// 1.add items to the cart
	public void addCart(CartItem cartItem) {
		// if it exists?
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			// Y-->increase the count, total
			CartItem oldCartItem =map.get(pid);	//the old cartitem
			oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());		
		}else {
			// N-->add the item to the cart, then change the total
			map.put(pid, cartItem);
		}		
		total+=cartItem.getSubtotal();
	}

	// 2.remove
	public void removeCart(Integer pid) {
		// remove the item
		CartItem cartItem = map.remove(pid);
		// change the total
		total -= cartItem.getSubtotal();
	}

	// 3.clear
	public void clearCart() {
		// clear all the items
		map.clear();
		// set total as 0
		total = 0;
	}

}
