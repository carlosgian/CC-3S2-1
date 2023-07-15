package com.kapumota.microservicios.core.recommendation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.kapumota.api.core.recommendation.Recommendation;
import com.kapumota.api.core.recommendation.RecommendationService;
import com.kapumota.api.exceptions.InvalidInputException;
import com.kapumota.api.exceptions.NotFoundException;
import com.kapumota.util.http.ServiceUtil;

import java.util.List;
import java.util.ArrayList;

@RestController
public class RecommendationServiceImpl implements RecommendationService {

  private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);

  private final ServiceUtil serviceUtil;

  @Autowired
  public RecommendationServiceImpl(ServiceUtil serviceUtil) {
    this.serviceUtil = serviceUtil;
  }

  @Override
  public List<Recommendation> getRecommendations(int productId) {
    LOG.debug("/recomendaciones devueltas para el id de producto={}", productId);

    if (productId < 1) {
      throw new InvalidInputException("Id del producto incorrecto: " + productId);
    }

    if (productId == 13) {
      throw new NotFoundException("No hay producto para el id del producto: " + productId);
    }
    
    Recommendation recom1 = new Recommendation(productId, 1, "Gian", 4, "Producto bueno", serviceUtil.getServiceAddress());
    List<Recommendation> recomList = new ArrayList<Recommendation>();
    recomList.add(recom1);
    return recomList;
  }
}
