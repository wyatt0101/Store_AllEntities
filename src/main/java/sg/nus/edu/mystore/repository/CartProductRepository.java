package sg.nus.edu.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.edu.mystore.entity.Cart;

import java.util.List;


public interface CartProductRepository extends JpaRepository<Cart, Integer> {
    // 查询购物车中具体商品
    @Query("SELECT c FROM Cart c WHERE c.user.id =:userId AND c.product.id =:productId")
    Cart findCartProductByCartIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    // 查询购物车中所有商品
    @Query("SELECT c FROM Cart c WHERE c.user.id =:userId")
    List<Cart> findCartProductByUserId(@Param("userId") Integer userId);

    // 删除购物车中具体商品
    @Query("DELETE FROM Cart c WHERE c.user.id =:userId AND c.product.id =:productId")
    void deleteCartProductByCartIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    // 删除购物车中所有商品
    @Query("DELETE FROM Cart C WHERE C.user.id =:userId")
    void deletaAllCartProductByUserId(@Param("userId") Integer userId);


}
