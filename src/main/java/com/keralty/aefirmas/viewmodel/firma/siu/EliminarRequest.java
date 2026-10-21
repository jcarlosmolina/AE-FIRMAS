package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;

public class EliminarRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaeliminarpthisFirma;
    
    @JsonIgnore
    private Firma firmaeliminarpthisFirmaInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaeliminarpthisFirma();
    }

    @JsonIgnore
    public FirmaOid getFirmaeliminarpthisFirma() {
        return this.firmaeliminarpthisFirma;
    }
    
    public void setFirmaeliminarpthisFirma(FirmaOid firmaeliminarpthisFirma) {
        this.firmaeliminarpthisFirma = firmaeliminarpthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaeliminarpthisFirmaInstance() {
        return this.firmaeliminarpthisFirmaInstance;
    }
    
    public void setFirmaeliminarpthisFirmaInstance(Firma firmaeliminarpthisFirma) {
        this.firmaeliminarpthisFirmaInstance = firmaeliminarpthisFirma;
        this.firmaeliminarpthisFirma = firmaeliminarpthisFirma != null ? firmaeliminarpthisFirma.getOid() : null;
    }

    private void checkFirmaeliminarpthisFirma() throws NotNullArgumentException {
        if(this.getFirmaeliminarpthisFirmaInstance() == null || this.getFirmaeliminarpthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_ELIMINAR, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_ELIMINAR_FIRMAELIMINARPTHISFIRMA, FirmaConstants.SERV_ALIAS_ELIMINAR, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_ELIMINAR_FIRMAELIMINARPTHISFIRMA);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
