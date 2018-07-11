package bupt.sse.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.category.service.CategoryService;
import bupt.sse.shop.category.vo.Category;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageBean;
import javassist.compiler.ast.NewExpr;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//用于接收数据的模型的驱动。
	private Product product = new Product();
	//注入商品的Service
	private ProductService productService;
	//接受分类cid
	private Integer cid;
	//接受二级分类的id
	private Integer csid;
	//商店id
	private Integer sid;
	
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//注入一级分类的Service
	private CategoryService categoryService;
	//接受当前页数
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		return product;
	}
	
	//根据商品的ID查询商品：执行方法；
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据分类id查询商品
	public String findByCid() {
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//根据一级分类查询商品，带分页查询。
		//将pagebean 存入值站中。
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据商品的二级分类id查询商品
	public String findByCsid() {
		//
		PageBean<Product> pageBean=productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCsid";
	}
	
	//跟据商店ID查询商店的所有商品并分页展示
	public String findBySid()
	{
		PageBean<Product> pageBean=productService.findBySid(sid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findBySid";
	}
}
