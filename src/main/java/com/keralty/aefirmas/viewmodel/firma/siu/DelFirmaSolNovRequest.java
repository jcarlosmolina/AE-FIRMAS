package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.SolicitudNovedad;

public class DelFirmaSolNovRequest {

    @JsonProperty("p_thisfirma_oid")
    private FirmaOid firmaDelFirmaSolNovpthisFirma;
    
    @JsonIgnore
    private Firma firmaDelFirmaSolNovpthisFirmaInstance;

    @JsonProperty("p_evcsolicitudnovedad_oid")
    private SolicitudNovedadOid firmaDelFirmaSolNovpevcSolicitudNovedad;
    
    @JsonIgnore
    private SolicitudNovedad firmaDelFirmaSolNovpevcSolicitudNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaDelFirmaSolNovpthisFirma();
        checkFirmaDelFirmaSolNovpevcSolicitudNovedad();
    }

    @JsonIgnore
    public FirmaOid getFirmaDelFirmaSolNovpthisFirma() {
        return this.firmaDelFirmaSolNovpthisFirma;
    }
    
    public void setFirmaDelFirmaSolNovpthisFirma(FirmaOid firmaDelFirmaSolNovpthisFirma) {
        this.firmaDelFirmaSolNovpthisFirma = firmaDelFirmaSolNovpthisFirma;
    }

    @JsonIgnore
    public Firma getFirmaDelFirmaSolNovpthisFirmaInstance() {
        return this.firmaDelFirmaSolNovpthisFirmaInstance;
    }
    
    public void setFirmaDelFirmaSolNovpthisFirmaInstance(Firma firmaDelFirmaSolNovpthisFirma) {
        this.firmaDelFirmaSolNovpthisFirmaInstance = firmaDelFirmaSolNovpthisFirma;
        this.firmaDelFirmaSolNovpthisFirma = firmaDelFirmaSolNovpthisFirma != null ? firmaDelFirmaSolNovpthisFirma.getOid() : null;
    }

    private void checkFirmaDelFirmaSolNovpthisFirma() throws NotNullArgumentException {
        if(this.getFirmaDelFirmaSolNovpthisFirmaInstance() == null || this.getFirmaDelFirmaSolNovpthisFirmaInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_DELFIRMASOLNOV, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_DELFIRMASOLNOV_FIRMADELFIRMASOLNOVPTHISFIRMA, FirmaConstants.SERV_ALIAS_DELFIRMASOLNOV, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_DELFIRMASOLNOV_FIRMADELFIRMASOLNOVPTHISFIRMA);
        }
    }

    @JsonIgnore
    public SolicitudNovedadOid getFirmaDelFirmaSolNovpevcSolicitudNovedad() {
        return this.firmaDelFirmaSolNovpevcSolicitudNovedad;
    }
    
    public void setFirmaDelFirmaSolNovpevcSolicitudNovedad(SolicitudNovedadOid firmaDelFirmaSolNovpevcSolicitudNovedad) {
        this.firmaDelFirmaSolNovpevcSolicitudNovedad = firmaDelFirmaSolNovpevcSolicitudNovedad;
    }

    @JsonIgnore
    public SolicitudNovedad getFirmaDelFirmaSolNovpevcSolicitudNovedadInstance() {
        return this.firmaDelFirmaSolNovpevcSolicitudNovedadInstance;
    }
    
    public void setFirmaDelFirmaSolNovpevcSolicitudNovedadInstance(SolicitudNovedad firmaDelFirmaSolNovpevcSolicitudNovedad) {
        this.firmaDelFirmaSolNovpevcSolicitudNovedadInstance = firmaDelFirmaSolNovpevcSolicitudNovedad;
        this.firmaDelFirmaSolNovpevcSolicitudNovedad = firmaDelFirmaSolNovpevcSolicitudNovedad != null ? firmaDelFirmaSolNovpevcSolicitudNovedad.getOid() : null;
    }

    private void checkFirmaDelFirmaSolNovpevcSolicitudNovedad() throws NotNullArgumentException {
        if(this.getFirmaDelFirmaSolNovpevcSolicitudNovedadInstance() == null || this.getFirmaDelFirmaSolNovpevcSolicitudNovedadInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_DELFIRMASOLNOV, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_DELFIRMASOLNOV_FIRMADELFIRMASOLNOVPEVCSOLICITUDNOVEDAD, FirmaConstants.SERV_ALIAS_DELFIRMASOLNOV, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_DELFIRMASOLNOV_FIRMADELFIRMASOLNOVPEVCSOLICITUDNOVEDAD);
        }
    }
}
