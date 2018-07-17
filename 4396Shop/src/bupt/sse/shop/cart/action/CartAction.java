package bupt.sse.shop.cart.action;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;
import java.text.ParseException;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Propagation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.cart.service.CartItemService;
import bupt.sse.shop.cart.vo.Cart;
import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.user.vo.User;
import bupt.sse.shop.utils.PageBean;

public class CartAction extends ActionSupport {
	private CartItemService cartItemService;
	// recieve citemid;
	private Integer citemid;
	// 接收pid
	private Integer pid;
	// recieve uid
	private Integer uid;
	// 接收商品数量count
	private Integer count;
	// 注入商品的Service
	private ProductService productService;
	
	//接收购物项修改后的数量
	private Integer newCount;
	
	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCitemid(Integer citemid) {
		this.citemid = citemid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	// 获取当前登录的user
	public User getCurrentUser() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		return user;
	}

	// 将购物项添加到购物车:执行的方法
	public String addCart() throws ParseException {
		// 封装一个CartItem对象.
		CartItem cartItem = new CartItem();
		// 设置数量:
		cartItem.setCount(count);
		// 根据pid进行查询商品:
		Product product = productService.findByPid(pid);
		// 设置商品:
		cartItem.setProduct(product);
		// 根据uid查询用户
		User user = getCurrentUser();
		// 设置用户
		cartItem.setUser(user);
		// 生成该购物项
		cartItemService.generateCartItem(cartItem);
		//刷新购物车
		cartItemService.loadCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "addCart";
	}
	
	public String updateCart() throws IOException{
		CartItem cartItem=cartItemService.findByCitemid(citemid);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(cartItem.getProduct().getPavailable()>=newCount)
		{
			cartItem.setCount(newCount);
			cartItem.setSubtotal(cartItem.getProduct().getShop_price()*newCount);
			cartItemService.updateCartItem(cartItem);
			response.getWriter().print("true,"+cartItem.getSubtotal());
		}else {
			Integer left=cartItem.getProduct().getPavailable();
			response.getWriter().print("false,"+left);
		}
		return NONE;
	}

	public String removeCart() {
		// 调用移除方法
		cartItemService.removeCartItem(citemid);
		//刷新购物车
		User user = getCurrentUser();
		cartItemService.loadCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "removeCart";
	}

	public String clearCart() {
		User user = getCurrentUser();
		// 调用购物车清空方法
		cartItemService.clearCart(user.getUid());
		//刷新购物车
		cartItemService.loadCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "clearCart";
	}

	public String myCart() {
		// find all cartitems and put them into the SET
		//刷新购物车
		User user = getCurrentUser();
		//刷新购物车
		cartItemService.loadCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "myCart";
	}
}
