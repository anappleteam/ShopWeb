package bupt.sse.shop.product.service;

import java.util.List;

import javax.transaction.Transactional;

import bupt.sse.shop.product.dao.ProductDao;
import bupt.sse.shop.product.vo.Product;

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
}
