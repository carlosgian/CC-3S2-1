package com.kapumota.microservicios.core.product.services; 
@RestController
public class ProductServiceImpl implements ProductService { 
 
  private final ServiceUtil serviceUtil;

  @Autowired
  public ProductServiceImpl(ServiceUtil serviceUtil) {
    this.serviceUtil = serviceUtil;
  }
  
  @Override 
  public Product getProduct(int productId) { 
    return new Product(productId, "nombre-" + productId, 123, serviceUtil.getServiceAddress()); 
  } 
} 
