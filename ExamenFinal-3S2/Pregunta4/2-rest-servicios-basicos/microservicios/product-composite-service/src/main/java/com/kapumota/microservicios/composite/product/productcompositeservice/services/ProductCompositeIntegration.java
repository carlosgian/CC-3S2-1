package com.kapumota.microservicios.composite.product.productcompositeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kapumota.api.core.review.ReviewService;
import com.kapumota.api.core.recommendation.RecommendationService;
import com.kapumota.api.core.product.ProductService;
import com.kapumota.api.core.review.Review;
import com.kapumota.api.core.recommendation.Recommendation;
import com.kapumota.api.core.product.Product;
import com.kapumota.util.http.ServiceUtil;

import java.util.List;
import java.util.ArrayList;

@Component
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

  	private final ServiceUtil serviceUtil;

  	@Autowired
  	public ProductCompositeIntegration(ServiceUtil serviceUtil) {
    		this.serviceUtil = serviceUtil;
  	}
	
	@Override
  	public Product getProduct(int productId) {

    	return new Product(productId, "nombre-" + productId, 123, serviceUtil.getServiceAddress());
  	}
  	
  	
  	@Override
  	public List<Recommendation> getRecommendations(int productId) {
    
    	Recommendation recom1 = new Recommendation(productId, 1, "Gian", 4, "Producto bueno", serviceUtil.getServiceAddress());
    	List<Recommendation> recomList = new ArrayList<Recommendation>();
    	recomList.add(recom1);
    	return recomList;
 	 }
  	
	@Override
  	public List<Review> getReviews(int productId) {
    
    		Review review1 = new Review(productId, 1, "Gian", "Subject", "Producto bueno", serviceUtil.getServiceAddress());
    		List<Review> reviewList = new ArrayList<Review>();
    		reviewList.add(review1);
    		return reviewList;
  	}

}
