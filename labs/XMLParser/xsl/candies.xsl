<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">

        <html>
            <body>
                <h1>My Favorite Candies</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Energy</th>


                        <th>Proteins</th>
                        <th>Fat</th>
                        <th>Carbohydrates</th>
                        <th>Production</th>
                    </tr>
                    <xsl:for-each select="Candies/Candy">
                        <tr>
                            <td>
                                <xsl:value-of select="ID"/>
                            </td>
                            <td>
                                <xsl:value-of select="Type"/>
                            </td>
                            <td>
                                <xsl:value-of select="Name"/>
                            </td>
                            <td>
                                <xsl:value-of select="Energy"/>
                            </td>
                            <td>
                                <xsl:value-of select="Value/Protein"/>
                            </td>
                            <td>
                                <xsl:value-of select="Value/Fat"/>
                            </td>
                            <td>
                                <xsl:value-of select="Value/Carbohydrate"/>
                            </td>
                            <td>
                                <xsl:value-of select="Production"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>