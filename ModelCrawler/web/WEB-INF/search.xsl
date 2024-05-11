<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : search.xsl
    Created on : October 16, 2019, 2:28 AM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <xsl:template match="productListDto">
        <xsl:for-each select="product">
            <div class="row">
                <div class="col-md-5">
                    <a href="DetailServlet?param={upcCode}">
                        <img class="card-img-top" src="{productImage}" alt=""/>
                    </a>
                </div>
                <div class="col-md-7">
                    <h3>
                        <span class="card-title">
                            <a href="DetailServlet?param={upcCode}">
                                <xsl:value-of select="productName"/>
                            </a>
                        </span>
                    </h3>
                    <br />
                    <div class="float-md-left ml-2">
                        <h5>Manufacturer : <span>
                                <xsl:value-of select="productManufacturer"/>
                            </span>
                        </h5>
                        <h5>Scale : <span>
                                <xsl:value-of select="scale"/>
                            </span>
                        </h5>
                        <h5>UPC Code : <span>
                                <xsl:value-of select="upcCode"/>
                            </span>
                        </h5>
                        <h5>Price : <span>
                                <xsl:value-of select="price"/> yen
                            </span>
                        </h5>
                    </div>
                </div>
            </div>
        </xsl:for-each>
        <xsl:if test="not(product)">
            <h3 class="text-center">No result found!!!</h3>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>
