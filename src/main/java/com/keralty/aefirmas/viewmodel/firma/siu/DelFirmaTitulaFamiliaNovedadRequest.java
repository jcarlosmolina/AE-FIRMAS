package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;

public class DelFirmaTitulaFamiliaNovedadRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaDelFirmaTitulaFamiliaNovedadpthisFirma;
    
    @JsonIgnore
    private Firma firmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance;

    @JsonProperty("p_evcfamilianovedad_oid")
    private FamiliaNovedadOid firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad;
    
    @JsonIgnore
    private FamiliaNovedad firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaDelFirmaTitulaFamiliaNovedadpthisFirma();
        checkFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad();
    }

    @JsonIgnore
    public FirmaOid getFirmaDelFirmaTitulaFamiliaNovedadpthisFirma() {
        return this.firmaDelFirmaTitulaFamiliaNovedadpthisFirma;
    }
    
    public void setFirmaDelFirmaTitulaFamiliaNovedadpthisFirma(FirmaOid firmaDelFirmaTitulaFamiliaNovedadpthisFirma) {
        this.firmaDelFirmaTitulaFamiliaNovedadpthisFirma = firmaDelFirmaTitulaFamiliaNovedadpthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance() {
        return this.firmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance;
    }
    
    public void setFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance(Firma firmaDelFirmaTitulaFamiliaNovedadpthisFirma) {
        this.firmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance = firmaDelFirmaTitulaFamiliaNovedadpthisFirma;
        this.firmaDelFirmaTitulaFamiliaNovedadpthisFirma = firmaDelFirmaTitulaFamiliaNovedadpthisFirma != null ? firmaDelFirmaTitulaFamiliaNovedadpthisFirma.getOid() : null;
    }

    private void checkFirmaDelFirmaTitulaFamiliaNovedadpthisFirma() throws NotNullArgumentException {
        if(this.getFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance() == null || this.getFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_DELFIRMATITULAFAMILIANOVEDAD, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_DELFIRMATITULAFAMILIANOVEDAD_FIRMADELFIRMATITULAFAMILIANOVEDADPTHISFIRMA, FirmaConstants.SERV_ALIAS_DELFIRMATITULAFAMILIANOVEDAD, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_DELFIRMATITULAFAMILIANOVEDAD_FIRMADELFIRMATITULAFAMILIANOVEDADPTHISFIRMA);
        }
    }

    @JsonIgnore
    public FamiliaNovedadOid getFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad() {
        return this.firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad;
    }
    
    public void setFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad(FamiliaNovedadOid firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad) {
        this.firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad = firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad;
    }

    @JsonIgnore
    public FamiliaNovedad getFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance() {
        return this.firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance;
    }
    
    public void setFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance(FamiliaNovedad firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad) {
        this.firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance = firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad;
        this.firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad = firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad != null ? firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad.getOid() : null;
    }

    private void checkFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad() throws NotNullArgumentException {
        if(this.getFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance() == null || this.getFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_DELFIRMATITULAFAMILIANOVEDAD, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_DELFIRMATITULAFAMILIANOVEDAD_FIRMADELFIRMATITULAFAMILIANOVEDADPEVCFAMILIANOVEDAD, FirmaConstants.SERV_ALIAS_DELFIRMATITULAFAMILIANOVEDAD, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_DELFIRMATITULAFAMILIANOVEDAD_FIRMADELFIRMATITULAFAMILIANOVEDADPEVCFAMILIANOVEDAD);
        }
    }
}
