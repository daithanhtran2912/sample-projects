<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : hobbysearch.xsl
    Created on : October 10, 2019, 4:25 PM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" />
    <xsl:template match="/">
        <xsl:element name="model">
            <xsl:apply-templates select="document"/>
        </xsl:element>
    </xsl:template>
    <xsl:template match="document">
        <xsl:element name="productName">
            <xsl:value-of select="//td[contains(normalize-space(@class), 'item_name1')]/h2[contains(normalize-space(@class), 'h2_itemDetail')]"/>
        </xsl:element>
        <xsl:param name="tableContent" select="//table[contains(normalize-space(@id), 'tblItemInfo')]/tr[not(td/@colspan)]"/>
        <xsl:element name="details">
            <xsl:for-each select="$tableContent">
                <xsl:element name="detail">
                    <xsl:element name="name">
                        <xsl:value-of select="td[@class='tdItemDetail' and contains(normalize-space(@style), 'width:')]"/>
                    </xsl:element>
                    <xsl:element name="value">
                        <xsl:choose>
                            <xsl:when test="td[@class='tdItemDetail' and not(contains(@style, 'width:'))]/span/span[contains(@style, 'bold')]">
                                <xsl:value-of select="td[@class='tdItemDetail' and not(contains(@style, 'width:'))]/span/span[contains(@style, 'bold')]"/>
                            </xsl:when>
                            <xsl:when test="td[@class='tdItemDetail' and not(contains(@style, 'width:'))]">
                                <xsl:value-of select="td[@class='tdItemDetail' and not(contains(@style, 'width:'))]"/>
                            </xsl:when>
                        </xsl:choose>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
        <xsl:element name="imgLink">
            <xsl:value-of select="string(//div[contains(@class, 'itemlistlink')]/a/@href)"/>
        </xsl:element>
        <xsl:for-each select="//table[contains(@class,'tag01')]/tr">
            <xsl:element name="manufacturer">
                <xsl:value-of select="td//a"/>
            </xsl:element>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
