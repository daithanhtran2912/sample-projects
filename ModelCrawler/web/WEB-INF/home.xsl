<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : home.xsl
    Created on : October 15, 2019, 9:02 PM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="body">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="productListDto">
        <h2 class="text-lg-left">
            <xsl:value-of select="@category"/>
        </h2>
        <div class="row">
            <xsl:for-each select="product">
                <div class="col-lg-3 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="DetailServlet?param={upcCode}">
                            <img class="card-img-top" src="{productImage}" alt="{productName}"/>
                        </a>
                        <div class="card-body text-lg-left">
                            <span class="card-title">
                                <a href="DetailServlet?param={upcCode}">
                                    <xsl:value-of select="productName"/>
                                </a>
                            </span>
                            <br />
                            <span class="scale-name">Scale : <span class="scale">
                                    <xsl:value-of select="scale"/>
                                </span>
                            </span>
                            <br />
                            <span class="manufacturer-name">Manufacturer : <span class="manufacturer">
                                    <xsl:value-of select="productManufacturer"/>
                                </span>
                            </span>
                            <br />
                            <span class="price-name">Price : <font color='red'>
                                    <xsl:value-of select="price"/> yen</font>
                            </span>
                        </div>
                    </div>
                </div>
            </xsl:for-each>
        </div>
    </xsl:template>

</xsl:stylesheet>
