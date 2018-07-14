package bupt.sse.shop.cart.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import bupt.sse.shop.cart.service.CartItemService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageBean;

//实现序列化接口，避免出现session序列化异常
public class Cart implements Serializable {
	// 购物车属性

//	// 购物项集合:Map的key就是商品pid,value:购物项
//	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
//	// 页面中遍历map对象麻烦，只展示map的values即可--->将map的values转成单列的集合
//	private Map<Integer, CartItem> cartItems=new LinkedHashMap<Integer, CartItem>(); 
	private List<CartItem> cartItems=new ArrayList<CartItem>();
	
//	public Map<Integer, CartItem> getCartItems() {
//		return cartItems;
//	}
//	
//	public void setCartItems(Map<Integer, CartItem> cartItems) {
//		this.cartItems = cartItems;
//	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

}
