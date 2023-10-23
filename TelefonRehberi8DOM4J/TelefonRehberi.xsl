<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
		    <body>
		         <h2>Kişi Listesi</h2>
		         
		         <xsl:apply-templates select="kisilistesi" />
		         
		          
                         <table border="1">
		           <tr bgcolor="#FFFF00">
                       <th>Tüzel Kişi Listesi</th>
		               <th>Şirket İsmi</th>
		               <th>Kuruluş Tarihi </th>
		               <th>Ticaret Sicil Numarası</th>
		               <th>Merkez Yeri</th>
		               <th>İletişim Numarası</th>
		               <th>İletişim Adresi</th>
		               <th>Kuruluş Sermayesi</th>
		               <th>Şirket Türü</th>
		               <th>Bağlı Olduğu Vergi Dairesi</th>
                       <th>Faaliyet Gösterdiği Sektör</th>
		           </tr>

		           <xsl:for-each select="kisilistesi/tuzelkisi">
		                  <tr>
		                  <td></td>
		                  <td><xsl:value-of select="isim"/></td>
		                  <td><xsl:value-of select="kurulustarihi"/></td>
		                  <td><xsl:value-of select="verginumarasi"/></td>
                          <td><xsl:value-of select="hizmetyeri"/></td> 
		                  <td><xsl:value-of select="iletisimnumarasi"/></td>
		                  <td><xsl:value-of select="iletisimadresi"/></td>
		                  <td><xsl:value-of select="kurulussermayesi"/></td>
		                  <td><xsl:value-of select="sirketturu"/></td>
		                  <td><xsl:value-of select="bagliolduguvergidairesi"/></td> 
                          <td><xsl:value-of select="faaliyetsektoru"/></td> 
		                  </tr>
		          </xsl:for-each>
		         </table>
		         <table border="1">
		           <tr bgcolor="#9acd32">
                       <th>Gerçek Kişi Listesi</th>
		               <th>İsim</th>
		               <th>Doğum Tarihi </th>
		               <th>Kimlik Numarası</th>
		               <th>İkametgah Yeri</th>
		               <th>İletişim Numarası</th>
		               <th>İletişim Adresi</th>
		               <th>Öğrenim Durumu</th>
		               <th>Mesleği</th>
		               <th>Sicil Kaydı</th>
		           </tr>
		            
		           <xsl:for-each select="kisilistesi/gercekkisi">
		                  <tr>
		                  <td></td>
		                  <td><xsl:value-of select="isim"/></td>
		                  <td><xsl:value-of select="dogumtarihi"/></td>
		                  <td><xsl:value-of select="kimliknumarasi"/></td>
		                  <td><xsl:value-of select="ikametgahyeri"/></td>
		                  <td><xsl:value-of select="iletisimnumarasi"/></td>
		                  <td><xsl:value-of select="iletisimadresi"/></td>
		                  <td><xsl:value-of select="ogrenimdurumu"/></td>
		                  <td><xsl:value-of select="meslegi"/></td>
		                  <td><xsl:value-of select="sicilkaydi"/></td>               
		                  </tr>	
		          </xsl:for-each>
		          
		         </table>
		         
		    </body>
		</html>
	</xsl:template>
	
	<xsl:template match="kisilistesi">
		<table border="1">
		           <tr bgcolor="#FFFF00">
                       <th>Tüzel Kişi Listesi</th>
		               <th>Şirket İsmi</th>
		               <th>Kuruluş Tarihi </th>
		               <th>Ticaret Sicil Numarası</th>
		               <th>Merkez Yeri</th>
		               <th>İletişim Numarası</th>
		               <th>İletişim Adresi</th>
		               <th>Kuruluş Sermayesi</th>
		               <th>Şirket Türü</th>
		               <th>Bağlı Olduğu Vergi Dairesi</th>
                       <th>Faaliyet Gösterdiği Sektör</th>
		           </tr>
			<xsl:apply-templates select="tuzelkisi" />		           
		</table>
		
		<table border="1">
		           <tr bgcolor="#9acd32">
                       <th>Gerçek Kişi Listesi</th>
		               <th>İsim</th>
		               <th>Doğum Tarihi </th>
		               <th>Kimlik Numarası</th>
		               <th>İkametgah Yeri</th>
		               <th>İletişim Numarası</th>
		               <th>İletişim Adresi</th>
		               <th>Öğrenim Durumu</th>
		               <th>Mesleği</th>
		               <th>Sicil Kaydı</th>
		           </tr>
		           <xsl:apply-templates select="gercekkisi" />
		</table>
		
	</xsl:template>
	
	<xsl:template match="tuzelkisi">
		                  <tr>
		                  <td></td>
		                  <td><xsl:value-of select="isim"/></td>
		                  <td><xsl:value-of select="kurulustarihi"/></td>
		                  <td><xsl:value-of select="verginumarasi"/></td>
                          <td><xsl:value-of select="hizmetyeri"/></td> 
		                  <td><xsl:value-of select="iletisimnumarasi"/></td>
		                  <td><xsl:value-of select="iletisimadresi"/></td>
		                  <td><xsl:value-of select="kurulussermayesi"/></td>
		                  <td><xsl:value-of select="sirketturu"/></td>
		                  <td><xsl:value-of select="bagliolduguvergidairesi"/></td> 
                          <td><xsl:value-of select="faaliyetsektoru"/></td> 
		                  </tr>	
	</xsl:template>
	
	<xsl:template match="gercekkisi">
		                  <tr>
		                  <td></td>
		                  <td><xsl:value-of select="isim"/></td>
		                  <td><xsl:value-of select="dogumtarihi"/></td>
		                  <td><xsl:value-of select="kimliknumarasi"/></td>
		                  <td><xsl:value-of select="ikametgahyeri"/></td>
		                  <td><xsl:value-of select="iletisimnumarasi"/></td>
		                  <td><xsl:value-of select="iletisimadresi"/></td>
		                  <td><xsl:value-of select="ogrenimdurumu"/></td>
		                  <td><xsl:value-of select="meslegi"/></td>
		                  <td><xsl:value-of select="sicilkaydi"/></td>               
		                  </tr>		
	</xsl:template>
	
</xsl:stylesheet>




