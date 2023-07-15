package com.kapumota.microservicios.composite.product.productcompositeservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.kapumota.api.composite.product.ProductAggregate;
import com.kapumota.api.composite.product.ProductCompositeService;
import com.kapumota.api.core.review.Review;
import com.kapumota.api.core.recommendation.Recommendation;
import com.kapumota.api.core.product.Product;
import com.kapumota.api.composite.product.RecommendationSummary;
import com.kapumota.api.composite.product.ReviewSummary;
import com.kapumota.api.composite.product.ServiceAddresses;
//import com.kapumota.api.composite.product.ProductCompositeIntegration;
import com.kapumota.api.exceptions.InvalidInputException;
import com.kapumota.api.exceptions.NotFoundException;
import com.kapumota.util.http.ServiceUtil;

import java.util.List;
import java.util.ArrayList;

@RestController
public class ProductCompositeServiceImpl implements ProductCompositeService {

  private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeServiceImpl.class);

  private final ServiceUtil serviceUtil;

  @Autowired
  public ProductCompositeServiceImpl(ServiceUtil serviceUtil) {
    this.serviceUtil = serviceUtil;
  }

  @Override
  public ProductAggregate getProduct(int productId) {
    LOG.debug("/Resumen de el producto devuelto segun el id de producto={}", productId);

    if (productId < 1) {
      throw new InvalidInputException("Id del producto incorrecto: " + productId);
    }

    if (productId == 13) {
      throw new NotFoundException("No hay producto para el id del producto: " + productId);
    }
    ProductCompositeIntegration integration = new ProductCompositeIntegration(serviceUtil);
    Product product = integration.getProduct(productId);
    List<RecommendationSummary> recommendations = new ArrayList<>();
    recommendations.add(integration.getRecommendations(productId).get(0).exportToRecommendationSummary());
    String rec = integration.getRecommendations(productId).get(0).getServiceAddress();
    
    List<ReviewSummary> reviews = new ArrayList<>();
    reviews.add(integration.getReviews(productId).get(0).exportToReviewSummary());
    String rev = integration.getReviews(productId).get(0).getServiceAddress();
    ServiceAddresses addresses = new ServiceAddresses(serviceUtil.getServiceAddress(),product.getServiceAddress(),rev,rec);
    return new ProductAggregate(product.getProductId(),"producto1", 50, recommendations, reviews, addresses);
  }
}
