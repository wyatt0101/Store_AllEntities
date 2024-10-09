package sg.nus.edu.mystore.interfacemethods;


import sg.nus.edu.mystore.entity.Cart;

import java.util.List;

public interface CartInterface {
    // 向购物车添加商品
    public void addProductToCart(Cart cart);

    // 从购物车中删除商品
    public void removeProductFromCart(Integer user_id, Integer product_id);

    // 清空购物车
    public void removeAllProductsFromCart(Integer user_id);

    // 查看购物车所有商品
    public List<Cart> viewCartList(Integer user_id);

    // 查看购物车单个商品(模糊查询)
    public List<Cart> viewCartListByProductName(Integer user_id, String productName);

    // 更新购物车商品数量
    public void updateQuantity(Cart cart);

    // 计算购物车总价
    public double calculateTotalPrice(Integer user_id);
}
