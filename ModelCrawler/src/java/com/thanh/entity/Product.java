/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.entity;

import com.thanh.jaxbadapter.CategoryAdapter;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author T.Z.B
 */
@Entity
@Table(name = "Product", catalog = "ModelCrawler", schema = "dbo")
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByIdProduct", query = "SELECT p FROM Product p WHERE p.idProduct = :idProduct")
    , @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName")
    , @NamedQuery(name = "Product.findByProductManufacturer", query = "SELECT p FROM Product p WHERE p.productManufacturer = :productManufacturer")
    , @NamedQuery(name = "Product.findByScale", query = "SELECT p FROM Product p WHERE p.scale = :scale")
    , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")
    , @NamedQuery(name = "Product.findByProductCode", query = "SELECT p FROM Product p WHERE p.productCode = :productCode")
    , @NamedQuery(name = "Product.findByProductNameAndProductUrl", query = "SELECT p FROM Product p WHERE lower(p.productName) LIKE lower(:productName) AND p.productUrl = :productUrl")
    , @NamedQuery(name = "Product.findListProductImage", query = "SELECT pi FROM Product p INNER JOIN p.productImageCollection pi")
    , @NamedQuery(name = "Product.findListProductImageByProductId", query = "SELECT pi FROM Product p INNER JOIN p.productImageCollection pi WHERE p.idProduct = :idProduct")
    , @NamedQuery(name = "Product.findTopProductByCategory", query = "SELECT p FROM Product p INNER JOIN p.idCategory c WHERE c.category = :category ORDER BY p.price")
    , @NamedQuery(name = "Product.findByProductUrl", query = "SELECT p FROM Product p WHERE p.productUrl = :productUrl")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    @XmlAttribute(required = true)
    private Integer idProduct;

    @Column(name = "product_name", length = 2147483647)
    @XmlElement(required = true)
    private String productName;

    @Column(name = "product_manufacturer", length = 100)
    @XmlElement(required = true)
    private String productManufacturer;

    @Column(name = "scale", length = 50)
    @XmlElement
    private String scale;

    @Column(name = "price", length = 50)
    @XmlElement
    private String price;

    @Column(name = "product_code", length = 60)
    @XmlElement
    private String productCode;

    @Column(name = "upc_code", length = 100)
    @XmlElement
    private String upcCode;

    @Column(name = "product_url", length = 2147483647)
    @XmlElement(required = true)
    private String productUrl;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "idProduct")
    @XmlTransient
    private Collection<ProductImage> productImageCollection;

    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @ManyToOne
    @XmlJavaTypeAdapter(CategoryAdapter.class)
    private Category idCategory;

    public Product() {
    }

    public Product(Integer idProduct, String productName, String productManufacturer, String scale, String price, String upcCode) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.scale = scale;
        this.price = price;
        this.upcCode = upcCode;
    }

    public Product(Integer idProduct, String productName, String productManufacturer, String scale, String price, String productCode, String upcCode, String productUrl, Collection<ProductImage> productImageCollection, Category idCategory) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.scale = scale;
        this.price = price;
        this.productCode = productCode;
        this.upcCode = upcCode;
        this.productUrl = productUrl;
        this.productImageCollection = productImageCollection;
        this.idCategory = idCategory;
    }
    
    

    public String getUpcCode() {
        return upcCode;
    }

    public void setUpcCode(String upcCode) {
        this.upcCode = upcCode;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @XmlTransient
    public Collection<ProductImage> getProductImageCollection() {
        return productImageCollection;
    }

    public void setProductImageCollection(Collection<ProductImage> productImageCollection) {
        this.productImageCollection = productImageCollection;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thanh.entity.Product[ idProduct=" + idProduct + " ]";
    }
}
