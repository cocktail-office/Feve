<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE xsl:stylesheet
[
<!ENTITY  nbsp  "&#160;">
<!ENTITY  space  "&#x20;">
<!ENTITY  br  "&#x2028;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    
    
    <!-- TPL SAUT DE PAGE-->
    <xsl:template name="sautDePage" match="sautDePage">
        <fo:table-row><fo:table-cell><fo:block break-after="page"></fo:block></fo:table-cell></fo:table-row>
    </xsl:template>
    <!-- TPL /SAUT DE PAGE-->
    
    
    <!-- TPL ENTETE -->
    <xsl:template name="entete">
        <fo:table>
            <fo:table-column />
            <fo:table-column />
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell text-align="left">
                        <fo:block font-size="8pt"><xsl:value-of select="etablissement"/></fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="right">
                        <fo:block font-size="8pt">Période <xsl:value-of select="periode"></xsl:value-of>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row height="1mm">
                    <fo:table-cell number-columns-spanned="2" border-top-style="solid" border-top-width="0.5pt"/>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /ENTETE -->
    
    
    
    
	<!-- TPL PIED DE PAGE -->
	<xsl:template name="piedDePage" match="piedDePage">
		<fo:table width="100%" table-layout="fixed" space-before="0pt" padding="0pt">
        	<fo:table-column/>
	        <fo:table-body>    
	        	<fo:table-row text-align="center" display-align="center">
		    		<fo:table-cell>
    		        	<fo:block font-size="8pt" text-align="center">
    		        		<fo:page-number/>/<fo:page-number-citation ref-id="last-page"/>
    		        	</fo:block>
     	  	    	</fo:table-cell>
     	   		</fo:table-row>
     		</fo:table-body>
    	</fo:table>
	</xsl:template>
    
     <!-- TPL TITRE -->
    <xsl:template name="titre" match="titre">
       <fo:table-row>
            <fo:table-cell>
                <fo:table border-style="solid" border-width="0.5pt" padding="7pt">
                    <fo:table-column/>
					<fo:table-body>
					
						<fo:table-row height="5mm"></fo:table-row>
						
						<fo:table-row><fo:table-cell text-align="center" display-align="center"><fo:block font-size="18pt"> COMPTE RENDU D'ENTRETIEN PROFESSIONNEL </fo:block></fo:table-cell></fo:table-row>
						
						<xsl:if test="dateTenueEntretien">
							<fo:table-row height="3mm"/>
							<fo:table-row><fo:table-cell text-align="center" display-align="center"><fo:block font-size="14pt"> 
								<fo:inline font-style="italic">entretien réalisé le <xsl:value-of select="dateTenueEntretien"/></fo:inline>
							</fo:block></fo:table-cell></fo:table-row>
						</xsl:if>
						
						<fo:table-row height="5mm"></fo:table-row>
					</fo:table-body>
				</fo:table>
            </fo:table-cell>
       </fo:table-row>
       <fo:table-row height="5mm"></fo:table-row>
      </xsl:template>
    <!--TPL /TITRE  -->

    
     <!-- TPL IDENTITE -->
     <xsl:template name="identite" match="identite">
          <fo:table-row height="10mm"/>
         <fo:table-row><fo:table-cell>
            <fo:table border-style="solid" border-width="0.5pt" padding="8pt" border-spacing="3mm" border-collapse="separate" font-size="16pt">
            <fo:table-column column-width="90mm"></fo:table-column>
            <fo:table-column></fo:table-column>
            <fo:table-body>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Identifiant :</fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="identifiant"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Nom : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="nom"></xsl:value-of> </fo:block></fo:table-cell></fo:table-row>
               	<fo:table-row><fo:table-cell><fo:block font-weight="bold"> Prénom : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="prenom"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Date de naissance : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="dNaissance"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>                
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Statut : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="statut"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Corps :</fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="corps"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Grade : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="grade"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Structure  : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="structure"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Responsable hiérarchique  : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="responsable"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
                <fo:table-row><fo:table-cell><fo:block font-weight="bold"> Emploi Type  : </fo:block></fo:table-cell><fo:table-cell><fo:block><xsl:value-of select="emploiType"></xsl:value-of></fo:block></fo:table-cell></fo:table-row>
             </fo:table-body>
            </fo:table>    
        </fo:table-cell>
        </fo:table-row>
       <fo:table-row height="10mm"/>
    </xsl:template>
    <!-- /IDENTITE -->
    
    
    
     <!-- TPL OBJECTIF PRECEDENT -->
    <xsl:template name="objectifPrecedent" match="objectifPrecedent">
       	<fo:table font-size="10pt">
         	<fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-body>
		        <fo:table-row keep-together="always">
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="objectif"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="moyen"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="mesure"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="resultats"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="observation"/></fo:block></fo:table-cell>
		        </fo:table-row>
           	</fo:table-body>    
        </fo:table>    
    </xsl:template>
    <!-- TPL /OBJECTIF PRECEDENT  -->
    
    <!-- TPL OBJECTIFS PRECEDENTS  -->
    <xsl:template name="objectifsPrecedents" match="objectifsPrecedents">
       <fo:table-row>
            <fo:table-cell>
                <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="concat('Revue du programme d',&quot;'&quot;,'objectifs de la période écoulée (', periodePrecedente,')')"/>
					<xsl:with-param name="sousTitre"
						select="'Résultats atteints au regard des missions et/ou des objectifs spécifiques fixés, moyens mis oeuvre et diagnostic des points forts et/ou à améliorer'"/>
				</xsl:call-template>
            </fo:table-cell>
        </fo:table-row>
                        
      	<fo:table-row>
            <fo:table-cell>
                <fo:table keep-together="always">
                    <fo:table-column/>
                    <fo:table-column/>
                    <fo:table-column/>
                    <fo:table-column/>
                    <fo:table-column/>
                   		<fo:table-header display-align="center" font-size="14pt" >
	                   		<fo:table-row  height="10mm" text-align="center" background-color="lightgrey">
    	               			<fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Objectifs</fo:block></fo:table-cell>
       	                       	<fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Moyens</fo:block></fo:table-cell>
                               	<fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Eléments  de mesure</fo:block></fo:table-cell>
                               	<fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Résultats obtenus</fo:block></fo:table-cell>
                               	<fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Observations</fo:block></fo:table-cell>    
                            </fo:table-row> 
                  		</fo:table-header>
                       <fo:table-body>
                           <fo:table-row>
  	                         <fo:table-cell number-columns-spanned="5">
  	                        	 <xsl:apply-templates select="objectifPrecedent"/>
  	                         </fo:table-cell>  
                           </fo:table-row> 
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
       	</fo:table-row>
    </xsl:template>
    <!-- TPL /OBJECTIFS PRECEDENTS -->
    
    
     <!-- TPL OBJECTIF SUIVANT -->
    <xsl:template name="objectifSuivant" match="objectifSuivant">
      	<fo:table>
         	<fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-body>
		        <fo:table-row keep-together="always">
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="objectif"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="moyen"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="mesure"/></fo:block></fo:table-cell>
		            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt"><fo:block><xsl:value-of select="observation"/></fo:block></fo:table-cell>
		        </fo:table-row>
           	</fo:table-body>    
        </fo:table>    
    </xsl:template>
    <!-- TPL /OBJECTIF SUIVANT  -->
    
    <!-- TPL OBJECTIFS SUIVANTS  -->
    <xsl:template name="objectifsSuivants" match="objectifsSuivants">
        <fo:table-row>
            <fo:table-cell>
                <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="concat('Revue du programme d',&quot;'&quot;,'objectifs de la période à venir (', periodeSuivante,')')"/>
				</xsl:call-template>
            </fo:table-cell>
        </fo:table-row>
        <fo:table-row>
            <fo:table-cell>
                 <fo:table>
                   <fo:table-column/>
                   <fo:table-column/>
                   <fo:table-column/>
                   <fo:table-column/>
        		   <fo:table-header display-align="center" font-size="14pt" >
	               		<fo:table-row  height="10mm" text-align="center" background-color="lightgrey">
    	               		<fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Objectifs</fo:block></fo:table-cell>
	                        <fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Moyens</fo:block></fo:table-cell>
	                         <fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Eléments  de mesure</fo:block></fo:table-cell>
	                         <fo:table-cell border-style="solid" border-width="0.5pt"><fo:block>Observations</fo:block></fo:table-cell>        
                        </fo:table-row> 
                   </fo:table-header>
                   <fo:table-body>
                   		<fo:table-row>
  	                		<fo:table-cell number-columns-spanned="4">
  	                        	 <xsl:apply-templates select="objectifSuivant"/>
  	                        </fo:table-cell>  
                        </fo:table-row> 
                   </fo:table-body> 
                 </fo:table>
			 </fo:table-cell>            
        </fo:table-row>
    </xsl:template>
    <!-- TPL /OBJECTIFS SUIVANTS -->

    
    
     <!-- TPL SITUATION -->
      <xsl:template name="situation" match="situation">
        <fo:table-row>
            <fo:table-cell>
                <fo:table border-style="solid" border-width="0.5pt">
                    <fo:table-column/>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block  font-weight="bold">Exposé situation d'activité <xsl:value-of select="numero"/> :</fo:block></fo:table-cell></fo:table-row>
                            <fo:table-row><fo:table-cell><fo:block><xsl:value-of select="libelle"/></fo:block></fo:table-cell></fo:table-row>
                          </fo:table-body>
                    </fo:table>
                </fo:table-cell>
        </fo:table-row>
         <fo:table-row height="5mm"></fo:table-row>
    </xsl:template>
    <!-- TPL /SITUATION  -->
    <!-- TPL SITUATIONS  -->
    <xsl:template name="situations" match="situations">
    
        <fo:table-row>
            <fo:table-cell>
                <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="concat('Situations d',&quot;'&quot;,'activité retenues')"/>
					<xsl:with-param name="sousTitre" 
						select="concat('La description des situations d',&quot;'&quot;,'activité sert de point de départ à l',&quot;'&quot;,'échange avec l',&quot;'&quot;,'agent sur les activités réalisées')"/>
				</xsl:call-template>
			</fo:table-cell>
		</fo:table-row>
                           
        <fo:table-row>
            <fo:table-cell>
                <fo:table>
                    <fo:table-column/>
                       <fo:table-body>
                           <xsl:apply-templates select="situation"></xsl:apply-templates>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /SITUATIONS -->

    
    <!-- TPL fdp -->
	 <xsl:template name="fdp" match="fdp">
        <fo:table-row background-color="#ADDFFF">
            <fo:table-cell padding="2pt" number-columns-spanned="2" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">       
				<fo:block text-align="center" font-weight="bold">Fiche de poste : <xsl:value-of select="libelle"/></fo:block></fo:table-cell>
        </fo:table-row>
   		<xsl:if test="count(competence) &gt; 0">
			<fo:table-row background-color="#F0F0F0">
				<fo:table-cell padding="2pt" number-columns-spanned="2" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">       
					<fo:block text-align="center">
						Compétences "REFERENS"
					</fo:block>
				</fo:table-cell>
			</fo:table-row>
			<xsl:apply-templates select="competence"/>
		</xsl:if>
		<xsl:if test="count(competenceAutre) &gt; 0">
			<fo:table-row background-color="#F0F0F0">
				<fo:table-cell padding="2pt" number-columns-spanned="2" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">       
					<fo:block text-align="center">
						Compétences "autres"
					</fo:block>
				</fo:table-cell>
			</fo:table-row>
			<xsl:apply-templates select="competenceAutre"/>
		</xsl:if>
    </xsl:template>
	<!-- TPL /fdp  -->
	
    <!-- TPL COMPETENCE -->
    <xsl:template name="competence" match="competence">
        <fo:table-row>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">       
				<fo:block><xsl:value-of select="libelle"/></fo:block></fo:table-cell>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center">                                   
				<fo:block><xsl:value-of select="niveau"/></fo:block></fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /COMPETENCE  -->
    
    <!-- TPL COMPETENCEAUTRE -->
    <xsl:template name="competenceAutre" match="competenceAutre">
        <fo:table-row>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">       
				<fo:block><xsl:value-of select="libelle"/></fo:block></fo:table-cell>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center">                                   
				<fo:block><xsl:value-of select="niveau"/></fo:block></fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /COMPETENCEAUTRE  -->
    
    <!-- TPL COMPETENCES  -->
    <xsl:template name="competences" match="competences" >
        <fo:table-row>
            <fo:table-cell>
                <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="'Positionnement des compétences attendues sur le poste'"/>
				</xsl:call-template>
			</fo:table-cell>
        </fo:table-row>
         <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/><fo:table-column  column-width="50mm"/>
                       <fo:table-body>
                           <fo:table-row  background-color="lightgrey" font-size="14pt">
                               <fo:table-cell padding="2pt"  border-style="solid" border-width="0.5pt" display-align="center">
                               		<fo:block>
                               			Compétences issues de la (des) fiche(s) de poste de l'agent
                               		</fo:block>
                               </fo:table-cell>
                               <fo:table-cell padding="2pt"  border-style="solid" border-width="0.5pt" display-align="center">
                               		<fo:block>
                               			Positionnement agent
                               		</fo:block>
                               	</fo:table-cell>
                           </fo:table-row> 
						   <xsl:apply-templates select="fdp"/>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
        </fo:table-row>
      </xsl:template>
    <!-- TPL /COMPETENCES -->

    
    
    
      
     <!-- TPL COMPETENCE ANNEXE-->
    <xsl:template name="competenceAnnexe" match="competenceAnnexe">
        <fo:table-row>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="left"><fo:block><xsl:value-of select="libelle"/></fo:block></fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /COMPETENCE ANNEXE -->
    
    <!-- TPL COMPETENCES ANNEXES -->
    <xsl:template name="competencesAnnexes" match="competencesAnnexes">
        <xsl:if test="hasCompetencesAnnexes = 'true'">
          <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/>
                       <fo:table-body>
                           <fo:table-row background-color="lightgrey">
                               <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center"><fo:block  font-weight="bold" >Compétences annexes repérées</fo:block></fo:table-cell>
                          </fo:table-row> 
                           <xsl:apply-templates select="competenceAnnexe"></xsl:apply-templates>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
         </fo:table-row>
         </xsl:if>
      </xsl:template>
    <!-- TPL /COMPETENCES ANNEXES -->

    
        
     <!-- TPL CHAMP LIBRE -->
    <xsl:template name="champlibre" match="champlibre">
        <fo:table-row height="8mm"/>
        <fo:table-row><fo:table-cell><fo:table><fo:table-column column-width="25mm"/><fo:table-column/><fo:table-body>  <fo:table-row>
            <fo:table-cell display-align="center" text-align="left"><fo:block font-weigth="bold">Champ libre : </fo:block></fo:table-cell>
            <fo:table-cell display-align="center"><fo:block><xsl:value-of select="champlibre"/></fo:block></fo:table-cell>
          </fo:table-row></fo:table-body></fo:table> </fo:table-cell></fo:table-row>
       <fo:table-row height="8mm"/>
     </xsl:template>
    <!-- TPL /CHAMP LIBRE  -->

    
        
    
    <!-- TPL competencesProfessionnellesEtTechnicites  -->
    <xsl:template name="competencesProfessionnellesEtTechnicites" match="competencesProfessionnellesEtTechnicites" >
         <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/><fo:table-column  column-width="50mm"/>
                       <fo:table-body>
                           <fo:table-row  background-color="lightgrey" font-size="14pt">
                               <fo:table-cell padding="2pt"  border-style="solid" border-width="0.5pt" display-align="center">
                               	<fo:block>Compétences professionnelles et technicité</fo:block></fo:table-cell>
                               <fo:table-cell padding="2pt"  border-style="solid" border-width="0.5pt" display-align="center"><fo:block>Appréciation</fo:block></fo:table-cell>
                           </fo:table-row> 
						   <xsl:apply-templates select="competence"/>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
        </fo:table-row>
      </xsl:template>
    <!-- TPL /competencesProfessionnellesEtTechnicites -->
    
    
    
    
    <!-- TPL management  -->
    <xsl:template name="management" match="management" >
        <xsl:if test="hasManagement = 'true'">
        <fo:table-row height="10mm"/>
         <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/><fo:table-column  column-width="50mm"/>
                       <fo:table-body>
                           <fo:table-row  background-color="lightgrey" font-size="14pt">
                               <fo:table-cell padding="2pt"  border-style="solid" border-width="0.5pt" display-align="center">
                               	<fo:block>
	                               	<xsl:value-of select="//bloc[@code='MANAGEMENT']/libelle"/>
                               </fo:block>
                            </fo:table-cell>
                               <fo:table-cell padding="2pt"  border-style="solid" border-width="0.5pt" display-align="center"><fo:block>Appréciation</fo:block></fo:table-cell>
                           </fo:table-row> 
						   <xsl:apply-templates select="competence"/>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
        </fo:table-row>
        </xsl:if>
    </xsl:template>
    <!-- TPL /management -->
    
    
        
    
    <!-- TPL contributionALActiviteDuService  -->
    <xsl:template name="contributionALActiviteDuService" match="contributionALActiviteDuService" >
        <fo:table-row>
            <fo:table-cell>
                <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="'Aptitudes'"/>
				</xsl:call-template>
            </fo:table-cell>
        </fo:table-row>
         <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/><fo:table-column  column-width="50mm"/>
                       <fo:table-body>
                           <fo:table-row  background-color="lightgrey" font-size="14pt">
                               <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center"><fo:block>Contribution à l'activité du service</fo:block></fo:table-cell>
                               <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center"><fo:block>Appréciation</fo:block></fo:table-cell>
                           </fo:table-row> 
						   <xsl:apply-templates select="competence"/>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /contributionALActiviteDuService -->
    
        
    
    <!-- TPL aualitesPersonnellesEtRelationnelles  -->
    <xsl:template name="aualitesPersonnellesEtRelationnelles" match="aualitesPersonnellesEtRelationnelles" >
        <fo:table-row height="10mm"/>
         <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/><fo:table-column  column-width="50mm"/>
                       <fo:table-body>
                           <fo:table-row  background-color="lightgrey" font-size="14pt">
                               <fo:table-cell  padding="2pt" border-style="solid" border-width="0.5pt" display-align="center"><fo:block>Qualités personnelles et relationnelles</fo:block></fo:table-cell>
                               <fo:table-cell  padding="2pt" border-style="solid" border-width="0.5pt" display-align="center"><fo:block>Appréciation</fo:block></fo:table-cell>
                           </fo:table-row> 
						   <xsl:apply-templates select="competence"/>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /aualitesPersonnellesEtRelationnelles -->
    
    
        
             
     <!-- TPL formationSuivie-->
    <xsl:template name="formationSuivie" match="formationSuivie">
        <fo:table-row>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">
            	<fo:block><xsl:value-of select="libelle"/></fo:block></fo:table-cell>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="center">
            	<fo:block><xsl:value-of select="debut"/></fo:block></fo:table-cell>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="center">
            	<fo:block><xsl:value-of select="fin"/></fo:block></fo:table-cell>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="center">
            	<fo:block><xsl:value-of select="duree"/>&nbsp;<xsl:value-of select="typeUniteTemps"/></fo:block></fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /formationSuivie -->
    
    
    
    <!-- TPL formationsSuivies -->
    <xsl:template name="formationsSuivies" match="formationsSuivies">
           <fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/>
                    <fo:table-column column-width="30mm"/>
                    <fo:table-column column-width="30mm"/>
                    <fo:table-column/>
                       <fo:table-body>
                           <fo:table-row background-color="lightgrey" font-size="14pt">
                               <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" number-columns-spanned="4">
                               		<fo:block padding="2pt" > Bilan des formations suivies</fo:block></fo:table-cell>
                          </fo:table-row> 
                          <fo:table-row>
                            <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" text-align="center" padding="2pt">
                            	<fo:block font-weight="bold">Libellé</fo:block></fo:table-cell>
                            <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" text-align="center" padding="2pt">
                            	<fo:block font-weight="bold">Début</fo:block></fo:table-cell>
                            <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" text-align="center" padding="2pt">
                            	<fo:block font-weight="bold">Fin</fo:block></fo:table-cell>
                            <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" text-align="center" padding="2pt">
                            	<fo:block font-weight="bold">Durée</fo:block></fo:table-cell>
                          </fo:table-row>
                          <xsl:apply-templates select="formationSuivie"></xsl:apply-templates>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
         </fo:table-row>      
    </xsl:template>
    <!-- TPL /formationsSuivies -->
    
        
             
     <!-- TPL formationSouhaitee-->
    <xsl:template name="formationSouhaitee" match="formationSouhaitee">
        <fo:table-row>
            <fo:table-cell padding="2pt" border-style="solid" border-width="0.5pt" display-align="center" text-align="left">
            	<fo:block><xsl:value-of select="libelle"/></fo:block></fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /formationSouhaitee -->
    
    
    
    <!-- TPL formationsSouhaitees -->
    <xsl:template name="formationsSouhaitees" match="formationsSouhaitees">
    	<fo:table-row>
            <fo:table-cell>
                <fo:table text-align="center">
                    <fo:table-column/>
                       <fo:table-body>
                           <fo:table-row background-color="lightgrey" font-size="14pt">
                               <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" number-columns-spanned="3">
                               		<fo:block padding="2pt" > Bilan des formations souhaitées</fo:block></fo:table-cell>
                          </fo:table-row> 
                          <fo:table-row>
                            <fo:table-cell border-style="solid" border-width="0.5pt" display-align="center" text-align="center" padding="2pt">
                            	<fo:block font-weight="bold">Libellé</fo:block></fo:table-cell>
                          </fo:table-row>
                          <xsl:apply-templates select="formationSouhaitee"></xsl:apply-templates>
                       </fo:table-body>    
                </fo:table>    
            </fo:table-cell>
         </fo:table-row>      
    </xsl:template>
    <!-- TPL /formationsSouhaitees -->
    
    
    <!-- TPL TITRE PAGE -->
    <xsl:template name = "titrePage">
    
	    <xsl:param name="titre"/>
	    <xsl:param name="sousTitre"/>
	    
	    <fo:table display-align="center">
        	<fo:table-column/>
            <fo:table-column column-width="160mm"/>
            <fo:table-column/>
            <fo:table-body>
            	<fo:table-row  height="10mm" text-align="center" padding="3pt">
                	<fo:table-cell/>
	            	<fo:table-cell border-style="solid" border-width="0.5pt" background-color="#ffebb9">
                    	<fo:block font-color="#222222" font-size="14pt">
							<xsl:value-of select="$titre"/>
	    				</fo:block>
                    </fo:table-cell>
                    <fo:table-cell/>
	            </fo:table-row>
	            
                <fo:table-row height="3mm"/>
				
				<xsl:if test="$sousTitre">
					<fo:table-row>
						<fo:table-cell number-columns-spanned="3">
							<fo:block text-align="center" font-size="12pt">
								<xsl:value-of select="$sousTitre"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

	                <fo:table-row height="3mm"/>
	
				</xsl:if>
                
     	   </fo:table-body>    
        </fo:table>    
	   
	    
    </xsl:template>    
  
  
  	<!-- TPL BLOC TEXTE -->
    <xsl:template name = "blocTexte">
    
	    <xsl:param name="titre"/>
	    <xsl:param name="sousTitre"/>
	    <xsl:param name="texte"/>
	    
	    <fo:table border-style="solid" border-width="0.5pt">
			<fo:table-column/>
			<fo:table-body>    
				<fo:table-row background-color="lightgrey" font-size="14pt">
					<fo:table-cell padding="2pt">
						<fo:block>
							<xsl:value-of select="$titre"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				
				<fo:table-row>
					<fo:table-cell background-color="#EEEEEE" padding="2pt">
						<fo:block font-size="11pt">
							<xsl:value-of select="$sousTitre"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				
				<fo:table-row height="30mm">
					<fo:table-cell padding="2pt">
					
						<xsl:if test="$texte">
							<fo:block>
								<xsl:value-of select="$texte"/>
							</fo:block>
						</xsl:if>
					
						<fo:table>
							<fo:table-column/>
							<fo:table-body>
															
								<xsl:apply-templates select="item"/>
								
							</fo:table-body>
						</fo:table>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
	    
    </xsl:template>    
    <!-- /TPL BLOC TEXTE -->
    
  	<!-- TPL CHAMP TEXTE -->
    <xsl:template name = "champTexte">
    
	    <xsl:param name="titre"/>
	    <xsl:param name="sousTitre"/>
	    <xsl:param name="texte"/>
	    
	    <fo:table>
			<fo:table-column/>
			<fo:table-body>    
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding="2pt" font-size="11pt">
							<fo:inline font-weight="bold">
								<xsl:value-of select="$titre"/>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:table>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block>
											<fo:inline font-weight="bold">
												<xsl:value-of select="$sousTitre"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block>
											<xsl:value-of select="$texte"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
	    
    </xsl:template>    
    <!-- /TPL CHAMP TEXTE -->

  
  	
    <!-- TPL EVOLUTIONS -->
    <xsl:template name="evolutions" match="evolutions">
		<fo:table-row>
            <fo:table-cell>
	            <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="//onglet[@code='EVAEVOL']/libelle"/>
				</xsl:call-template>
            </fo:table-cell>
        </fo:table-row>
        <fo:table-row>
         	<fo:table-cell>
         	
	         	<xsl:call-template name="blocTexte">
					<xsl:with-param name="titre" select="concat('Propositions d',&quot;'&quot;,'évolution sur le poste')"/>
					<xsl:with-param name="texte" select="evoluPropo"/>
				</xsl:call-template>
         	
         	</fo:table-cell>
         </fo:table-row>
         
         
       	<fo:table-row height="5mm"/>
       	
       	
        <fo:table-row>
       		<fo:table-cell>
       		
       			<xsl:call-template name="blocTexte">
					<xsl:with-param name="titre" select="concat('Perspectives d',&quot;'&quot;,'évolution de carrière')"/>
					<xsl:with-param name="texte" select="evoluEnvis"/>
				</xsl:call-template>
       		
          	</fo:table-cell>
        </fo:table-row>


		<fo:table-row height="5mm"/>
        	
        	
        <fo:table-row>
            	<fo:table-cell>
                	<fo:table border-style="solid" border-width="0.5pt">
                    	<fo:table-column/>
                    	<fo:table-body>    
                    	    <fo:table-row>
                    	        <fo:table-cell>
                    	            <fo:table>
                    	                <fo:table-column/>
                    	                <fo:table-body>
                    	                    <xsl:apply-templates select="formationsSuivies"/>
                    	                </fo:table-body>
                    	            </fo:table>
                    	       </fo:table-cell>
                   	     	</fo:table-row>
                    	</fo:table-body>
                	</fo:table>
            	</fo:table-cell>
        	</fo:table-row>   
        
        
        
      	<fo:table-row height="5mm"/>
        
        
        <fo:table-row>
           	<fo:table-cell>
               	<fo:table border-style="solid" border-width="0.5pt">
                   	<fo:table-column/>
                   	<fo:table-body>    
                       	<fo:table-row>
                           	<fo:table-cell>
                               	<fo:table>
                                   	<fo:table-column/>
                                   	<fo:table-body>
                                       	<xsl:apply-templates select="formationsSouhaitees"/>
                                   	</fo:table-body>
                               	</fo:table>
                          		</fo:table-cell>
                       	</fo:table-row>
                   	</fo:table-body>
               	</fo:table>
           	</fo:table-cell>
        </fo:table-row> 
    
    
      </xsl:template>
    <!-- TPL /EVOLUTIONS -->



    <!-- TPL EVOLUTIONS AGENT -->
    <xsl:template name="evolutionsAgent" match="evolutionsAgent">
        <fo:table-row>
            <fo:table-cell>
                <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="concat('Avis de l',&quot;'&quot;,'agent - page manuscrite')"/>
				</xsl:call-template>
            </fo:table-cell>
        </fo:table-row>
        
        <fo:table-row>
         	<fo:table-cell>
         	
                <xsl:call-template name="blocTexte">
					<xsl:with-param name="titre" select="concat('Déroulement de l',&quot;'&quot;,'entretien vu par l',&quot;'&quot;,'agent')"/>
					<xsl:with-param name="sousTitre" select="'Commentaires :'"/>
				</xsl:call-template>
				
			</fo:table-cell>
		</fo:table-row>
       	<fo:table-row height="5mm"/>
        
        <fo:table-row>
          	<fo:table-cell>
          	
                <xsl:call-template name="blocTexte">
					<xsl:with-param name="titre" select="concat('Evolutions souhaitées par l',&quot;'&quot;,'agent')"/>
					<xsl:with-param name="sousTitre" select="'Commentaires :'"/>
				</xsl:call-template>
				
          	</fo:table-cell>
        </fo:table-row>
        
     </xsl:template>
    <!-- TPL /EVOLUTIONS AGENT -->



    <!-- TPL SIGNATURES -->
    <xsl:template name="signatures" match="signatures">
    
    	<xsl:param name="isAfficherModalitesRecours"/>
    	<xsl:param name="isAfficherResponsableService"/>
    	<xsl:param name="isAfficherDate"/>
    
      	<fo:table-row height="5mm"/>
    
        <fo:table-row>
        	<fo:table-cell>
        		<fo:table font-size = "8px">
        			<fo:table-column/>
        			
        			<xsl:if test="$isAfficherResponsableService = 'false'">
	        			<fo:table-column column-width="58mm"/>
	        			<fo:table-column column-width="30mm"/>
	        			<fo:table-column column-width="58mm"/>
					</xsl:if>
					
        			<xsl:if test="$isAfficherResponsableService = 'true'">
	        			<fo:table-column column-width="48mm"/>
	        			<fo:table-column column-width="30mm"/>
	        			<fo:table-column column-width="48mm"/>
	        			<fo:table-column column-width="30mm"/>
	        			<fo:table-column column-width="48mm"/>
					</xsl:if>
					
        			<fo:table-column/>
        			<fo:table-body>
        				<fo:table-row height="20mm">
             				<fo:table-cell/>
				            
				            <fo:table-cell border="thin solid #000000" text-align="center" padding="4pt">
				            	<fo:block>
				            		<xsl:if test="$isAfficherDate = 'true'">
				            			Date et signature 
				            		</xsl:if>
				            		<xsl:if test="$isAfficherDate = 'false'">
				            			Signature 
				            		</xsl:if>
				            		de l'<fo:inline font-weight="bold">agent</fo:inline>
				            	</fo:block>
				            </fo:table-cell>
				            
             				<fo:table-cell/>
 				           	
 				           	<fo:table-cell border="thin solid #000000" text-align="center" padding="4pt">
 				           		<fo:block>
				            		<xsl:if test="$isAfficherDate = 'true'">
				            			Date et signature 
				            		</xsl:if>
				            		<xsl:if test="$isAfficherDate = 'false'">
				            			Signature 
				            		</xsl:if>
 				           			du <fo:inline font-weight="bold">responsable direct</fo:inline>
 				           		</fo:block>
 				           	</fo:table-cell>
            				
            				<xsl:if test="$isAfficherResponsableService = 'true'">

								<fo:table-cell/>
								
								<fo:table-cell border="thin solid #000000" text-align="center" padding="4pt">
									<fo:block>
				            		<xsl:if test="$isAfficherDate = 'true'">
				            			Date et signature 
				            		</xsl:if>
				            		<xsl:if test="$isAfficherDate = 'false'">
				            			Signature 
				            		</xsl:if>
										du <fo:inline font-weight="bold">responsable de service ou de composante</fo:inline>
									</fo:block>
								</fo:table-cell>
            				
            				</xsl:if>
            				
            				<fo:table-cell/>
  						</fo:table-row>
  					</fo:table-body>
  				</fo:table>
  			</fo:table-cell>
  		</fo:table-row>
       	
			
		<xsl:if test="$isAfficherModalitesRecours = 'true'">
			
			<fo:table-row height="5mm"/>
			
			<!-- les modalités de recours sont en dur pour l'instant .... -->
			<fo:table-row>
				<fo:table-cell>
					<xsl:call-template name="champTexte">
	
						<!-- xsl:with-param name="titre" 		select="'Modalités de recours'"/> -->
						<xsl:with-param name="titre" 		select="//bloc[@code='EVARECOURS']/libelle" />
						<xsl:with-param name="sousTitre" 	select="//item[@code='EVARECOURI']/commentaire"/>
						<xsl:with-param name="texte" 		select="//item[@code='EVARECOURI']/valeur"/>
					</xsl:call-template>
				</fo:table-cell>
			</fo:table-row>
				 

		</xsl:if>

     
     </xsl:template>
    <!-- TPL /SIGNATURES  -->




    
    <!-- TPL CHAMP TEXTE -->
    <xsl:template name = "noticeDePromotionsAvis">
    
	    <xsl:param name="titre"/>
	    <xsl:param name="sousTitre"/>
	    <xsl:param name="decision"/>
	    <xsl:param name="motif"/>
	    <xsl:param name="champLibre"/>
	
		<fo:table-row>
       		<fo:table-cell>
       			<fo:block>
	
	    <fo:table border-style="solid" border-width="0.5pt">
			<fo:table-column/>
			<fo:table-body>    
			
				<fo:table-row background-color="lightgrey" font-size="14pt">
					<fo:table-cell padding="2pt">
						<fo:block>
							<xsl:value-of select="$titre"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				
				<xsl:if test="$sousTitre">
					<fo:table-row>
						<fo:table-cell background-color="#EEEEEE" padding="2pt">
							<fo:block font-size="10pt">
								<xsl:value-of select="$sousTitre"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:if>
				
				<fo:table-row>
					<fo:table-cell>
						<fo:table padding="1pt">
							<fo:table-column/>
							<fo:table-body>
							
								<xsl:if test="$decision">
									<fo:table-row>
										<fo:table-cell>
											<fo:block  padding="2pt" font-size="11pt">
												Avis : <fo:inline font-weight="bold"><xsl:value-of select="$decision"/></fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
									
								<xsl:if test="$motif">
									<fo:table-row>
										<fo:table-cell padding="2pt" font-size="10pt">
											<fo:block>
												Motif : <xsl:value-of select="$motif"/>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>

								<xsl:if test="$champLibre">
									<fo:table-row>
										<fo:table-cell padding="2pt" font-size="10pt">
											<fo:block>
												<xsl:value-of select="$champLibre"/>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
								
							</fo:table-body>
						</fo:table>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
			
    </xsl:template>    
    <!-- /TPL CHAMP TEXTE -->

  
    
    
     <!-- TPL NOTICE DE PROMOTIONS -->
     <xsl:template name="noticeDePromotions" match="noticeDePromotions">
     
		<fo:table-row>
        	<fo:table-cell>
	            <xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="concat('Notice de promotions ', ../periode)"/>
				</xsl:call-template>
            </fo:table-cell>
        </fo:table-row>
     
		<fo:table-row>
        	<fo:table-cell>
				<fo:block border-style="solid" border-width="1pt" padding="8pt" text-align="center">
					<fo:inline font-weight="bold">Rappel :</fo:inline>
					aucune réduction / majoration d'ancienneté ou promotion ne peut être attribuée à un agent titulaire si l'entretien professionnel n'a pas été conduit
				</fo:block>
            </fo:table-cell>
        </fo:table-row>
     
     	<fo:table-row height="5mm"/>
    
    	<fo:table-row>
        	<fo:table-cell>
				<fo:table>
					<fo:table-column  column-width="30mm"/>
					<fo:table-column/>
				
					<fo:table-body>

						<fo:table-row>
							<fo:table-cell><fo:block text-align="right"><fo:inline font-weight="bold">Filière : </fo:inline></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="/FevFicheEvaluation/population"/></fo:block></fo:table-cell>
						</fo:table-row>

						<fo:table-row>
							<fo:table-cell><fo:block text-align="right"><fo:inline font-weight="bold">Corps : </fo:inline></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="/FevFicheEvaluation/corps"/></fo:block></fo:table-cell>
						</fo:table-row>

						<fo:table-row>
							<fo:table-cell><fo:block text-align="right"><fo:inline font-weight="bold">Grade : </fo:inline></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="/FevFicheEvaluation/grade"/></fo:block></fo:table-cell>
						</fo:table-row>

					</fo:table-body>
					
				</fo:table>
			</fo:table-cell>
		</fo:table-row>
	
     	<fo:table-row height="5mm"/>
     	
		<xsl:call-template name="noticeDePromotionsAvis">
			<xsl:with-param name="titre" select="concat('1. Avis sur une réduction du temps moyen d',&quot;'&quot;,'échelon')"/>
			<xsl:with-param name="decision" select="noticeDePromotionsReductionEchelon"/>
			<xsl:with-param name="motif" select="noticeDePromotionsReductionEchelonMotif"/>
		</xsl:call-template>
								
		<fo:table-row height="5mm"/>
								
		<xsl:call-template name="noticeDePromotionsAvis">
			<xsl:with-param name="titre" select="'2. Avis sur une promotion de grade'"/>
			<xsl:with-param name="decision" select="noticeDePromotionsPromotionGrade"/>
			<xsl:with-param name="motif" select="noticeDePromotionsPromotionGradeMotif"/>
		</xsl:call-template>
		
		<fo:table-row height="5mm"/>

		<xsl:call-template name="noticeDePromotionsAvis">
			<xsl:with-param name="titre" select="'3. Avis sur une promotion de corps'"/>
			<xsl:with-param name="decision" select="noticeDePromotionsPromotionCorps"/>
			<xsl:with-param name="motif" select="noticeDePromotionsPromotionCorpsMotif"/>
		</xsl:call-template>
		
		<fo:table-row height="5mm"/>

		<xsl:call-template name="noticeDePromotionsAvis">
			<xsl:with-param name="titre" select="'4. Avis général sur une promotion'"/>
			<xsl:with-param name="champLibre" select="noticeDePromotionsAvisGeneralSurUnePromotion"/>
		</xsl:call-template>
		
		<fo:table-row height="5mm"/>

		<xsl:call-template name="noticeDePromotionsAvis">
			<xsl:with-param name="titre" select="concat('5. Observations éventuelles de l',&quot;'&quot;,'agent (manuscrites)')"/>
			<xsl:with-param name="champLibre" select="'.&#x2028;.&#x2028;.&#x2028;.'"/>
		</xsl:call-template>
		
		<xsl:call-template name="signatures">
			<xsl:with-param name = "isAfficherModalitesRecours" select = "'false'"/>
			<xsl:with-param name = "isAfficherResponsableService" select = "'true'"/>
			<xsl:with-param name = "isAfficherDate" select = "'false'"/>
		</xsl:call-template>
		
    </xsl:template>
    <!-- /NOTICE DE PROMOTIONS -->
    


    <!-- TPL ONGLET -->
    <xsl:template name="onglet" match="onglet">
   		
   		<!-- pour l'instant que le bilan de l'année écoulée et l'appréciation générale -->
   		<xsl:if test="@code = 'BILANECOUL' or @code = 'SYNTVALPRO'">
			<fo:table-row>
		
				<fo:table-cell>
					<xsl:call-template name="titrePage">
						<xsl:with-param name="titre" select="libelle"/>
					</xsl:call-template>
				</fo:table-cell>
				
			</fo:table-row>
			
			<xsl:apply-templates select="bloc"/>
		
			<xsl:call-template name="sautDePage"/>
   		</xsl:if>   	
        
     </xsl:template>
    <!-- TPL /ONGLET -->
    

	<!-- TPL onglet unique -->    
    <xsl:template name="ongletUnique" match="ongletUnique">
    
   		<xsl:param name="codeAAfficher"/>
   		
   		<fo:table-row>
		
			<fo:table-cell>
				
				<xsl:call-template name="titrePage">
					<xsl:with-param name="titre" select="$codeAAfficher/libelle"/>
				</xsl:call-template>

			</fo:table-cell>
				
		</fo:table-row>
		
		<xsl:apply-templates select="$codeAAfficher/bloc"/>
	
		<xsl:call-template name="sautDePage"/>
    
    </xsl:template>
    <!-- TPL /onglet unique -->
    
    <!-- TPL BLOC -->
    <xsl:template name="bloc" match="bloc">
   		
		<fo:table-row>
			<fo:table-cell>
			
				<xsl:call-template name="blocTexte">

					<xsl:with-param name="titre" 		select="libelle"/>
					<xsl:with-param name="sousTitre" 	select="commentaire"/>
					
				</xsl:call-template>
			</fo:table-cell>
		</fo:table-row>
		
		<fo:table-row height="5mm"/>
   	    
     </xsl:template>
    <!-- TPL /BLOC -->
    
    
    <!-- TPL ITEM -->
    <xsl:template name="item" match="item">
   		
		<fo:table-row>
			<fo:table-cell>
				<xsl:call-template name="champTexte">

					<xsl:with-param name="titre" 		select="libelle"/>
					<xsl:with-param name="sousTitre" 	select="commentaire"/>
					<xsl:with-param name="texte" 		select="valeur"/>
					
				</xsl:call-template>
			</fo:table-cell>
		</fo:table-row>
		
		<fo:table-row height="5mm"/>
   	    
     </xsl:template>
    <!-- TPL /ITEM -->



    
      <!-- TPL FICHE -->
    <xsl:template name="fiche" match="fiche">
        <fo:table>
            <fo:table-column/>
            <fo:table-body>
                <xsl:call-template name="titre"/>
               	<xsl:call-template name="identite"/>
                <xsl:call-template name="sautDePage"/>
                
                
                <xsl:apply-templates select="objectifsPrecedents"/>
                <xsl:call-template name="sautDePage"/>
                
                
                <xsl:apply-templates select="objectifsSuivants"/>
                <xsl:call-template name="sautDePage"/>
                
                <xsl:if test="//onglet[@code='BILANECOUL']">
           	    	<xsl:call-template name="ongletUnique">
           	     		<xsl:with-param name="codeAAfficher" select="//onglet[@code='BILANECOUL']"/>
           	 		</xsl:call-template>
                </xsl:if>

				<xsl:if test="situations">
	                <xsl:apply-templates select="situations"/>
	                <xsl:call-template name="sautDePage"/>
	            </xsl:if>


                <xsl:apply-templates select="competences"/>
                
                <xsl:apply-templates select="competencesAnnexes"/>
                
                <xsl:if test="champlibre">
	                <xsl:call-template name="champlibre"/>
	            </xsl:if>
	            
                <xsl:apply-templates select="competencesProfessionnellesEtTechnicites"/>
                
                <xsl:apply-templates select="management"/>                
                <xsl:call-template name="sautDePage"/>
                
                
                <xsl:apply-templates select="contributionALActiviteDuService"/>
                <xsl:apply-templates select="aualitesPersonnellesEtRelationnelles"/>
                <xsl:call-template name="sautDePage"/>
                
                <!--<xsl:if test="onglet">
	                <xsl:apply-templates select="onglet"/>
	            </xsl:if>-->
	            
	            <xsl:if test="//onglet[@code='SYNTVALPRO']">
					<xsl:call-template name="ongletUnique">
						<xsl:with-param name="codeAAfficher" select="//onglet[@code='SYNTVALPRO']"/>
					</xsl:call-template>
				</xsl:if>

                <xsl:call-template name="evolutions"/>
                
                <!-- saut de page si l'evolution de l'agent ou la signature est a afficher -->
			    <xsl:if test="showEvolutionAgent = 'true' or showSignatures = 'true'">
	                <xsl:call-template name="sautDePage"/>
		    	</xsl:if>

                <!-- on affiche les evolutions que si explicitement demandé (pas le cas pour la fiche brouillon) -->
			    <xsl:if test="showEvolutionAgent = 'true'">
            	    <xsl:call-template name="evolutionsAgent"/>
            	</xsl:if>

                <!-- on affiche les signatures que si explicitement demandé (pas le cas pour la fiche brouillon) -->
			    <xsl:if test="showSignatures = 'true'">
	                <xsl:call-template name="signatures">
               			<xsl:with-param name="isAfficherModalitesRecours" select="'true'"/>
 						<xsl:with-param name = "isAfficherResponsableService" select = "'false'"/>
						<xsl:with-param name = "isAfficherDate" select = "'true'"/>
				    </xsl:call-template>
   				</xsl:if>           
   				
   				<!-- notice de promotions -->
   				<xsl:if test="noticeDePromotions">
	   				<xsl:call-template name="sautDePage"/>
	   				<xsl:apply-templates select = "noticeDePromotions"/>
	   			</xsl:if>
   				
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL FevFicheEvaluation -->
    
    
    
    <xsl:template name="FevFicheEvaluation" match="FevFicheEvaluation">
        <fo:static-content flow-name="xsl-region-end"></fo:static-content>
        <fo:static-content flow-name="xsl-region-after">
            <xsl:call-template name="piedDePage"/>
        </fo:static-content>
        <fo:static-content flow-name="xsl-region-before">
            <xsl:call-template name="entete"/>
        </fo:static-content>
        <fo:static-content flow-name="xsl-region-start"></fo:static-content>
        <fo:flow flow-name="xsl-region-body" font-size="10pt">
            <xsl:call-template name="fiche"/>
            <fo:block id="last-page"/>
        </fo:flow>
    </xsl:template>
    
    <!-- TPL toto (racine) -->
    <xsl:template name="toto" match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="page" margin-top="1cm" margin-bottom="0cm" page-width="29.7cm" page-height="21cm" margin-left="1cm" margin-right="1cm">
                    <fo:region-before extent="5mm" margin="0pt"/>
                    <fo:region-after extent="5mm"/>
                    <fo:region-start/>
                    <fo:region-end/>
                    <fo:region-body margin-bottom="7mm" margin-top="7mm" margin-left="0cm" margin-right="0cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="page">
                <xsl:apply-templates select="FevFicheEvaluation"/>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
