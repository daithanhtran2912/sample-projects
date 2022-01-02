<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : supperhobby.xsl
    Created on : October 12, 2019, 1:32 PM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:element name="product">
            <xsl:element name="productName">
                <xsl:value-of select="//div[contains(@class, 'item-title-bar')]//h1/text()"/>
            </xsl:element>
            <xsl:element name="scale">
                <xsl:value-of select="//span[contains(@class,'item-product-scale')]/b"/>
            </xsl:element>
            <xsl:variable name="doc" select="//div[contains(@class,'item-product-brand')]" />
            <xsl:element name="manufacturer">
                <xsl:value-of select="$doc/div/span/a"/>
            </xsl:element>
            <xsl:element name="itemCode">
                <xsl:value-of select="$doc/div/span/text()"/>
            </xsl:element>
            <xsl:for-each select="//div[contains(@class, 'table-responsive')]//tr">
                <xsl:element name="item">
                    <xsl:for-each select="td">
                        <xsl:choose>
                            <xsl:when test="@class">
                                <xsl:element name="name">
                                    <xsl:value-of select="text()"/>
                                </xsl:element>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:element name="value">
                                    <xsl:value-of select="."/>
                                </xsl:element>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </xsl:element>
            </xsl:for-each>
            <xsl:element name="price">
                <xsl:choose>
                    <xsl:when test="//div[contains(@class,'item-product-main-price')]//span[contains(@class, 'promotion-txt')]">
                        <xsl:value-of select="//div[contains(@class,'item-product-main-price')]/span[contains(@class, 'promotion-txt')]"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="//div[contains(@class,'item-product-main-price')]//span[contains(@class, 'normal-price')]"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:element>
            <xsl:element name="listImage">
                <xsl:variable name="list" select="//li[contains(normalize-space(@class), 'item-gallery')]/ul/li//img"/>
                <xsl:for-each select="$list">
                    <xsl:element name="link">
                        <xsl:value-of select="@src"/>
                    </xsl:element>
                </xsl:for-each>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
