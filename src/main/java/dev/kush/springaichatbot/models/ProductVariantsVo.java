package dev.kush.springaichatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
public class ProductVariantsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_varient_id", length = 10)
    private long productVariantId;

    @Column(name = "shopify_variant_source_id")
    private Long shopifyVariantSourceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductVo productVo;

    @Column(name = "varient_name", length = 150)
    private String variantName;

    @Column(name = "inventory_policy", length = 150)
    private String inventoryPolicy;

    @Column(name = "sku", length = 150)
    private String sku;

    @Column(name = "weight", length = 20)
    private double weight;

    @Column(name = "position", length = 150)
    private String position;

    @Column(name = "purchase_price", length = 20, columnDefinition = "double precision default 0.0")
    private double purchasePrice;

    @Column(name = "retailer_price", length = 20, columnDefinition = "double precision default 0.0")
    private double retailerPrice;

    @Column(name = "wholesaler_price", length = 20, columnDefinition = "double precision default 0.0")
    private double wholesalerPrice;

    @Column(name = "other_price", length = 20, columnDefinition = "double precision default 0.0")
    private double otherPrice;

    @Column(name = "shopify_price", length = 20, columnDefinition = "double precision default 0.0")
    private double shopifyPrice;

    @Column(name = "compare_at_price", length = 20, columnDefinition = "double precision default 0.0")
    private double compareAtPrice;

    @Column(name = "retailer_margin", length = 20, columnDefinition = "double precision default 0.0")
    private double retailerMargin;

    @Column(name = "new_retailer_margin", length = 20, columnDefinition = "double precision default 0.0")
    private double newRetailerMargin;

    @Column(name = "wholesaler_margin", length = 20, columnDefinition = "double precision default 0.0")
    private double wholesalerMargin;

    @Column(name = "other_margin", length = 20, columnDefinition = "double precision default 0.0")
    private double otherMargin;

    @Column(name = "shopify_margin", length = 20, columnDefinition = "double precision default 0.0")
    private double shopifyMargin;


    @Column(name = "retailer_margin_type", length = 20)
    private String retailerMarginType;


    @Column(name = "new_retailer_margin_type", length = 20)
    private String newRetailerMarginType;


    @Column(name = "wholesaler_margin_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String wholesalerMarginType;


    @Column(name = "other_margin_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String otherMarginType;


    @Column(name = "shopify_margin_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String shopifyMarginType;


    @Column(name = "membership_margin_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String membershipMarginType;

    @Column(name = "membership_margin", length = 20, columnDefinition = "double precision default 0.0")
    private double membershipMargin;

    @Column(name = "membership_price", length = 20, columnDefinition = "double precision default 0.0")
    private double membershipPrice;


    @Column(name = "discount_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String discountType;

    @Column(name = "discount", length = 20, columnDefinition = "double precision default 0.0")
    private double discount;


    @Column(name = "new_retailer_discount_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String newRetailerDiscountType;

    @Column(name = "new_retailer_discount", length = 20, columnDefinition = "double precision default 0.0")
    private double newRetailerDiscount;


    @Column(name = "wholesaler_discount_type", length = 20, columnDefinition = "character varying default 'amount'")
    private String wholesalerDiscountType;

    @Column(name = "wholesaler_discount", length = 20, columnDefinition = "double precision default 0.0")
    private double wholesalerDiscount;

    @Column(name = "landing_cost", length = 20, columnDefinition = "double precision default 0.0")
    private double landingCost;

    @Column(name = "mrp", length = 20, columnDefinition = "double precision default 0.0")
    private double mrp;

    @Column(name = "selling_price", length = 20, columnDefinition = "double precision default 0.0")
    private double sellingPrice;


    @Column(name = "attribute_name_1", length = 150)
    private String attributeName1;


    @Column(name = "attribute_name_2", length = 150)
    private String attributeName2;


    @Column(name = "attribute_name_3", length = 150)
    private String attributeName3;


    @Column(name = "attribute_value_1", length = 150)
    private String attributeValue1;


    @Column(name = "attribute_value_2", length = 150)
    private String attributeValue2;


    @Column(name = "attribute_value_3", length = 150)
    private String attributeValue3;

    @Column(name = "is_deleted", length = 1, columnDefinition = "int default 0")
    private int isDeleted;

    @Column(name = "active_online", length = 1, columnDefinition = "int default 0")
    private int activeOnline;

    @Column(name = "is_btob_show", length = 1, columnDefinition = "int default 0")
    private int isBtoBShow;

    @Column(name = "min_qty", columnDefinition = "double precision default 0.0")
    private double minQty;


    @Column(name = "item_code", length = 80)
    private String itemCode;

    @Column(name = "stock_of_limit")
    private double stockOfLimit = 0;

    @Column(name = "company_id", length = 10)
    private long companyId;

    @Column(name = "image_id", length = 10, updatable = false)
    private long imageId = 0;

    @Column(name = "branch_id", length = 10)
    private long branchId;

    @Column(name = "sequence_no", length = 10)
    private long sequenceNo;

    @Column(name = "temp_qty", columnDefinition = "double precision default 0.0")
    private double tempQty;

    @Column(name = "shopify_negative_selling_status", length = 20)
    private String shopifyNegativeSellingStatus;


    @Column(name = "jpm_product_id")
    public String jpmProductId;

    @Column(name = "jpm_product_tbl_id", columnDefinition = "bigint default 0")
    public long JpmProductTBLId;


    @Column(name = "jio_product_type", length = 1, columnDefinition = "int default 0")
    private int jioProductType;

    @Transient
    private String jpmProductSkuId;


    @Column(name = "ajio_article_jio_code")
    public String ajioArticleJioCode;

    @Column(name = "ajio_product_article_id", columnDefinition = "bigint default 0")
    public long ajioProductArticleId;

    ////////// /// end AJIO fields //////////////////////


    @Column(name = "is_created_in_woo_commerce", columnDefinition = "int default 0")
    private int isCreatedInWooCommerce;


    @Column(name = "woo_commerce_variation_id")
    private String wooCommerceVariationId;

    @Column(name = "opening_qty")
    private String openingQty;


}
