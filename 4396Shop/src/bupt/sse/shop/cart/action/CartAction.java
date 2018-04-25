package bupt.sse.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bupt.sse.shop.cart.vo.Cart;
import bupt.sse.shop.cart.vo.CartItem;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;

public class CartAction extends ActionSupport {
	// 接收pid
	private Integer pid;
	// 接收数量count
	private Integer count;
	// 注入商品的Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	// 将购物项添加到购物车:执行的方法
	public String addCart() {
		// 封装一个CartItem对象.
		CartItem cartItem = new CartItem();
		// 设置数量:
		cartItem.setCount(count);
		// 根据pid进行查询商品:
		Product product = productService.findByPid(pid);
		// 设置商品:
		cartItem.setProduct(product);
		// 将购物项添加到购物车.
		// 购物车应该存在session中.
		Cart cart = getCart();
		cart.addCart(cartItem);

		return "addCart";
	}
	
	public String removeCart(){
		//获得购物车对象
		Cart cart =getCart();
		//调用移除方法
		cart.removeCart(pid);
		return "removeCart";
	}
	
	public String clearCart(){
		//获得购物车对象
		Cart cart=getCart();
		//调用购物车清空方法
		cart.clearCart();
		return "clearCart";
	}
	
	public String myCart(){
		return "myCart";
	}

	//获得cart的方法：从session中取出
	public Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
