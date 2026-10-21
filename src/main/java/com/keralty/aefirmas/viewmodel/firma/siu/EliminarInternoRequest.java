package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;

public class EliminarInternoRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaeliminarInternopthisFirma;
    
    @JsonIgnore
    private Firma firmaeliminarInternopthisFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaeliminarInternopthisFirma();
    }

    @JsonIgnore
    public FirmaOid getFirmaeliminarInternopthisFirma() {
        return this.firmaeliminarInternopthisFirma;
    }
    
    public void setFirmaeliminarInternopthisFirma(FirmaOid firmaeliminarInternopthisFirma) {
        this.firmaeliminarInternopthisFirma = firmaeliminarInternopthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaeliminarInternopthisFirmaInstance() {
        return this.firmaeliminarInternopthisFirmaInstance;
    }
    
    public void setFirmaeliminarInternopthisFirmaInstance(Firma firmaeliminarInternopthisFirma) {
        this.firmaeliminarInternopthisFirmaInstance = firmaeliminarInternopthisFirma;
        this.firmaeliminarInternopthisFirma = firmaeliminarInternopthisFirma != null ? firmaeliminarInternopthisFirma.getOid() : null;
    }

    private void checkFirmaeliminarInternopthisFirma() throws NotNullArgumentException {
        if(this.getFirmaeliminarInternopthisFirmaInstance() == null || this.getFirmaeliminarInternopthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_ELIMINARINTERNO, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_ELIMINARINTERNO_FIRMAELIMINARINTERNOPTHISFIRMA, FirmaConstants.SERV_ALIAS_ELIMINARINTERNO, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_ELIMINARINTERNO_FIRMAELIMINARINTERNOPTHISFIRMA);
        }
    }
}
