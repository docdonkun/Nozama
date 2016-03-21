package nozama.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Adress;
import nozama.model.Article;
import nozama.model.Order;
import nozama.model.Product;
import nozama.model.ProductOrder;
import nozama.model.User;
import nozama.repository.ProductRepository;
import nozama.util.Util;

@Service
public class ProductCartServiceImpl implements ProductCartService {

  @Autowired
  private ProductRepository PR;

  private List<Map<String, Object>> allCart;

  public List<Map<String, Object>> getAllCart(List<Map<String, Object>> allCart) {
    this.allCart = allCart;
    List<Map<String, Object>> allProduct = new ArrayList<Map<String, Object>>();

    List<Integer> allId = new ArrayList<Integer>();
    for (Map<String, Object> productCart : allCart) {
      allId.add((Integer) productCart.get("id"));
    }

    if (allId.size() > 0) {
      List<Article> articles = PR.getArticleById(allId);
      margeAllResultTypeSupport(articles, allProduct);
    }

    return allProduct;
  }

  private void margeAllResultTypeSupport(List<Article> articles,
      List<Map<String, Object>> allProduct) {
    for (Article article : articles) {
      insertInProducts(allProduct, article.getProduct().getType(), article.getProduct(), article);
    }
  }


  private void insertInProducts(List<Map<String, Object>> allProduct, String type, Product product,
      Article article) {
    Map<String, Object> newProduct = new HashMap<String, Object>();
    newProduct.put(type, product.getIdProduct());
    newProduct.put("type", type);
    if (type.equals("album")) {
      newProduct.put("urlType", "/liste-toutes-les-musiques/AllSupport/album/AllYears/ALL");
    } else if (type.equals("single")) {
      newProduct.put("urlType", "/liste-toutes-les-musiques/AllSupport/single/AllYears/ALL");
    } else if (type.equals("film")) {
      newProduct.put("urlType", "/liste-tous-les-films");
    }
    newProduct.put("name", product.getName());
    newProduct.put("description", product.getDescription());
    newProduct.put("urlPicture", product.getUrlPicture());
    newProduct.put("dateReleased", product.getDateReleased());
    newProduct.put("nameTagDateReleased", product.getNameTagDateReleased());

    newProduct.put("price", Float.toString(article.getPrice()));
    newProduct.put("support", article.getNameSupport());
    newProduct.put("id", Integer.toString(article.getIdArticle()));

    for (Map<String, Object> productCart : allCart) {
      if (((Integer) productCart.get("id")).equals(article.getIdArticle())) {
        DecimalFormat df = new DecimalFormat("0.00");
        newProduct.put("numberProduct", (Integer) productCart.get("number"));
        newProduct.put("totalProduct",
            df.format(((Integer) productCart.get("number")) * article.getPrice()));
      }
    }

    allProduct.add(newProduct);
  }


  public float calculTotalProduct(List<Map<String, Object>> list) {
    float priceTotal = 0;
    for (Map<String, Object> product : list) {
      int numberProduct = (Integer) product.get("number");
      Article article = PR.getArticleById((Integer) product.get("id"));
      priceTotal = priceTotal + (numberProduct * article.getPrice());

    }
    return priceTotal;

  }

  public Order insertOrder(Adress adress, Map<String, Object> listTransport, String modePayment,
      User user, float totalPrice, float prixTotalProduct) {
    Order order = new Order();
    order.setAdress(adress);
    order.setCommentDelivery(Util.ConvertStringToNull((String) listTransport.get("commentaire")));
    order.setModeDelivery((String) listTransport.get("id"));
    order.setModePayment(modePayment);
    order.setTotalDelivery((float) listTransport.get("prix"));
    order.setTotalOrder(totalPrice);
    order.setTotalProduct(prixTotalProduct);
    order.setUser(user);
    order.setCreateTime(new Date());
    order.setUpdateTime(new Date());
    PR.inserOrder(order);

    return order;
  }
  

  public void insertProductOrder(List<Map<String, Object>> carts, Order order) {
    List<Integer> allId = new ArrayList<>();
    for (Map<String, Object> cart : carts) {
      allId.add((Integer) cart.get("id"));
    }

    if (allId.size() > 0) {
      List<Article> articles = PR.getArticleById(allId);
      for (Article article : articles) {


        ProductOrder productOrder = new ProductOrder();
        productOrder.setArticle(article);
        productOrder.setOrder(order);
        for (Map<String, Object> cart : carts) {
          if (article.getIdArticle() == (Integer) cart.get("id")) {
            productOrder.setQuantity((Integer) cart.get("number"));
          }
        }
        PR.inserOrderProduct(productOrder);
      }

    }

  }

}
