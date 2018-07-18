package bupt.sse.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


//实现序列化接口，避免出现session序列化异常
public class Cart implements Serializable {
	// 购物车属性
	//	// 购物项集合:Map的key就是商品pid,value:购物项
	private Map<Integer, CartItem> cartItemsMap=new LinkedHashMap<Integer, CartItem>(); 

	//private List<CartItem> cartItems=new ArrayList<CartItem>();
	
	public Map<Integer, CartItem> getCartItemsMap() {
		return cartItemsMap;
	}
	
	public void setCartItemsMap(Map<Integer, CartItem> cartItemsMap) {
		this.cartItemsMap = cartItemsMap;
	}
	
	// Cart对象中有一个叫cartItems属性.
//	// 页面中遍历map对象麻烦，只展示map的values即可--->将map的values转成单列的集合
	public Collection<CartItem> getCartItems(){
		return cartItemsMap.values();
	}
	
	
//	public List<CartItem> getCartItems() {
//		return cartItems;
//	}
//	
//	public void setCartItems(List<CartItem> cartItems) {
//		this.cartItems = cartItems;
//	}

}
