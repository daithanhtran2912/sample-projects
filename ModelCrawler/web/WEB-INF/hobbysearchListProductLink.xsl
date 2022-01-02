<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : hobbysearchListProductLink.xsl
    Created on : October 11, 2019, 4:09 PM
    Author     : T.Z.B
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    
    <xsl:template match="/">
        <xsl:element name="listLink">
            <xsl:for-each select="//div[contains(normalize-space(@class),'ListItemName')]/a">
                <xsl:element name="link">
                    <xsl:value-of select="@href"/>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
