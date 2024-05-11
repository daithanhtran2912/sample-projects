<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : supperhobby.xsl
    Created on : October 12, 2019, 1:04 PM
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
        <xsl:element name="listLink">
            <xsl:for-each select="//span[contains(@class, 'product-list-item-name')]/a">
                <xsl:element name="link">
                    <xsl:value-of select="@href"/>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
