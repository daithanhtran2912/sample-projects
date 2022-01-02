<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : searchFilter.xsl
    Created on : October 16, 2019, 8:08 AM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8"/>
    <xsl:param name="searchValue"/>
    
    <xsl:template match="/" >
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="productListDto">
        <productListDto>
            <xsl:apply-templates/>
        </productListDto>
    </xsl:template>
        
    <xsl:template match="product[contains(translate(productName, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate($searchValue, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) or contains(translate(productManufacturer, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate($searchValue, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]">
        <product idProduct="{@idProduct}">
            <productName>
                <xsl:value-of select="productName"/>
            </productName>
            <productManufacturer>
                <xsl:value-of select="productManufacturer"/>
            </productManufacturer>
            <scale>
                <xsl:value-of select="scale"/>
            </scale>
            <price>
                <xsl:value-of select="price"/>
            </price>
            <upcCode>
                <xsl:value-of select="upcCode"/>
            </upcCode>
            <productImage>
                <xsl:value-of select="productImage"/>
            </productImage>
        </product>
    </xsl:template>
    
    <xsl:template match="product">
    </xsl:template>
    
</xsl:stylesheet>
