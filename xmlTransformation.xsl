<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="xml" version="1.0" indent="yes"/>

    <xsl:template match="/">

        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master  master-name="PageMaster" 
                                        page-height="29.7cm" 
                                        page-width="21.0cm" 
                                        margin-top="1cm"  
                                        margin-bottom="1cm"
                                        margin-left="2cm" 
                                        margin-right="2cm">
                    <fo:region-body region-name="body" margin-top="3cm" margin-bottom="2cm" />
                    <fo:region-before region-name="header" extent="3cm" />
                    <fo:region-after region-name="footer" extent="2cm" />
                    <fo:region-start region-name="left"  extent="2cm" />
                    <fo:region-end region-name="right"  extent="2cm" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="PageMaster">        
                <fo:static-content flow-name="header">
                    <fo:block   text-align="center"   line-height="2cm"
                                font-size="26pt" font-weight="bold"
                                background-color="#dddddd">
                        HOME APPLIANCE STORE
                    </fo:block>
                </fo:static-content>        
                <fo:static-content flow-name="footer" line-height="20pt" >
                    <fo:block   text-align="right"
                                border-top-style="solid"
                                border-width="1px"  >
                        <fo:block font-size="8pt">@HomeApplianceStore</fo:block>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-before-float-separator">
                    <fo:block background-color="red">Separation area of header and body</fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-footnote-separator">
                    <fo:block background-color="blue">Separation area of body and footnote</fo:block>
                </fo:static-content>
                <fo:flow flow-name="body">
                    <fo:block font-weight="bolder" font-size="18pt" text-align="center" space-after="1cm">Facture / Bon de Livraison</fo:block>
                    <!-- Adress Area -->
                    <fo:block space-after="1cm"> 
                        <fo:table border="none">
                            <fo:table-body>
                                <fo:table-row >
                                    <fo:table-cell >
                                    	<fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell >
                                    	<fo:block></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row >
                                    <fo:table-cell >
                                    	<fo:block font-weight="bold" font-size="10pt">Adresse de facturation/livraison</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell >
                                    	<fo:block></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row >
                                    <fo:table-cell>
                                    	<fo:block font-size="8pt" font-weight="bold"> <xsl:value-of select="invoice/customer/name"/> </fo:block>
                                    </fo:table-cell>                                
                                    <fo:table-cell text-align="right" >
                                    	<fo:block font-size="10pt" font-weight="bolder">HOME APPLIANCE STORE</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row >
                                    <fo:table-cell>
                                    	<fo:block font-size="8pt" > <xsl:value-of select="invoice/customer/address"/> </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" >
                                    	<fo:block font-size="8pt" >51 Av. Jean Medecin</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row >
                                    <fo:table-cell>
                                    	<fo:block font-size="8pt" > <xsl:value-of select="invoice/customer/zip"/> </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                    	<fo:block font-size="8pt" >06000 NICE</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <!-- References Area -->
                    <fo:block space-after="1cm" >
                        <fo:block font-size="8pt" >Reference Client : <xsl:value-of select="invoice/customer/@customerId"/> </fo:block>
                        <fo:block font-size="8pt" >Reference Facture : <xsl:value-of select="invoice/@invoiceNumber"/> </fo:block>
                        <fo:block font-size="8pt" >Date de Facturation : <xsl:value-of select="invoice/issueDate"/> </fo:block>
                        <fo:block font-size="8pt" >Mode de Livraison : <xsl:value-of select="invoice/deliveryMode/mode"/> </fo:block>
                    </fo:block>            
                    <!-- Items Area -->
                    <fo:block space-after="1cm">
                        <fo:table border="none">                        
                            <fo:table-column column-number="1" column-width="3cm" ></fo:table-column>
                            <fo:table-column column-number="2" column-width="3cm" ></fo:table-column>
                            <fo:table-column column-number="3"  ></fo:table-column>
                            <fo:table-column column-number="4" column-width="2cm" ></fo:table-column>
                            <fo:table-column column-number="5" column-width="2cm" ></fo:table-column>
                            <fo:table-column column-number="6" column-width="2cm" ></fo:table-column>
                            <fo:table-header >
                                <fo:table-row >
                                    <fo:table-cell text-align="left">
                                    	<fo:block border-bottom="1px solid" font-size="8pt" font-weight="bolder">Reference de l'article</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left">
                                    	<fo:block border-bottom="1px solid" font-size="8pt" font-weight="bolder">Type du Produit</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left">
                                    	<fo:block border-bottom="1px solid" font-size="8pt" font-weight="bolder">Description du Produit</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                    	<fo:block border-bottom="1px solid" font-size="8pt" font-weight="bolder">Prix Unitaire</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                    	<fo:block border-bottom="1px solid" font-size="8pt" font-weight="bolder">Quantite</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                    	<fo:block border-bottom="1px solid" font-size="8pt" font-weight="bolder">Prix</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>
                            <fo:table-footer>
                                <fo:table-row >
                                    <fo:table-cell number-columns-spanned="6">
                                    	<fo:block text-align="right" border-top="1px solid" font-size="9pt">
                                    		Livraison: <xsl:value-of select="invoice/deliveryMode/price"/>
                                    	</fo:block>
                                    </fo:table-cell>
                                </fo:table-row> 
                                <fo:table-row >
                                    <fo:table-cell number-columns-spanned="6">
                                    	<fo:block text-align="right" font-size="9pt" font-weight="bold">
                                    		Montant TTC (EUR): <xsl:value-of select="invoice/price"/>
                                    	</fo:block>
                                    </fo:table-cell>
                                </fo:table-row> 
                            </fo:table-footer>
                            <fo:table-body >
                                <xsl:for-each select="invoice/items/item">              
                                    <fo:table-row >
                                        <fo:table-cell>
                                        	<fo:block text-align="left"      line-height="18pt" font-size="8pt"> <xsl:value-of select="product/productRef" /> </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                        	<fo:block text-align="left"      line-height="18pt" font-size="8pt"> <xsl:value-of select="product/productType" /> </fo:block></fo:table-cell>
                                        <fo:table-cell>
                                        	<fo:block text-align="left"      line-height="18pt" font-size="8pt"> <xsl:value-of select="product/description" /> </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                        	<fo:block text-align="center"    line-height="18pt" font-size="8pt"> <xsl:value-of select="product/price" /> </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                        	<fo:block text-align="center"    line-height="18pt" font-size="8pt"> <xsl:value-of select="quantity" /> </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                        	<fo:block text-align="right"      line-height="18pt" font-size="8pt"> <xsl:value-of select="product/price * quantity" /> </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>    
                    <!-- Total Area -->
                    
                </fo:flow>
            </fo:page-sequence>    

        </fo:root>

    </xsl:template>
</xsl:transform>