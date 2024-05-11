<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : detail.xsl
    Created on : October 16, 2019, 12:20 AM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="body">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="listProduct">
        <h1 class="my-4">
            <xsl:value-of select="productName"/>
        </h1>

        <div class="row">

            <div class="col-md-6">
                <a href="{productImage}"  target="_blank">
                    <img class="img-fluid" src="{productImage}" alt="{productName}"/>
                </a>
            </div>

            <div class="col-md-6 mt-4">
                <h3 class="my-3">Product Information</h3>
                <div class="mt-5">
                    <h5>Manufacturer : <span>
                            <xsl:value-of select="productManufacturer"/>
                        </span>
                    </h5>
                    <h5>Scale : <span>
                            <xsl:value-of select="scale"/>
                        </span>
                    </h5>
                    <h5>Category : <span>
                            <xsl:value-of select="category"/>
                        </span>
                    </h5>
                    <h5>UPC Code : <span>
                            <xsl:value-of select="upcCode"/>
                        </span>
                    </h5>
                </div>
            </div>
        </div>
        
        <xsl:if test="listProductDetail">
            <h3 class="my-4">Vendor</h3>
            <xsl:for-each select="listProductDetail">
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 class="card-title">
                            <a href="{productUrl}">
                                <xsl:value-of select="productName"/>
                            </a>
                        </h3>
                        <h6>About: <font color="red">
                                <xsl:value-of select="price"/>
                                yen</font>
                        </h6>
                        <h6>Product Code: <xsl:value-of select="productCode"/></h6>
                    </div>
                </div>
            </xsl:for-each>
        </xsl:if>
        
        <h3 class="my-4">Product Images</h3>
        <div class="row text-center text-lg-left">
            <xsl:for-each select="listImage">
                <div class="col-lg-3 col-md-4 col-6">
                    <a class="d-block mb-4 h-100" href="{imageUrl}" target="_blank">
                        <img class="img-fluid img-thumbnail" src="{imageUrl}"/>
                    </a>
                </div>
            </xsl:for-each>
        </div>
    </xsl:template>
</xsl:stylesheet>
