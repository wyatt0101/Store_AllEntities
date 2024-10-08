package sg.nus.edu.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.edu.mystore.entity.Cart;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {
    // 查询购物车中具体商品
    @Query("SELECT c FROM Cart c WHERE c.user.id =:userId AND c.product.id =:productId")
    Cart findByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    // 查询购物车中所有商品
    @Query("SELECT c FROM Cart c WHERE c.user.id =:userId")
    List<Cart> findByUserId(@Param("userId") Integer userId);

    // 查询购物车中的商品（模糊查询）
    @Query("SELECT c FROM Cart c WHERE c.user.id =:userId AND c.product.name LIKE %:keyword%")
    List<Cart> findByUserIdAndProductName(@Param("userId") Integer userId, @Param("keyword") String keyword);

    // 删除购物车中具体商品
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user.id =:userId AND c.product.id =:productId")
    void deleteByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    // 删除购物车中所有商品
    @Modifying
    @Query("DELETE FROM Cart C WHERE C.user.id =:userId")
    void deletaByUserId(@Param("userId") Integer userId);

    // 计算购物车中商品的总价
    @Query("SELECT SUM(c.product.price * c.quantity) FROM Cart c WHERE c.user.id =:userId")
    Double findTotalPriceByUserId(@Param("userId") Integer userId);
}
