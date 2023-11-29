<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version = "1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<table border = "4">
					<tr bgcolor = "9acd32">
						<th>ID</th>
						<th>Vezetéknév</th>
						<th>Keresztnév</th>
						<th>Becenév</th>
						<th>Kor</th>
						<th>Ösztöndij</th>
					</tr>
					
					<xsl:for-each select="class/student">
						<tr>
							<td>
								<xsl:value-of select = "@id"/>
							</td>
						
							<td><xsl:value-of select = "vezetéknév"/></td>
							<td><xsl:value-of select = "keresztnév"/></td>
							<td><xsl:value-of select = "becenév"/></td>
							<td><xsl:value-of select = "kor"/></td>
							<td><xsl:value-of select = "osztondij"/></td>
						</tr>
					</xsl:for-each>
					
				</table>
			</body>
		</html>
		
	</xsl:template>	

</xsl:stylesheet>