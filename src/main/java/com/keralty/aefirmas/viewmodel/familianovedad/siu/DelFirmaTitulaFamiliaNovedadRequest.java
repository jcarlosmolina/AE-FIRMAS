package com.keralty.aefirmas.viewmodel.familianovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FamiliaNovedadConstants;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;

public class DelFirmaTitulaFamiliaNovedadRequest {

    @JsonProperty("p_thisfamilianovedad_oid")
    private FamiliaNovedadOid familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad;
    
    @JsonIgnore
    private FamiliaNovedad familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance;

    @JsonProperty("p_evcfirma_oid")
    private FirmaOid familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma;
    
    @JsonIgnore
    private Firma familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad();
        checkFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma();
    }

    @JsonIgnore
    public FamiliaNovedadOid getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad() {
        return this.familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad;
    }
    
    public void setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad(FamiliaNovedadOid familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad) {
        this.familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad = familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad;
    }

    @JsonIgnore
    public FamiliaNovedad getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance() {
        return this.familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance;
    }
    
    public void setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance(FamiliaNovedad familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad) {
        this.familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance = familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad;
        this.familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad = familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad != null ? familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad.getOid() : null;
    }

    private void checkFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad() throws NotNullArgumentException {
        if(this.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance() == null || this.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance().isNull()) {
            throw new NotNullArgumentException(FamiliaNovedadConstants.SERV_ID_DELFIRMATITULAFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ID, FamiliaNovedadConstants.ARG_ID_DELFIRMATITULAFAMILIANOVEDAD_FAMILIANOVEDADDELFIRMATITULAFAMILIANOVEDADPTHISFAMILIANOVEDAD, FamiliaNovedadConstants.SERV_ALIAS_DELFIRMATITULAFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ALIAS, FamiliaNovedadConstants.ARG_ALIAS_DELFIRMATITULAFAMILIANOVEDAD_FAMILIANOVEDADDELFIRMATITULAFAMILIANOVEDADPTHISFAMILIANOVEDAD);
        }
    }

    @JsonIgnore
    public FirmaOid getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma() {
        return this.familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma;
    }
    
    public void setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma(FirmaOid familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma) {
        this.familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma = familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma;
    }

    @JsonIgnore
    public Firma getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance() {
        return this.familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance;
    }
    
    public void setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance(Firma familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma) {
        this.familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance = familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma;
        this.familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma = familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma != null ? familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma.getOid() : null;
    }

    private void checkFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma() throws NotNullArgumentException {
        if(this.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance() == null || this.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FamiliaNovedadConstants.SERV_ID_DELFIRMATITULAFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ID, FamiliaNovedadConstants.ARG_ID_DELFIRMATITULAFAMILIANOVEDAD_FAMILIANOVEDADDELFIRMATITULAFAMILIANOVEDADPEVCFIRMA, FamiliaNovedadConstants.SERV_ALIAS_DELFIRMATITULAFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_ALIAS, FamiliaNovedadConstants.ARG_ALIAS_DELFIRMATITULAFAMILIANOVEDAD_FAMILIANOVEDADDELFIRMATITULAFAMILIANOVEDADPEVCFIRMA);
        }
    }
}
