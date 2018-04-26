package bupt.sse.shop.product.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.categorysecond.service.CategorySecondService;
import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	private Product product=new Product();

	@Override
	public Product getModel() {
		return product;
	}

	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 分页查询商品方法
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 注入二级分类的service
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public String addPage() {
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		// 通过值栈保存
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPage";
	}

}
