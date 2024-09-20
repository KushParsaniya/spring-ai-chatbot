package dev.kush.springaichatbot.function;

import dev.kush.springaichatbot.models.ProductVariantsVo;
import dev.kush.springaichatbot.models.ProductVo;
import dev.kush.springaichatbot.repos.ProductVariantsVoRepository;
import dev.kush.springaichatbot.repos.ProductVoRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;

@Configuration
@AllArgsConstructor
public class ProductChatClientFunctions {

    private final ProductVoRepository productVoRepository;

    private final ProductVariantsVoRepository productVariantsVoRepository;

    @Bean
    @Description("get the information of products of customer based on company_id and product_Name")
    Function<ProductRequest, ProductVo> getProductsByProductNameAndCompanyIdAndIsDeleted() {
        return productRequest -> productVoRepository.findByCompanyIdAndProductNameAndIsDeleted(
                productRequest.companyId(), productRequest.productName(), productRequest.isDeleted());
    }

    record ProductRequest(String productName, Long companyId, int isDeleted) {
    }

    @Bean
    @Description("get the count of products based on company_id")
    Function<ProductCountRequest, Long> getProductCountFromProductNameAndCompanyIdAndIsDeleted() {
        return productRequest -> productVoRepository.findProductCountByCompanyIdAndIsDeleted(
                productRequest.companyId(), productRequest.isDeleted());
    }

    @Bean
    @Description("get the list of products based on company_id")
    Function<ProductRequest, List<ProductVo>> getAllProductByCompanyIdAndIsDeleted() {
        return productRequest -> productVoRepository.findAllByCompanyIdAndIsDeleted(productRequest.companyId(), productRequest.isDeleted());
    }

    record ProductCountRequest(Long companyId, int isDeleted) {
    }


    @Bean
    @Description("get the list and information of product_variants based on product name")
    Function<ProductVariantRequest, List<ProductVariantsVo>> getAllProductVariantsByProductIdAndIsDeleted() {
        return productVariantRequest -> productVariantsVoRepository.findAllByProductIdAndIsDeleted(productVariantRequest.productName(), productVariantRequest.isDeleted());
    }

    @Bean
    @Description("get the count of product_variants based on product name")
    Function<ProductVariantRequest, Long> getProductVariantCountByProductIdAndIsDeleted() {
        return productVariantRequest -> productVariantsVoRepository.findProductVariantCountByProductIdAndIsDeleted(productVariantRequest.productName(), productVariantRequest.isDeleted());
    }

    record ProductVariantRequest(String productName, int isDeleted) {
    }

    @Bean
    @Description("get the information of product_variants based on product name and variant name")
    Function<ProductVariantRequestByVariantName, ProductVariantsVo> getProductVariantByVariantName() {
        return productVariantRequestByVariantName -> productVariantsVoRepository.findByProductNameAndVariantNameAndIsDeleted(productVariantRequestByVariantName.productName(), productVariantRequestByVariantName.variantName(), productVariantRequestByVariantName.isDeleted());
    }

    record ProductVariantRequestByVariantName(String productName, String variantName, int isDeleted) {
    }


}
