package bupt.sse.shop.product.mngaction;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bupt.sse.shop.categorysecond.service.CategorySecondService;
import bupt.sse.shop.categorysecond.vo.CategorySecond;
import bupt.sse.shop.product.service.ProductService;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.store.vo.Store;
import bupt.sse.shop.utils.PageBean;

public class ProductMngAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	private Integer sid;
	private Integer csid;

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Override
	public Product getModel() {
		//product.setCategorySecond(new CategorySecond());
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
	public Integer getPage() {
		return page;
	}

	// 文件上传参数
	private File upload;
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
		if(page==null)page=1;
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	private String findPageBySid(Integer sid) {
		if(sid==null)return "error";
		if(page==null)page=1;
		PageBean<Product> pageBean = productService.findBySid(sid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCurStore";
	}
	// 跟据商店ID查询商店的所有商品并分页展示
	public String findByCurStore() {
		Store curStore = (Store) ServletActionContext.getRequest().getSession().getAttribute("managedStore");
		if (curStore != null) {
			if(page==null)page=1;
			PageBean<Product> pageBean = productService.findBySid(curStore.getSid(), page);
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
			return "findByCurStore";
		}
		return "error";
	}

	// 注入二级分类的service
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public String add() {
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		// 通过值栈保存
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPage";
	}
	
	//添加和修改商品信息的后端检查
	private boolean infoCheck(boolean checkimgnull){
		if(product.getPname()==null||product.getPname().equals("")){
			return false;
		}
		if(product.getMarket_price()==null||product.getMarket_price().doubleValue()<0.0){
			return false;
		}
		if(product.getShop_price()==null||product.getShop_price().doubleValue()<0.0){
			return false;
		}
		if(product.getPdesc()==null||product.getPdesc().equals("")){
			return false;
		}
		if(product.getPavailable()==null||product.getPavailable().intValue()<0){
			return false;
		}
		String imgExe="jpeg,jpg,png,bmp,gif";
		if( upload==null?checkimgnull:imgExe.indexOf(FilenameUtils.getExtension(uploadFileName))==-1){
			return false;
		}
		return true;
	}

	// 保存商品的方法
	public String save() throws IOException {
		Store store = (Store) ServletActionContext.getRequest().getSession().getAttribute("managedStore");
		
		
		if (store != null) {
			
			if(!infoCheck(true)){
				
				this.addActionError("illegal product info");
				
				return findPageBySid(store.getSid());
			}
			
			product.setPdate(new Date(System.currentTimeMillis()));
			CategorySecond thisSecond=new CategorySecond();
			thisSecond.setCsid(csid);
			product.setCategorySecond(thisSecond);
			
			product.setStore(store);
			productService.save(product);
			if (upload != null) {
				uploadFileName="product_img_"+product.getPid()+"."+FilenameUtils.getExtension(uploadFileName);
				// 获得绝对路径
				String realPath = ServletActionContext.getServletContext().getRealPath("/products");
				// 创建文件
				File diskFile = new File(realPath + "//" + uploadFileName);
				// 文件上传
				FileUtils.copyFile(upload, diskFile);
				product.setImage("products/" + uploadFileName);
				productService.update(product);
			}
			// 页面跳转
			page=new Integer(Integer.MAX_VALUE);
			return findPageBySid(store.getSid());
		}
		return "productMngError";
	}

	// 移除商品
	public String delete() {
		Store managedStore = (Store) ServletActionContext.getRequest().getSession().getAttribute("managedStore");
		List<Product> products = productService.findBySid(managedStore.getSid());
		for (Product p : products) {
			if (p.getPid().intValue() == product.getPid().intValue()) {
				// 删除上传的图片
				String path = product.getImage();
				if (path != null) {
					String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
					File file = new File(realPath);
					file.delete();
				}

				// 删除数据库表项
				productService.delete(product);

				// 页面跳转
				return findPageBySid(managedStore.getSid());
			}
		}
		return findPageBySid(managedStore.getSid());
	}

	// 编辑商品
	public String edit() {

		// 先查询受管店铺是否含有此商品
		Store managedStore = (Store) ServletActionContext.getRequest().getSession().getAttribute("managedStore");
		if (managedStore == null)
			return "productMngError";
	
		List<Product> products = productService.findBySid(managedStore.getSid());
		for (Product p : products) {
			if (p.getPid().intValue() == product.getPid().intValue()) {
				product = p;

				ServletActionContext.getRequest().getSession().setAttribute("managedProduct", product);
				// 查询所有二级分类的集合
				List<CategorySecond> csList = categorySecondService.findAll();
				// 将数据保存到页面
				ActionContext.getContext().getValueStack().set("csList", csList);

				// 页面跳转
				return "editPage";
			}
		}
		return "productMngError";
	}

	// 修改商品
	public String update() throws IOException {
		Store managedStore=(Store)ServletActionContext.getRequest().getSession().getAttribute("managedStore");
		Product managedProduct = (Product) ServletActionContext.getRequest().getSession().getAttribute("managedProduct");
		if (managedProduct.getPid().intValue() != product.getPid().intValue()||managedStore==null)
			return "productMngError";
		
		if(!infoCheck(false)){
			this.addActionError("illegal product info");
			
			/*HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().flush();
			response.getWriter().write("<script>history.go(-1)</script>");*/
			return findPageBySid(managedStore.getSid());
		}
		
		product.setPdate(new Date(System.currentTimeMillis()));
		product.setStore(managedProduct.getStore());

		CategorySecond thisSecond=new CategorySecond();
		thisSecond.setCsid(csid);
		product.setCategorySecond(thisSecond);
		
		if (upload != null) {
			// 删除源图片
			String pathOld = product.getImage();
			if (pathOld != null) {
				String realPathOld = ServletActionContext.getServletContext().getRealPath("/" + pathOld);
				File fileOld = new File(realPathOld);
				fileOld.delete();
			}

			// 上传图片
			// 获得绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			// 文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}

		productService.update(product);

		// 页面跳转
		return findPageBySid(managedStore.getSid());
	}

}
