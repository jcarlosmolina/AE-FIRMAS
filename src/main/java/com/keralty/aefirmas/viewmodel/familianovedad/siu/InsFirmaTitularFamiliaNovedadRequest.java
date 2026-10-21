package com.keralty.aefirmas.viewmodel.familianovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FamiliaNovedadConstants;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;

public class InsFirmaTitularFamiliaNovedadRequest {

    @JsonProperty("p_thisfamilianovedad_oid")
    private FamiliaNovedadOid familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad;
    
    @JsonIgnore
    private FamiliaNovedad familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance;

    @JsonProperty("p_evcfirma_oid")
    private FirmaOid familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma;
    
    @JsonIgnore
    private Firma familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad();
        checkFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirma();
    }

    @JsonIgnore
    public FamiliaNovedadOid getFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad() {
        return this.familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad;
    }
    
    public void setFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad(FamiliaNovedadOid familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad) {
        this.familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad = familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad;
    }

    @JsonIgnore
    public FamiliaNovedad getFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance() {
        return this.familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance;
    }
    
    public void setFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance(FamiliaNovedad familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad) {
        this.familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance = familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad;
        this.familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad = familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad != null ? familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad.getOid() : null;
    }

    private void checkFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad() throws NotNullArgumentException {
        if(this.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance() == null || this.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance().isNull()) {
            throw new NotNullArgumentException(FamiliaNovedadConstants.SERV_ID_INSFIRMATITULARFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ID, FamiliaNovedadConstants.ARG_ID_INSFIRMATITULARFAMILIANOVEDAD_FAMILIANOVEDADINSFIRMATITULARFAMILIANOVEDADPTHISFAMILIANOVEDAD, FamiliaNovedadConstants.SERV_ALIAS_INSFIRMATITULARFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ALIAS, FamiliaNovedadConstants.ARG_ALIAS_INSFIRMATITULARFAMILIANOVEDAD_FAMILIANOVEDADINSFIRMATITULARFAMILIANOVEDADPTHISFAMILIANOVEDAD);
        }
    }

    @JsonIgnore
    public FirmaOid getFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirma() {
        return this.familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma;
    }
    
    public void setFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirma(FirmaOid familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma) {
        this.familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma = familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma;
    }

    @JsonIgnore
    public Firma getFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance() {
        return this.familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance;
    }
    
    public void setFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance(Firma familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma) {
        this.familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance = familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma;
        this.familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma = familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma != null ? familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma.getOid() : null;
    }

    private void checkFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirma() throws NotNullArgumentException {
        if(this.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance() == null || this.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FamiliaNovedadConstants.SERV_ID_INSFIRMATITULARFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ID, FamiliaNovedadConstants.ARG_ID_INSFIRMATITULARFAMILIANOVEDAD_FAMILIANOVEDADINSFIRMATITULARFAMILIANOVEDADPEVCFIRMA, FamiliaNovedadConstants.SERV_ALIAS_INSFIRMATITULARFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ALIAS, FamiliaNovedadConstants.ARG_ALIAS_INSFIRMATITULARFAMILIANOVEDAD_FAMILIANOVEDADINSFIRMATITULARFAMILIANOVEDADPEVCFIRMA);
        }
    }
}
