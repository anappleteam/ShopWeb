package bupt.sse.shop.cart.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import bupt.sse.shop.cart.dao.CartItemDao;
import bupt.sse.shop.cart.vo.Cart;
import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.utils.PageBean;

public class CartItemService {
	private CartItemDao cartItemDao;

	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}

	public void saveCartItem(CartItem cartItem) {
		cartItemDao.save(cartItem);
	}
	
	public Cart getCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public List<CartItem> findByUid(Integer uid){
		List<CartItem> list=cartItemDao.findByUid(uid);
		if (list!=null&&list.size()>0) {
			return list;		
		}
		return null;		
	}
	
	public void findCartItems(Integer uid){
		Cart cart=new Cart();
		List<CartItem> list= cartItemDao.findByUid(uid);
		//if list is not null, add cartitem to set
		if (list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				CartItem cartItem=list.get(i);
				cart.getCartItems().add(cartItem);
			}
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
	}

	// functions:
	// 1.add items to the cart
	public void generateCartItem(CartItem cartItem) throws ParseException {
		//get cart
		Cart cart =getCart();
		List<CartItem> list =cart.getCartItems();
		// update加入时的时间
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = temp.format(date);
		Date date3 = temp.parse(date2);
		cartItem.setAddtime(date3);
		// if it exists?
		Integer uid = cartItem.getUser().getUid();
		Integer pid = cartItem.getProduct().getPid();
		// find by uid&pid
		CartItem oldCartItem = cartItemDao.findByUid_Pid(uid, pid);
		if (oldCartItem != null) {
			// Y-->increase the count, total
			oldCartItem.setCount(oldCartItem.getCount() + cartItem.getCount());
			oldCartItem.setAddtime(cartItem.getAddtime());
			cartItemDao.update(oldCartItem);
		} else {
			// N-->add the item to the cart, then change the total
			cartItemDao.save(cartItem);
			cart.getCartItems().add(cartItem);
			// 保存购物项
		}
	}
	//2.remove cartItem
	public void removeCartItem(Integer citemid){
		CartItem cartItem=cartItemDao.findByCitemid(citemid);
		cartItemDao.delete(cartItem);
		Cart cart=getCart();
	}
	//3.clear cart:delete all cartitems
	public void clearCart(Integer uid){
		for(CartItem cartItem:cartItemDao.findByUid(uid)){
			cartItemDao.delete(cartItem);
		}	
		Cart cart=getCart();
	}
	
	public PageBean<CartItem> findByUid(int uid) {
		PageBean<CartItem> pageBean = new PageBean<CartItem>();
		Integer totalCount = null;
		totalCount = cartItemDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		pageBean.setLimit(totalCount);
		List<CartItem> list = cartItemDao.findByUid(uid);
		pageBean.setList(list);
		return pageBean;
	}
}
