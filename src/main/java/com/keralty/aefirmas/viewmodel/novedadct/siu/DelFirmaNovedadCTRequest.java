package com.keralty.aefirmas.viewmodel.novedadct.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.NovedadCTConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;

public class DelFirmaNovedadCTRequest {

    @JsonProperty("p_thisnovedadct_oid")
    private NovedadCTOid novedadCTDelFirmaNovedadCTpthisNovedadCT;
    
    @JsonIgnore
    private NovedadCT novedadCTDelFirmaNovedadCTpthisNovedadCTInstance;

    @JsonProperty("p_evcfirma_oid")
    private FirmaOid novedadCTDelFirmaNovedadCTpevcFirma;
    
    @JsonIgnore
    private Firma novedadCTDelFirmaNovedadCTpevcFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadCTDelFirmaNovedadCTpthisNovedadCT();
        checkNovedadCTDelFirmaNovedadCTpevcFirma();
    }

    @JsonIgnore
    public NovedadCTOid getNovedadCTDelFirmaNovedadCTpthisNovedadCT() {
        return this.novedadCTDelFirmaNovedadCTpthisNovedadCT;
    }
    
    public void setNovedadCTDelFirmaNovedadCTpthisNovedadCT(NovedadCTOid novedadCTDelFirmaNovedadCTpthisNovedadCT) {
        this.novedadCTDelFirmaNovedadCTpthisNovedadCT = novedadCTDelFirmaNovedadCTpthisNovedadCT;
    }

    @JsonIgnore
    public NovedadCT getNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance() {
        return this.novedadCTDelFirmaNovedadCTpthisNovedadCTInstance;
    }
    
    public void setNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance(NovedadCT novedadCTDelFirmaNovedadCTpthisNovedadCT) {
        this.novedadCTDelFirmaNovedadCTpthisNovedadCTInstance = novedadCTDelFirmaNovedadCTpthisNovedadCT;
        this.novedadCTDelFirmaNovedadCTpthisNovedadCT = novedadCTDelFirmaNovedadCTpthisNovedadCT != null ? novedadCTDelFirmaNovedadCTpthisNovedadCT.getOid() : null;
    }

    private void checkNovedadCTDelFirmaNovedadCTpthisNovedadCT() throws NotNullArgumentException {
        if(this.getNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance() == null || this.getNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance().isNull()) {
            throw new NotNullArgumentException(NovedadCTConstants.SERV_ID_DELFIRMANOVEDADCT, NovedadCTConstants.CLASS_ID, NovedadCTConstants.ARG_ID_DELFIRMANOVEDADCT_NOVEDADCTDELFIRMANOVEDADCTPTHISNOVEDADCT, NovedadCTConstants.SERV_ALIAS_DELFIRMANOVEDADCT, NovedadCTConstants.CLASS_ALIAS, NovedadCTConstants.ARG_ALIAS_DELFIRMANOVEDADCT_NOVEDADCTDELFIRMANOVEDADCTPTHISNOVEDADCT);
        }
    }

    @JsonIgnore
    public FirmaOid getNovedadCTDelFirmaNovedadCTpevcFirma() {
        return this.novedadCTDelFirmaNovedadCTpevcFirma;
    }
    
    public void setNovedadCTDelFirmaNovedadCTpevcFirma(FirmaOid novedadCTDelFirmaNovedadCTpevcFirma) {
        this.novedadCTDelFirmaNovedadCTpevcFirma = novedadCTDelFirmaNovedadCTpevcFirma;
    }

    @JsonIgnore
    public Firma getNovedadCTDelFirmaNovedadCTpevcFirmaInstance() {
        return this.novedadCTDelFirmaNovedadCTpevcFirmaInstance;
    }
    
    public void setNovedadCTDelFirmaNovedadCTpevcFirmaInstance(Firma novedadCTDelFirmaNovedadCTpevcFirma) {
        this.novedadCTDelFirmaNovedadCTpevcFirmaInstance = novedadCTDelFirmaNovedadCTpevcFirma;
        this.novedadCTDelFirmaNovedadCTpevcFirma = novedadCTDelFirmaNovedadCTpevcFirma != null ? novedadCTDelFirmaNovedadCTpevcFirma.getOid() : null;
    }

    private void checkNovedadCTDelFirmaNovedadCTpevcFirma() throws NotNullArgumentException {
        if(this.getNovedadCTDelFirmaNovedadCTpevcFirmaInstance() == null || this.getNovedadCTDelFirmaNovedadCTpevcFirmaInstance().isNull()) {
            throw new NotNullArgumentException(NovedadCTConstants.SERV_ID_DELFIRMANOVEDADCT, NovedadCTConstants.CLASS_ID, NovedadCTConstants.ARG_ID_DELFIRMANOVEDADCT_NOVEDADCTDELFIRMANOVEDADCTPEVCFIRMA, NovedadCTConstants.SERV_ALIAS_DELFIRMANOVEDADCT, NovedadCTConstants.CLASS_ALIAS, NovedadCTConstants.ARG_ALIAS_DELFIRMANOVEDADCT_NOVEDADCTDELFIRMANOVEDADCTPEVCFIRMA);
        }
    }
}
