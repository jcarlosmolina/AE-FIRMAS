package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;

public class InsFirmaTitularFamiliaNovedadRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaInsFirmaTitularFamiliaNovedadpthisFirma;
    
    @JsonIgnore
    private Firma firmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance;

    @JsonProperty("p_evcfamilianovedad_oid")
    private FamiliaNovedadOid firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad;
    
    @JsonIgnore
    private FamiliaNovedad firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaInsFirmaTitularFamiliaNovedadpthisFirma();
        checkFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad();
    }

    @JsonIgnore
    public FirmaOid getFirmaInsFirmaTitularFamiliaNovedadpthisFirma() {
        return this.firmaInsFirmaTitularFamiliaNovedadpthisFirma;
    }
    
    public void setFirmaInsFirmaTitularFamiliaNovedadpthisFirma(FirmaOid firmaInsFirmaTitularFamiliaNovedadpthisFirma) {
        this.firmaInsFirmaTitularFamiliaNovedadpthisFirma = firmaInsFirmaTitularFamiliaNovedadpthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance() {
        return this.firmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance;
    }
    
    public void setFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance(Firma firmaInsFirmaTitularFamiliaNovedadpthisFirma) {
        this.firmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance = firmaInsFirmaTitularFamiliaNovedadpthisFirma;
        this.firmaInsFirmaTitularFamiliaNovedadpthisFirma = firmaInsFirmaTitularFamiliaNovedadpthisFirma != null ? firmaInsFirmaTitularFamiliaNovedadpthisFirma.getOid() : null;
    }

    private void checkFirmaInsFirmaTitularFamiliaNovedadpthisFirma() throws NotNullArgumentException {
        if(this.getFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance() == null || this.getFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_INSFIRMATITULARFAMILIANOVEDAD, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_INSFIRMATITULARFAMILIANOVEDAD_FIRMAINSFIRMATITULARFAMILIANOVEDADPTHISFIRMA, FirmaConstants.SERV_ALIAS_INSFIRMATITULARFAMILIANOVEDAD, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_INSFIRMATITULARFAMILIANOVEDAD_FIRMAINSFIRMATITULARFAMILIANOVEDADPTHISFIRMA);
        }
    }

    @JsonIgnore
    public FamiliaNovedadOid getFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad() {
        return this.firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad;
    }
    
    public void setFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad(FamiliaNovedadOid firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad) {
        this.firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad = firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad;
    }

    @JsonIgnore
    public FamiliaNovedad getFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance() {
        return this.firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance;
    }
    
    public void setFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance(FamiliaNovedad firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad) {
        this.firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance = firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad;
        this.firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad = firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad != null ? firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad.getOid() : null;
    }

    private void checkFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad() throws NotNullArgumentException {
        if(this.getFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance() == null || this.getFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_INSFIRMATITULARFAMILIANOVEDAD, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_INSFIRMATITULARFAMILIANOVEDAD_FIRMAINSFIRMATITULARFAMILIANOVEDADPEVCFAMILIANOVEDAD, FirmaConstants.SERV_ALIAS_INSFIRMATITULARFAMILIANOVEDAD, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_INSFIRMATITULARFAMILIANOVEDAD_FIRMAINSFIRMATITULARFAMILIANOVEDADPEVCFAMILIANOVEDAD);
        }
    }
}
