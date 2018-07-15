package bupt.sse.shop.cart.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.cart.service.CartItemService;
import bupt.sse.shop.cart.vo.Cart;
import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.order.vo.OrderItem;
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
	// 接收数量count
	private Integer count;
	// 注入商品的Service
	private ProductService productService;
	// 接收page
	private Integer page;
	// 接收选中的cartitemID
	private String cidsstring;

	public void setcidsstring(String cidsstring) {
		this.cidsstring = cidsstring;
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

	public void setPage(Integer page) {
		this.page = page;
	}

	// 获取当前登录的user
	public User currentUser() {
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
		User user = currentUser();
		// 设置用户
		cartItem.setUser(user);
		// 生成该购物项
		cartItemService.generateCartItem(cartItem);
		Cart cart = cartItemService.getCart();
		//刷新购物车
		cartItemService.findCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "addCart";
	}

	public String removeCart() {
		// 调用移除方法
		cartItemService.removeCartItem(citemid);
		//刷新购物车
		User user = currentUser();
		cartItemService.findCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "removeCart";
	}

	public String clearCart() {
		// 调用购物车清空方法
		cartItemService.clearCart(uid);
		//刷新购物车
		cartItemService.findCartItems(uid);
		User user = currentUser();
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "clearCart";
	}

	public String myCart() {
		// find all cartitems and put them into the SET
		//刷新购物车
		
		User user = currentUser();
		cartItemService.findCartItems(user.getUid());
		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "myCart";
	}
	

}
