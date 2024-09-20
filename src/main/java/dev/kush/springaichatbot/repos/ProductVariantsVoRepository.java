package dev.kush.springaichatbot.repos;

import dev.kush.springaichatbot.models.ProductVariantsVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductVariantsVoRepository extends JpaRepository<ProductVariantsVo, Long> {

    @Query(value = "select pv from ProductVariantsVo pv where pv.productVo.name = :productName and pv.isDeleted = :isDeleted")
    List<ProductVariantsVo> findAllByProductIdAndIsDeleted(String productName, int isDeleted);

    @Query(value = "select count(1) from ProductVariantsVo pv where pv.productVo.name = :productName and pv.isDeleted = :isDeleted")
    Long findProductVariantCountByProductIdAndIsDeleted(String productName, int isDeleted);

    @Query(value = "select pv from ProductVariantsVo pv where pv.variantName = :variantName and pv.productVo.name = :productName and pv.isDeleted = :isDeleted")
    ProductVariantsVo findByProductNameAndVariantNameAndIsDeleted(String variantName, String productName, int isDeleted);
}