package bupt.sse.shop.product.mngaction;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.categorysecond.service.CategorySecondService;
import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageBean;

public class ProductMngAction extends ActionSupport implements ModelDriven<Product>{

	private Product product=new Product();
	private Integer sid;
	
	
	public void setSid(Integer sid) {
		this.sid = sid;
	}

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
	
	//文件上传参数
	private	File upload;
	private String uploadFileName;
	private String uploadContextType;
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}


	// 分页查询商品方法
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跟据商店ID查询商店的所有商品并分页展示
	public String findBySid()
	{
		PageBean<Product> pageBean=productService.findBySid(sid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "list";
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
	
	//保存商品的方法
	public String save() throws IOException {
		
		product.setPdate(new Date(System.currentTimeMillis()));
		
		if(upload!=null){
			//获得绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		
		productService.save(product);
		//页面跳转
		return "saveSuccess";
	}
	
	//移除商品
	public String delete() {
		//先查询再删除
		product=productService.findByPid(product.getPid());
		//删除上传的图片
		String path=product.getImage();
		if(path!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file=new File(realPath);
			file.delete();
		}
		
		//删除数据库表项
		productService.delete(product);
		
		//页面跳转
		return "deleteSuccess";
	}
	
	//编辑商品
	public String edit() {
		//根据商品id查询商品
		product=productService.findByPid(product.getPid());
		//查询所有二级分类的集合
		List<CategorySecond> csList=categorySecondService.findAll();
		//将数据保存到页面
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		//页面跳转
		return "editSuccess";
	}
	
	//修改商品
	public String update() throws IOException {
		
		product.setPdate(new Date(System.currentTimeMillis()));
		
		if(upload!=null){
			//删除源图片
			String pathOld=product.getImage();
			if(pathOld!=null){
				String realPathOld=ServletActionContext.getServletContext().getRealPath("/"+pathOld);
				File fileOld=new File(realPathOld);
				fileOld.delete();
			}
			
			//上传图片
			//获得绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		
		productService.update(product);
		
		//页面跳转
		return "updateSuccess";
	}
	
}
