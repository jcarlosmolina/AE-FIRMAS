package com.keralty.aefirmas.viewmodel.novedadct.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.NovedadCTConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;

public class InsFirmaNovedadCTRequest {

    @JsonProperty("p_thisnovedadct_oid")
    private NovedadCTOid novedadCTInsFirmaNovedadCTpthisNovedadCT;
    
    @JsonIgnore
    private NovedadCT novedadCTInsFirmaNovedadCTpthisNovedadCTInstance;

    @JsonProperty("p_evcfirma_oid")
    private FirmaOid novedadCTInsFirmaNovedadCTpevcFirma;
    
    @JsonIgnore
    private Firma novedadCTInsFirmaNovedadCTpevcFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadCTInsFirmaNovedadCTpthisNovedadCT();
        checkNovedadCTInsFirmaNovedadCTpevcFirma();
    }

    @JsonIgnore
    public NovedadCTOid getNovedadCTInsFirmaNovedadCTpthisNovedadCT() {
        return this.novedadCTInsFirmaNovedadCTpthisNovedadCT;
    }
    
    public void setNovedadCTInsFirmaNovedadCTpthisNovedadCT(NovedadCTOid novedadCTInsFirmaNovedadCTpthisNovedadCT) {
        this.novedadCTInsFirmaNovedadCTpthisNovedadCT = novedadCTInsFirmaNovedadCTpthisNovedadCT;
    }

    @JsonIgnore
    public NovedadCT getNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance() {
        return this.novedadCTInsFirmaNovedadCTpthisNovedadCTInstance;
    }
    
    public void setNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance(NovedadCT novedadCTInsFirmaNovedadCTpthisNovedadCT) {
        this.novedadCTInsFirmaNovedadCTpthisNovedadCTInstance = novedadCTInsFirmaNovedadCTpthisNovedadCT;
        this.novedadCTInsFirmaNovedadCTpthisNovedadCT = novedadCTInsFirmaNovedadCTpthisNovedadCT != null ? novedadCTInsFirmaNovedadCTpthisNovedadCT.getOid() : null;
    }

    private void checkNovedadCTInsFirmaNovedadCTpthisNovedadCT() throws NotNullArgumentException {
        if(this.getNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance() == null || this.getNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance().isNull()) {
            throw new NotNullArgumentException(NovedadCTConstants.SERV_ID_INSFIRMANOVEDADCT, NovedadCTConstants.CLASS_ID, NovedadCTConstants.ARG_ID_INSFIRMANOVEDADCT_NOVEDADCTINSFIRMANOVEDADCTPTHISNOVEDADCT, NovedadCTConstants.SERV_ALIAS_INSFIRMANOVEDADCT, NovedadCTConstants.CLASS_ALIAS, NovedadCTConstants.ARG_ALIAS_INSFIRMANOVEDADCT_NOVEDADCTINSFIRMANOVEDADCTPTHISNOVEDADCT);
        }
    }

    @JsonIgnore
    public FirmaOid getNovedadCTInsFirmaNovedadCTpevcFirma() {
        return this.novedadCTInsFirmaNovedadCTpevcFirma;
    }
    
    public void setNovedadCTInsFirmaNovedadCTpevcFirma(FirmaOid novedadCTInsFirmaNovedadCTpevcFirma) {
        this.novedadCTInsFirmaNovedadCTpevcFirma = novedadCTInsFirmaNovedadCTpevcFirma;
    }

    @JsonIgnore
    public Firma getNovedadCTInsFirmaNovedadCTpevcFirmaInstance() {
        return this.novedadCTInsFirmaNovedadCTpevcFirmaInstance;
    }
    
    public void setNovedadCTInsFirmaNovedadCTpevcFirmaInstance(Firma novedadCTInsFirmaNovedadCTpevcFirma) {
        this.novedadCTInsFirmaNovedadCTpevcFirmaInstance = novedadCTInsFirmaNovedadCTpevcFirma;
        this.novedadCTInsFirmaNovedadCTpevcFirma = novedadCTInsFirmaNovedadCTpevcFirma != null ? novedadCTInsFirmaNovedadCTpevcFirma.getOid() : null;
    }

    private void checkNovedadCTInsFirmaNovedadCTpevcFirma() throws NotNullArgumentException {
        if(this.getNovedadCTInsFirmaNovedadCTpevcFirmaInstance() == null || this.getNovedadCTInsFirmaNovedadCTpevcFirmaInstance().isNull()) {
            throw new NotNullArgumentException(NovedadCTConstants.SERV_ID_INSFIRMANOVEDADCT, NovedadCTConstants.CLASS_ID, NovedadCTConstants.ARG_ID_INSFIRMANOVEDADCT_NOVEDADCTINSFIRMANOVEDADCTPEVCFIRMA, NovedadCTConstants.SERV_ALIAS_INSFIRMANOVEDADCT, NovedadCTConstants.CLASS_ALIAS, NovedadCTConstants.ARG_ALIAS_INSFIRMANOVEDADCT_NOVEDADCTINSFIRMANOVEDADCTPEVCFIRMA);
        }
    }
}
