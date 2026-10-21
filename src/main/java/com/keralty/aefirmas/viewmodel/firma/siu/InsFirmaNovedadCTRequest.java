package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;

public class InsFirmaNovedadCTRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaInsFirmaNovedadCTpthisFirma;
    
    @JsonIgnore
    private Firma firmaInsFirmaNovedadCTpthisFirmaInstance;

    @JsonProperty("p_evcnovedadct_oid")
    private NovedadCTOid firmaInsFirmaNovedadCTpevcNovedadCT;
    
    @JsonIgnore
    private NovedadCT firmaInsFirmaNovedadCTpevcNovedadCTInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaInsFirmaNovedadCTpthisFirma();
        checkFirmaInsFirmaNovedadCTpevcNovedadCT();
    }

    @JsonIgnore
    public FirmaOid getFirmaInsFirmaNovedadCTpthisFirma() {
        return this.firmaInsFirmaNovedadCTpthisFirma;
    }
    
    public void setFirmaInsFirmaNovedadCTpthisFirma(FirmaOid firmaInsFirmaNovedadCTpthisFirma) {
        this.firmaInsFirmaNovedadCTpthisFirma = firmaInsFirmaNovedadCTpthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaInsFirmaNovedadCTpthisFirmaInstance() {
        return this.firmaInsFirmaNovedadCTpthisFirmaInstance;
    }
    
    public void setFirmaInsFirmaNovedadCTpthisFirmaInstance(Firma firmaInsFirmaNovedadCTpthisFirma) {
        this.firmaInsFirmaNovedadCTpthisFirmaInstance = firmaInsFirmaNovedadCTpthisFirma;
        this.firmaInsFirmaNovedadCTpthisFirma = firmaInsFirmaNovedadCTpthisFirma != null ? firmaInsFirmaNovedadCTpthisFirma.getOid() : null;
    }

    private void checkFirmaInsFirmaNovedadCTpthisFirma() throws NotNullArgumentException {
        if(this.getFirmaInsFirmaNovedadCTpthisFirmaInstance() == null || this.getFirmaInsFirmaNovedadCTpthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_INSFIRMANOVEDADCT, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_INSFIRMANOVEDADCT_FIRMAINSFIRMANOVEDADCTPTHISFIRMA, FirmaConstants.SERV_ALIAS_INSFIRMANOVEDADCT, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_INSFIRMANOVEDADCT_FIRMAINSFIRMANOVEDADCTPTHISFIRMA);
        }
    }

    @JsonIgnore
    public NovedadCTOid getFirmaInsFirmaNovedadCTpevcNovedadCT() {
        return this.firmaInsFirmaNovedadCTpevcNovedadCT;
    }
    
    public void setFirmaInsFirmaNovedadCTpevcNovedadCT(NovedadCTOid firmaInsFirmaNovedadCTpevcNovedadCT) {
        this.firmaInsFirmaNovedadCTpevcNovedadCT = firmaInsFirmaNovedadCTpevcNovedadCT;
    }

    @JsonIgnore
    public NovedadCT getFirmaInsFirmaNovedadCTpevcNovedadCTInstance() {
        return this.firmaInsFirmaNovedadCTpevcNovedadCTInstance;
    }
    
    public void setFirmaInsFirmaNovedadCTpevcNovedadCTInstance(NovedadCT firmaInsFirmaNovedadCTpevcNovedadCT) {
        this.firmaInsFirmaNovedadCTpevcNovedadCTInstance = firmaInsFirmaNovedadCTpevcNovedadCT;
        this.firmaInsFirmaNovedadCTpevcNovedadCT = firmaInsFirmaNovedadCTpevcNovedadCT != null ? firmaInsFirmaNovedadCTpevcNovedadCT.getOid() : null;
    }

    private void checkFirmaInsFirmaNovedadCTpevcNovedadCT() throws NotNullArgumentException {
        if(this.getFirmaInsFirmaNovedadCTpevcNovedadCTInstance() == null || this.getFirmaInsFirmaNovedadCTpevcNovedadCTInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_INSFIRMANOVEDADCT, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_INSFIRMANOVEDADCT_FIRMAINSFIRMANOVEDADCTPEVCNOVEDADCT, FirmaConstants.SERV_ALIAS_INSFIRMANOVEDADCT, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_INSFIRMANOVEDADCT_FIRMAINSFIRMANOVEDADCTPEVCNOVEDADCT);
        }
    }
}
