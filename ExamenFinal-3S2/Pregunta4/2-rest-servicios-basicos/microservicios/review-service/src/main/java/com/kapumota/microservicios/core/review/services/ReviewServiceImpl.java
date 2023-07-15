package com.kapumota.microservicios.core.recommendation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.kapumota.api.core.review.Review;
import com.kapumota.api.core.review.ReviewService;
import com.kapumota.api.exceptions.InvalidInputException;
import com.kapumota.api.exceptions.NotFoundException;
import com.kapumota.util.http.ServiceUtil;

import java.util.List;
import java.util.ArrayList;

@RestController
public class ReviewServiceImpl implements ReviewService {

  private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

  private final ServiceUtil serviceUtil;

  @Autowired
  public ReviewServiceImpl(ServiceUtil serviceUtil) {
    this.serviceUtil = serviceUtil;
  }

  @Override
  public List<Review> getReviews(int productId) {
    LOG.debug("/recomendaciones devueltas para el id de producto={}", productId);

    if (productId < 1) {
      throw new InvalidInputException("Id del producto incorrecto: " + productId);
    }

    if (productId == 13) {
      throw new NotFoundException("No hay producto para el id del producto: " + productId);
    }
    
    Review review1 = new Review(productId, 1, "Gian", "Subject", "Producto bueno", serviceUtil.getServiceAddress());
    List<Review> reviewList = new ArrayList<Review>();
    reviewList.add(review1);
    return reviewList;
  }
}
