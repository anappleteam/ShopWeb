package bupt.sse.shop.cart.action;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.cart.service.CartItemService;
import bupt.sse.shop.cart.vo.Cart;
import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.order.service.OrderService;
import bupt.sse.shop.order.vo.Order;
import bupt.sse.shop.order.vo.OrderItem;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.user.vo.User;

public class CartAction extends ActionSupport {
	private CartItemService cartItemService;
	
	private OrderService orderService;
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
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
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
		return "addCart";
	}
	
	public String updateCart() throws IOException{
		CartItem cartItem=cartItemService.findByCitemid(citemid);
		Integer pid=cartItem.getProduct().getPid();
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");	
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(cartItem.getProduct().getPavailable()>=newCount)
		{
			cartItem.setCount(newCount);
			cartItem.setSubtotal(cartItem.getProduct().getShop_price()*newCount);
			cartItemService.updateCartItem(cartItem);
			cart.getCartItemsMap().put(pid, cartItem);
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);;
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
		return "removeCart";
	}

	public String clearCart() {
		User user = getCurrentUser();
		// 调用购物车清空方法
		cartItemService.clearCart(user.getUid());
		//刷新购物车
		return "clearCart";
	}

	public String myCart() {
		// find all cartitems and put them into the SET
		//刷新购物车
		User user = getCurrentUser();
		//刷新购物车
		cartItemService.loadCartItems(user.getUid());
//		PageBean<CartItem> pageBean = cartItemService.findByUid(user.getUid());
//		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "myCart";
	}
	//从购物车到订单
	private String pidsstring;
	public void setPidsstring(String pidsstring) {
		this.pidsstring = pidsstring;
	}
		
	private Double total=0.0;
	public String save() throws ParseException, IOException{
		// 加前置通知
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if(existUser == null){
			this.addActionError("亲！您还没有登录！请先登录！");
			return "login";
		}
		Order order=new Order();
		order.setUser(existUser);
		order.setName(existUser.getName());
		order.setAddr(existUser.getAddr());
		order.setPhone(existUser.getPhone());
		String[] pids=pidsstring.split(",");
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("亲!您还没有购物!");
			return "msg";
		}
		Map<Integer,CartItem> cartItemMap=cart.getCartItemsMap();
		//List<CartItem> cartItems=cart.getCartItems();
		//Map<Integer,CartItem> cartItemMap=cartItems.stream().collect(Collectors.toMap(CartItem::getCitemid, a -> a,(k1,k2)->k1));
		for (int i = 0; i < pids.length; i++) {
			String string = pids[i];
			Integer pid=Integer.valueOf(string);
			if(cartItemMap.containsKey(pid))
			{
				//购物项中的信息填入订单项
				CartItem cartItem=cartItemMap.get(pid);
				Product product=productService.findByPid(pid);				
				Integer new_pAvailable=product.getPavailable()-cartItem.getCount();
				if (new_pAvailable>=0) {
					product.setPavailable(new_pAvailable);	
					OrderItem orderItem = new OrderItem();
					orderItem.setCount(cartItem.getCount());
					orderItem.setSubtotal(cartItem.getSubtotal());
					orderItem.setProduct(cartItem.getProduct());
					orderItem.setOrder(order);
					orderItem.setStore(cartItem.getProduct().getStore());
					//修改订单项的总金额
					total+=cartItem.getSubtotal();
					order.getOrderItems().add(orderItem);
					//将对应购物项从购物车移除
					cartItemService.removeCartItem(cartItem.getCitemid());
					//更新商品库存
					productService.update(product);
				}else{
					this.addActionMessage(cartItem.getProduct().getPname()+"库存不足！购买失败！");
					//刷新购物车
					cartItemService.loadCartItems(existUser.getUid());
					return "saveFail";
				}
			}	
		}
		order.setTotal(total);
		order.setState(1);
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		order.setOrdertime(date3);
		orderService.save(order);
		return "saveSuccess";
	}
	
}
