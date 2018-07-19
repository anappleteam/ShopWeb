package bupt.sse.shop.product.service;

import java.util.List;

import javax.management.NotificationListener;
import javax.transaction.Transactional;

import bupt.sse.shop.product.dao.ProductDao;
import bupt.sse.shop.product.vo.Product;
import bupt.sse.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
		
	}
	//最新商品查询
	public List<Product> fingNew() {
		List<Product> nList= productDao.findNew();
		return nList;
	}
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	
	//根据一级分类的cid带有分页的查询上品
	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置每页显示记录
		int limit= 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount =0;
		totalCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage= totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//设置当前页数
		if(page==null||page<1)page=1;
		else if (page>totalPage) {
			page=totalPage;
		}
		pageBean.setPage(page);
		
		//每页显示的数据集合
		//从那开始
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//根据二级分类查询商品信息
	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置每页显示记录
		int limit= 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount =0;
		totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage= totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//设置当前页数
		if(page==null||page<1)page=1;
		else if (page>totalPage) {
			page=totalPage;
		}
		pageBean.setPage(page);
		
		//每页显示的数据集合
		//从那开始
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层查询商品带分页
	public PageBean<Product> findByPage(Integer page){
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if (totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> pList=productDao.findByPage(begin,limit);
		pageBean.setList(pList);
		return pageBean;
		
	}
	public PageBean<Product> findBySid(Integer sid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		
		//设置每页显示记录
		int limit= 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount =0;
		totalCount=productDao.findCountSid(sid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage= totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//设置当前页数
		if(page==null||page<1)page=1;
		else if (page>totalPage) {
			page=totalPage;
		}
		pageBean.setPage(page);
		
		//每页显示的数据集合
		//从那开始
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageSid(sid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public List<Product> findBySid(Integer sid) {
		return productDao.findBySid(sid);
	}
	
	//保存商品
	public void save(Product product) {
		productDao.save(product);
		
	}
	
	//删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}
	
	//修改商品
	public void update(Product product) {
		productDao.update(product);
		
	}
	

}
