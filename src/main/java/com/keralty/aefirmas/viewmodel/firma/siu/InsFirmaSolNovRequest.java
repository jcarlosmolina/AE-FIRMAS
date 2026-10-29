package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.SolicitudNovedad;

public class InsFirmaSolNovRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaInsFirmaSolNovpthisFirma;
    
    @JsonIgnore
    private Firma firmaInsFirmaSolNovpthisFirmaInstance;

    @JsonProperty("p_evcsolicitudnovedad_oid")
    private SolicitudNovedadOid firmaInsFirmaSolNovpevcSolicitudNovedad;
    
    @JsonIgnore
    private SolicitudNovedad firmaInsFirmaSolNovpevcSolicitudNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaInsFirmaSolNovpthisFirma();
        checkFirmaInsFirmaSolNovpevcSolicitudNovedad();
    }

    @JsonIgnore
    public FirmaOid getFirmaInsFirmaSolNovpthisFirma() {
        return this.firmaInsFirmaSolNovpthisFirma;
    }
    
    public void setFirmaInsFirmaSolNovpthisFirma(FirmaOid firmaInsFirmaSolNovpthisFirma) {
        this.firmaInsFirmaSolNovpthisFirma = firmaInsFirmaSolNovpthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaInsFirmaSolNovpthisFirmaInstance() {
        return this.firmaInsFirmaSolNovpthisFirmaInstance;
    }
    
    public void setFirmaInsFirmaSolNovpthisFirmaInstance(Firma firmaInsFirmaSolNovpthisFirma) {
        this.firmaInsFirmaSolNovpthisFirmaInstance = firmaInsFirmaSolNovpthisFirma;
        this.firmaInsFirmaSolNovpthisFirma = firmaInsFirmaSolNovpthisFirma != null ? firmaInsFirmaSolNovpthisFirma.getOid() : null;
    }

    private void checkFirmaInsFirmaSolNovpthisFirma() throws NotNullArgumentException {
        if(this.getFirmaInsFirmaSolNovpthisFirmaInstance() == null || this.getFirmaInsFirmaSolNovpthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_INSFIRMASOLNOV, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_INSFIRMASOLNOV_FIRMAINSFIRMASOLNOVPTHISFIRMA, FirmaConstants.SERV_ALIAS_INSFIRMASOLNOV, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_INSFIRMASOLNOV_FIRMAINSFIRMASOLNOVPTHISFIRMA);
        }
    }

    @JsonIgnore
    public SolicitudNovedadOid getFirmaInsFirmaSolNovpevcSolicitudNovedad() {
        return this.firmaInsFirmaSolNovpevcSolicitudNovedad;
    }
    
    public void setFirmaInsFirmaSolNovpevcSolicitudNovedad(SolicitudNovedadOid firmaInsFirmaSolNovpevcSolicitudNovedad) {
        this.firmaInsFirmaSolNovpevcSolicitudNovedad = firmaInsFirmaSolNovpevcSolicitudNovedad;
    }

    @JsonIgnore
    public SolicitudNovedad getFirmaInsFirmaSolNovpevcSolicitudNovedadInstance() {
        return this.firmaInsFirmaSolNovpevcSolicitudNovedadInstance;
    }
    
    public void setFirmaInsFirmaSolNovpevcSolicitudNovedadInstance(SolicitudNovedad firmaInsFirmaSolNovpevcSolicitudNovedad) {
        this.firmaInsFirmaSolNovpevcSolicitudNovedadInstance = firmaInsFirmaSolNovpevcSolicitudNovedad;
        this.firmaInsFirmaSolNovpevcSolicitudNovedad = firmaInsFirmaSolNovpevcSolicitudNovedad != null ? firmaInsFirmaSolNovpevcSolicitudNovedad.getOid() : null;
    }

    private void checkFirmaInsFirmaSolNovpevcSolicitudNovedad() throws NotNullArgumentException {
        if(this.getFirmaInsFirmaSolNovpevcSolicitudNovedadInstance() == null || this.getFirmaInsFirmaSolNovpevcSolicitudNovedadInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_INSFIRMASOLNOV, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_INSFIRMASOLNOV_FIRMAINSFIRMASOLNOVPEVCSOLICITUDNOVEDAD, FirmaConstants.SERV_ALIAS_INSFIRMASOLNOV, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_INSFIRMASOLNOV_FIRMAINSFIRMASOLNOVPEVCSOLICITUDNOVEDAD);
        }
    }
}
