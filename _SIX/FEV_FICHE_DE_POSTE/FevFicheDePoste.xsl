<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE xsl:stylesheet
[
<!ENTITY  nbsp  "&#160;">
<!ENTITY  space  "&#x20;">
<!ENTITY  br  "&#x2028;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <!-- TPL ENTETE -->
    <xsl:template name="entete">
        <fo:table>
            <fo:table-column></fo:table-column>
            <fo:table-column></fo:table-column>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell text-align="left">
                        <fo:block font-size="8pt"><xsl:value-of select="etablissement"/></fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="right">
                        <fo:block font-size="8pt">Fiche de poste <xsl:value-of select="libelle"></xsl:value-of>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell number-columns-spanned="2" border-top-style="solid" border-top-width="0.5pt">
                        <fo:block font-size="8pt"></fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row height="3mm"></fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /ENTETE -->
    <!-- TPL COLLABORATEUR -->
    <xsl:template name="collaborateur" match="collaborateur">
        <xsl:value-of select="."></xsl:value-of>, &nbsp; </xsl:template>
    <!-- TPL /COLLABORATEUR -->
    <!-- TPL COLLABORATEURS -->
    <xsl:template name="collaborateurs" match="collaborateurs">
        <fo:table>
            <fo:table-column></fo:table-column>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:apply-templates select="collaborateur"></xsl:apply-templates>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /COLLABORATEURS-->
    
    <!-- TPL BLOCACTIVITESREFERENS -->
    <xsl:template name="blocActivitesReferens" match="blocActivitesReferens">
	    <xsl:param name="font-size"/>
		<fo:table-cell>
			<fo:block>
				<fo:table border-style="solid" border-width="0.5pt" padding="5pt">
					<fo:table-column/>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell>
								<fo:table>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:block text-align="center" font-weight="bold">
													Activités essentielles du poste
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row height="3mm"/>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
                                                                             
													<fo:inline text-decoration="underline"> Activités REFERENS</fo:inline>
												</fo:block>
                                                                        
											</fo:table-cell>
                                                                    
										</fo:table-row>
                                                                    
										<fo:table-row height="2mm"/>
                                                                    
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
                                                                                 
													<xsl:apply-templates select="activites">
														<xsl:with-param name="font-size" select="$font-size"/>
													</xsl:apply-templates>
												</fo:block>
                                                                        
											</fo:table-cell>
                                                                    
										</fo:table-row>
                                                                    
										<!-- on affiche la rubrique activités autre que s'il y en a -->
                                                                   
										<xsl:if test="count(activitesAutres) &gt; 0">
         
											<fo:table-row height="3mm"/>
                                
											<fo:table-row>
                                                
												<fo:table-cell>
													<fo:block>
                                                                             
														<fo:inline text-decoration="underline"> Activités autres</fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
                                                                    
											<fo:table-row height="2mm"/>
                                                                    
											<fo:table-row>
                                                                        
												<fo:table-cell>
													<fo:block>
                                                                             
														<xsl:apply-templates select="activitesAutres">
															<xsl:with-param name="font-size" select="$font-size"/>
														</xsl:apply-templates>
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
    </xsl:template>
   	<!-- TPL BLOCACTIVITESREFERENS -->
   
    <!-- TPL ACTIVITE -->
    <xsl:template name="activite" match="activite">
	    <xsl:param name="font-size"/>
        <fo:table-row>
            <fo:table-cell>
                <fo:block>
	                <xsl:attribute name="font-size">
	                	<xsl:value-of select="$font-size"/>
	                </xsl:attribute>
	               - <xsl:value-of select="."/>
	            </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /ACTIVITE -->
    <!-- TPL ACTIVITES -->
    <xsl:template name="activites" match="activites">
    	<xsl:param name="font-size"/>
        <fo:table>
            <fo:table-column/>
            <fo:table-body>
                <xsl:apply-templates select="activite">
					<xsl:with-param name="font-size" select="$font-size"/>
				</xsl:apply-templates>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /ACTIVITES-->
    <!-- TPL ACTIVITEAUTRE -->
    <xsl:template name="activiteAutre" match="activiteAutre">
	    <xsl:param name="font-size"/>
        <fo:table-row>
            <fo:table-cell>
                <fo:block>
	                <xsl:attribute name="font-size">
	                	<xsl:value-of select="$font-size"/>
	                </xsl:attribute>
                    - <xsl:value-of select="."/>
                </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /ACTIVITEAUTRE -->
    <!-- TPL ACTIVITEAUTRES -->
    <xsl:template name="activitesAutres" match="activitesAutres">
	    <xsl:param name="font-size"/>
        <fo:table>
           <fo:table-column/>
            <fo:table-body>
                <xsl:apply-templates select="activiteAutre">
                	<xsl:with-param name="font-size" select="$font-size"/>
                </xsl:apply-templates>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /ACTIVITEAUTRES-->
    
    <!--BLOCCOMPETENCESREFERENS -->
	<xsl:template name="blocCompetencesReferens" match="blocCompetencesReferens">                 
		 <xsl:param name="font-size"/>
		<fo:table-cell>
			<fo:block>
				<fo:table border-style="solid" border-width="0.5pt" padding="5pt">
					<fo:table-column/>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell>
								<fo:table>
									<fo:table-column></fo:table-column>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:block text-align="center" font-weight="bold"> Compétences essentielles du poste</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row height="3mm"/>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
                                                                            	
													<fo:inline text-decoration="underline"> Compétences REFERENS</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row height="2mm"/>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
                                                                            	
													<xsl:apply-templates select="competences">
														<xsl:with-param name="font-size" select="$font-size"/>
													</xsl:apply-templates>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<!-- on affiche la rubrique activités autre que s'il y en a -->
										<xsl:if test="count(competencesAutres) &gt; 0">
										<fo:table-row height="3mm"/>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													<fo:inline text-decoration="underline"> Compétences autres</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row height="2mm"/>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													<xsl:apply-templates select="competencesAutres">
														<xsl:with-param name="font-size" select="$font-size"/>
													</xsl:apply-templates>
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
	</xsl:template>
	<!--/BLOCCOMPETENCESREFERENS -->

    
    <!-- TPL COMPETENCE -->
    <xsl:template name="competence" match="competence">
	    <xsl:param name="font-size"/>
        <fo:table-row>
            <fo:table-cell>
                <fo:block>
	                <xsl:attribute name="font-size">
	                	<xsl:value-of select="$font-size"/>
	                </xsl:attribute>
                    - <xsl:value-of select="."/>
                </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /COMPETENCE -->
    <!-- TPL COMPETENCES -->
    <xsl:template name="competences" match="competences">
	    <xsl:param name="font-size"/>
        <fo:table>
            <fo:table-column/>
            <fo:table-body>
                <xsl:apply-templates select="competence">
					<xsl:with-param name="font-size" select="$font-size"/>
				</xsl:apply-templates>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /COMPETENCES-->
    <!-- TPL COMPETENCEAUTRE -->
    <xsl:template name="competenceAutre" match="competenceAutre">
        <xsl:param name="font-size"/>
        <fo:table-row>
            <fo:table-cell>
                <fo:block>
	                <xsl:attribute name="font-size">
	                	<xsl:value-of select="$font-size"/>
	                </xsl:attribute>
                    - <xsl:value-of select="."></xsl:value-of>
                </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    <!-- TPL /COMPETENCEAUTRE -->
    <!-- TPL COMPETENCEAUTRES -->
    <xsl:template name="competencesAutres" match="competencesAutres">
	    <xsl:param name="font-size"/>
        <fo:table>
           <fo:table-column/>
            <fo:table-body>
                <xsl:apply-templates select="competenceAutre">
                	<xsl:with-param name="font-size" select="$font-size"/>
                </xsl:apply-templates>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- TPL /COMPETENCEAUTRES-->
    
    <!-- TPL FICHE -->
    <xsl:template name="fiche" match="fiche">
        <fo:table>
            <fo:table-column></fo:table-column>
            <fo:table-body>
                <!-- TITRE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt">
                            <fo:table-column></fo:table-column>
                            <fo:table-body>
                                <fo:table-row height="3mm"></fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell text-align="center" display-align="center">
                                        <fo:block font-size="16pt"> FICHE DE POSTE </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row height="3mm"></fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /TITRE  -->
                <xsl:if test="showInfosPersonelles = 'true'">
                <fo:table-row height="3mm"></fo:table-row>
                <!-- IDENTITE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column column-width="50mm"></fo:table-column>
                            <fo:table-column></fo:table-column>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Identifiant :</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="identifiant"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Nom : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="nom"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Prénom : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="prenom"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Date de naissance : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="dNaissance"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Statut : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="statut"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Corps :</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="corps"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Grade : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="grade"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /IDENTITE -->
                </xsl:if>
                <fo:table-row height="3mm"></fo:table-row>
                <!-- REFERENS -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column column-width="50mm"></fo:table-column>
                            <fo:table-column></fo:table-column>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> BAP : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="dcp"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Famille professionnelle: </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="famillePro"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Emploi Type : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="emploiType"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /REFERENS -->
                <fo:table-row height="3mm"></fo:table-row>
                <!-- COMPOSANTE/STRUCTURE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column column-width="60mm"></fo:table-column>
                            <fo:table-column></fo:table-column>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Composante : </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="composante"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Structure de rattachement: </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="structure"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <xsl:if test="poste">
	                                <fo:table-row>
    	                        		<fo:table-cell>
       	                             		<fo:block font-weight="bold"> Poste: </fo:block>
       	                            	</fo:table-cell>
       	                             	<fo:table-cell>
       	                             		<fo:block>
       	                                 		<xsl:value-of select="poste"></xsl:value-of>
       	                                 	</fo:block>
       	                             	</fo:table-cell>
       	                     		</fo:table-row>
       	                     	</xsl:if>
       	                     	
       	                     
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /COMPOSANTE/STRUCTURE -->
                <fo:table-row height="3mm"></fo:table-row>
                <!-- MISSIONCOMPOSANTE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                           <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Missions de la composante</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="missionComposante"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
               <!-- /MISSIONCOMPOSANTE -->
                <fo:table-row height="3mm"></fo:table-row>
                <!-- MISSIONSERVICE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Missions du service</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="missionService"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /MISSIONSERVICE -->
                <fo:table-row height="3mm"></fo:table-row>
                <!-- PROJETSERVICE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Projet du service</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="projetService"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /PROJETSERVICE -->
            	<fo:table-row>
                    <fo:table-cell>
                        <fo:block break-after="page"></fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <!-- MISSIONPOSTE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold"> Missions liées au poste</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="missionPoste"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /MISSIONPOSTE -->
                <fo:table-row height="3mm"></fo:table-row>
                <!-- CONTEXTE -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table border-style="solid" border-width="0.5pt" padding="1pt" border-spacing="3mm" border-collapse="separate">
                            <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                 <fo:table-cell>
                                        <fo:block font-weight="bold">Contexte et environnement de travail</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block > <xsl:value-of select="contexte"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /CONTEXTE -->
                <fo:table-row height="3mm"></fo:table-row>
                
                
                <!-- ACTIVITESCOMPETENCES -->
                <fo:table-row>
                    <fo:table-cell>
                    
	                    <xsl:choose>
	                    	<xsl:when test="blocActiCompVertical and blocActiCompVertical = 'true'">
	                    		
	                    		<!-- présentation verticale -->	                    		
	                    		<fo:table>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
											<!--activites -->
											<xsl:call-template name="blocActivitesReferens">
												<xsl:with-param name="font-size" select="'10pt'"/>
											</xsl:call-template>
										</fo:table-row>

										<fo:table-row height="3mm"/>
										
										<fo:table-row>
											<!--competences -->
											<xsl:call-template name="blocCompetencesReferens">
												<xsl:with-param name="font-size" select="'10pt'"/>
											</xsl:call-template>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
	                    		
	                    		
	                    	</xsl:when>
							<xsl:otherwise>
						 		
						 		<!-- présentation horizontale -->
	                    		<fo:table>
									<fo:table-column/>
									<fo:table-column column-width="10mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
											<!--activites -->
											<xsl:call-template name="blocActivitesReferens">
												<xsl:with-param name="font-size" select="'8pt'"/>
											</xsl:call-template>
											<fo:table-cell/>
											<!--competences -->
											<xsl:call-template name="blocCompetencesReferens">
												<xsl:with-param name="font-size" select="'8pt'"/>
											</xsl:call-template>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
								
	                      	</xsl:otherwise>
						</xsl:choose>
                    	
                    
                        
                        
                        
                    </fo:table-cell>
                </fo:table-row>
                <!-- /ACTIVITESCOMPETENCES -->
      
                
                
                <xsl:if test="showInfosPersonelles = 'true'">
                <fo:table-row height="10mm"></fo:table-row>
                <!-- SIGNATURES -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:table>
                            <fo:table-column column-width="5mm"/>
                            <fo:table-column/>
                            <fo:table-column column-width="5mm"/>
                            <fo:table-column/>
                            <fo:table-column column-width="5mm"/>
                            <fo:table-column/>
                            <fo:table-column column-width="5mm"/>
                            <fo:table-body>
                                <fo:table-row>
                                	<fo:table-cell/>
                                    <!-- REPONSABLE-->
                                    <fo:table-cell border-style="solid" border-width="0.5pt" padding="8pt">
                                        <fo:block text-align="center">Date: ..................</fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block text-align="center" font-size="8pt">Nom et signature&br;du responsable (N+1)</fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                    </fo:table-cell>
                                    <!-- /REPONSABLE -->
                                	<fo:table-cell/>
                                    <!-- REPONSABLECOMPOSANTE-->
                                    <fo:table-cell border-style="solid" border-width="0.5pt" padding="8pt">
                                        <fo:block text-align="center">Date: ..................</fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block text-align="center" font-size="8pt">Visa du directeur ou du responsable administratif de la composante</fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                    </fo:table-cell>
                                    <!-- /REPONSABLECOMPOSANTE -->
                                	<fo:table-cell/>
                                    <!-- AGENT-->
                                    <fo:table-cell border-style="solid" border-width="0.5pt" padding="8pt">
                                        <fo:block text-align="center">Date: ..................</fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block text-align="center" font-size="8pt">Signature de l'agent</fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                        <fo:block>&nbsp; </fo:block>
                                    </fo:table-cell>
                                    <!-- /AGENT -->
                                	<fo:table-cell/>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:table-cell>
                </fo:table-row>
                <!-- /SIGNATURES -->
                </xsl:if>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    
    <!-- TPL FevFicheDePoste -->
    <xsl:template name="FevFicheDePoste" match="FevFicheDePoste">
        <fo:static-content flow-name="xsl-region-end"></fo:static-content>
        <fo:static-content flow-name="xsl-region-after"></fo:static-content>
        <fo:static-content flow-name="xsl-region-before">
            <xsl:call-template name="entete"></xsl:call-template>
        </fo:static-content>
        <fo:static-content flow-name="xsl-region-start"></fo:static-content>
        <fo:flow flow-name="xsl-region-body" font-size="10pt">
            <xsl:call-template name="fiche"></xsl:call-template>
        </fo:flow>
    </xsl:template>
    <!-- TPL toto (racine) -->
    <xsl:template name="toto" match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="page" margin-top="1cm" margin-bottom="1cm" page-width="21cm" page-height="29.7cm" margin-left="1cm" margin-right="1cm">
                    <fo:region-before extent="10mm" margin="0pt"></fo:region-before>
                    <fo:region-after extent="0mm"></fo:region-after>
                    <fo:region-start></fo:region-start>
                    <fo:region-end></fo:region-end>
                    <fo:region-body margin-bottom="0mm" margin-top="10mm" margin-left="0cm" margin-right="0cm"></fo:region-body>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="page">
                <xsl:apply-templates select="FevFicheDePoste"></xsl:apply-templates>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
