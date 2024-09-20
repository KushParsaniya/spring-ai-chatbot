package dev.kush.springaichatbot.repos;

import dev.kush.springaichatbot.models.ProductVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductVoRepository extends JpaRepository<ProductVo, Long> {

    @Query("select p from ProductVo p where p.companyId=:companyId and p.isDeleted= :isDeleted")
    List<ProductVo> findAllByCompanyIdAndIsDeleted(Long companyId, int isDeleted);

    @Query("select p from ProductVo p where p.companyId=:companyId and p.name = :productName and p.isDeleted= :isDeleted")
    ProductVo findByCompanyIdAndProductNameAndIsDeleted(Long companyId, String productName, int isDeleted);

    @Query("select count(1) from ProductVo p where p.companyId=:companyId and p.isDeleted= :isDeleted")
    Long findProductCountByCompanyIdAndIsDeleted(Long companyId, int isDeleted);
}