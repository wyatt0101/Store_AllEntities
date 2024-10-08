package sg.nus.edu.mystore.interfacemethods;


import sg.nus.edu.mystore.entity.Cart;

import java.util.List;

public interface CartInterface {
    // 向购物车添加商品
    public void addProductToCart(Integer user_id, Integer product_id, int quantity);

    // 从购物车中删除商品
    public void removeProductFromCart(Integer user_id, Integer product_id);

    // 从购物车删除所有商品
    public void removeAllProductsFromCart(Integer user_id);

    // 查看购物车
    public List<Cart> viewCartList(Integer user_id);

    // 更新购物车商品数量
    public void updateQuantity(Integer user_id, Integer product_id, int quantity);

    // 清空购物车
    public void emptyCart(Integer user_id);

}
