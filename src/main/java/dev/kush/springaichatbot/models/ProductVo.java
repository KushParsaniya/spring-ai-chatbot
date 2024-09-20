
package dev.kush.springaichatbot.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", length = 10)
    private long productId;

    @Column(name = "shopify_source_id")
    private long shopifySourceId;

    @Column(name = "have_variation", length = 1, columnDefinition = "int default 0")
    private int haveVariation;

    @Column(name = "have_design_no", columnDefinition = "int default 0")
    private int haveDesignno;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "active", length = 1, columnDefinition = "int default 0")
    private int active;

    @Column(name = "active_online", length = 1, columnDefinition = "int default 0")
    private int activeOnline;

    @Column(name = "shopify_sync", length = 1, columnDefinition = "int default 0")
    private int shopifySync;


    @Column(name = "company_id", length = 10, updatable = false)
    private long companyId;
    @Column(name = "branch_id", length = 10, updatable = false)
    private long branchId;
    @Column(name = "alterby_id", length = 10)
    private long alterBy;
    @Column(name = "createdby_id", length = 10, updatable = false)
    private long createdBy;
    @Column(name = "is_deleted", length = 1, columnDefinition = "int default 0")
    private int isDeleted;

    @Column(name = "description", length = 300, columnDefinition = "text")
    private String description;

    @Column(name = "short_description", columnDefinition = "text")
    private String shortDescription;


    @Column(name = "display_name", length = 150)
    private String displayName;

    @Column(name = "tag", length = 50)
    private String tag;

    //    @Pattern(regexp = "^[0-9]+$|", message = "only numbers allowed")
    @Column(name = "hsn_code", length = 100)
    private String hsnCode;

    @Column(name = "nutrition1", columnDefinition = "text")
    private String nutrition1;


    @Column(name = "nutrition2", columnDefinition = "text")
    private String nutrition2;


    @Column(name = "nutrition3", columnDefinition = "text")
    private String nutrition3;


    @Column(name = "nutrition4", columnDefinition = "text")
    private String nutrition4;


    @Column(name = "nutrition5", columnDefinition = "text")
    private String nutrition5;

    @Column(name = "product_code", length = 80)
    private String productCode;


    @Column(name = "product_type", length = 80)
    private String productType;

    @Column(name = "tax_included", columnDefinition = "int default 0")
    private int taxIncluded;

    @Column(name = "purchase_tax_included", columnDefinition = "int default 0")
    private int purchaseTaxIncluded;


    @Column(name = "price_wise_tax", length = 80)
    private String priceWiseTax;

    @Column(name = "ingredients", length = 1000)
    private String ingredients;


    //    @Pattern(regexp = "^[0-9]+$|", message = "only numbers allowed")
    @Column(name = "expirationdays", length = 100)
    private String expirationdays;


    @Column(name = "netweight", length = 100)
    private String netweight;


    @Column(name = "cess", length = 1, columnDefinition = "int default 0")
    private int cess;

    @Column(name = "cess_tax", columnDefinition = "double precision default 0")
    private double cesstax;


    @Column(name = "shopify_product_description", columnDefinition = "TEXT")
    private String shopifyProductDescription;

    @Column(name = "stock_limit", columnDefinition = "double precision default 0", length = 20)
    private double stockLimit;

    @Column(name = "po_quantity", columnDefinition = "double precision default 0", length = 20)
    private double poQuantity;


    @Column(name = "type", columnDefinition = "varchar(255) default 'finished'")
    private String type = "finished";

    @Column(name = "is_created_in_woo_commerce", columnDefinition = "int default 0")
    private int isCreatedInWooCommerce;


    @Column(name = "woo_sync_status")
    private String wooSyncStatus;


    @Column(name = "woo_commerce_created_product_id")
    private String wooCommerceCreatedProductId;

    @Column(name = "woo_product_link", updatable = false)
    private String wooProductlink;


    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "mrp", length = 20, columnDefinition = "double precision default 0")
    private double mrp;

    @Column(name = "selling_price", length = 20, columnDefinition = "double precision default 0")
    private double sellingPrice;


    @Column(name = "imagepath", length = 1000, columnDefinition = "varchar(1000) default '/No-image-found.jpg'", updatable = false)
    private String imagepath;


    @Column(name = "purchase_conversation", length = 1, columnDefinition = "int default 0")
    private int purchaseConversation;

    @Column(name = "master_qty", columnDefinition = "double precision default 0")
    private float masterQty;

    @Column(name = "merchant_type_id", columnDefinition = "bigint default 0", updatable = false)
    private long merchantTypeId;


    @Column(name = "cluster_id", updatable = false)
    private String clusterId;

    @Column(name = "jpm_product_id")
    public String jpmProductId;


    @Column(name = "jpm_fcproduct_id")
    public String jpmFCProductId;

    @Column(name = "jpm_product_tbl_id", columnDefinition = "bigint default 0")
    public long JpmProductTBLId;

    @Column(name = "jio_product_type", length = 1, columnDefinition = "int default 0")
    private int jioProductType;

    @Column(name = "is_updatable", length = 1, columnDefinition = "int default 0", updatable = false)
    private int isUpdatable;


    @Column(name = "ajio_style_jio_code")
    public String ajioStyleJioCode;

    @Column(name = "product_key")
    private String productKey;


    //*****EXPIRY MANAGEMENT

    @Column(name = "is_batch_manage", length = 1, columnDefinition = "int default 0", updatable = false)
    private int isBatchManage;
    //if batch manage the enter 1 

    @Column(name = "is_expiry_manage", length = 1, columnDefinition = "int default 0", updatable = false)
    private int isExpiryManage;
    //if expiry_manage the enter 1 

    //*****EXPIRY MANAGEMENT
    @Column(name = "is_expiry_see", length = 1, columnDefinition = "int default 1")
    private int isExpirySee;


    @Column(name = "exp_dat_type")
    private String expDateType;

    @OneToMany(mappedBy = "productVo", cascade = CascadeType.ALL)
    private List<ProductVariantsVo> productVariantsVos;


}