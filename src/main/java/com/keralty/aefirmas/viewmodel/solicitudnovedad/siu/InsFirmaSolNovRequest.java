package com.keralty.aefirmas.viewmodel.solicitudnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.SolicitudNovedadConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.SolicitudNovedad;

public class InsFirmaSolNovRequest {

    @JsonProperty("p_thissolicitudnovedad_oid")
    private SolicitudNovedadOid solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad;
    
    @JsonIgnore
    private SolicitudNovedad solicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance;

    @JsonProperty("p_evcfirma_oid")
    private FirmaOid solicitudNovedadInsFirmaSolNovpevcFirma;
    
    @JsonIgnore
    private Firma solicitudNovedadInsFirmaSolNovpevcFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedad();
        checkSolicitudNovedadInsFirmaSolNovpevcFirma();
    }

    @JsonIgnore
    public SolicitudNovedadOid getSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedad() {
        return this.solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad;
    }
    
    public void setSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedad(SolicitudNovedadOid solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad) {
        this.solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad = solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad;
    }

    @JsonIgnore
    public SolicitudNovedad getSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance() {
        return this.solicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance;
    }
    
    public void setSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance(SolicitudNovedad solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad) {
        this.solicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance = solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad;
        this.solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad = solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad != null ? solicitudNovedadInsFirmaSolNovpthisSolicitudNovedad.getOid() : null;
    }

    private void checkSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedad() throws NotNullArgumentException {
        if(this.getSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance() == null || this.getSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudNovedadConstants.SERV_ID_INSFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ID, SolicitudNovedadConstants.ARG_ID_INSFIRMASOLNOV_SOLICITUDNOVEDADINSFIRMASOLNOVPTHISSOLICITUDNOVEDAD, SolicitudNovedadConstants.SERV_ALIAS_INSFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ALIAS, SolicitudNovedadConstants.ARG_ALIAS_INSFIRMASOLNOV_SOLICITUDNOVEDADINSFIRMASOLNOVPTHISSOLICITUDNOVEDAD);
        }
    }

    @JsonIgnore
    public FirmaOid getSolicitudNovedadInsFirmaSolNovpevcFirma() {
        return this.solicitudNovedadInsFirmaSolNovpevcFirma;
    }
    
    public void setSolicitudNovedadInsFirmaSolNovpevcFirma(FirmaOid solicitudNovedadInsFirmaSolNovpevcFirma) {
        this.solicitudNovedadInsFirmaSolNovpevcFirma = solicitudNovedadInsFirmaSolNovpevcFirma;
    }

    @JsonIgnore
    public Firma getSolicitudNovedadInsFirmaSolNovpevcFirmaInstance() {
        return this.solicitudNovedadInsFirmaSolNovpevcFirmaInstance;
    }
    
    public void setSolicitudNovedadInsFirmaSolNovpevcFirmaInstance(Firma solicitudNovedadInsFirmaSolNovpevcFirma) {
        this.solicitudNovedadInsFirmaSolNovpevcFirmaInstance = solicitudNovedadInsFirmaSolNovpevcFirma;
        this.solicitudNovedadInsFirmaSolNovpevcFirma = solicitudNovedadInsFirmaSolNovpevcFirma != null ? solicitudNovedadInsFirmaSolNovpevcFirma.getOid() : null;
    }

    private void checkSolicitudNovedadInsFirmaSolNovpevcFirma() throws NotNullArgumentException {
        if(this.getSolicitudNovedadInsFirmaSolNovpevcFirmaInstance() == null || this.getSolicitudNovedadInsFirmaSolNovpevcFirmaInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudNovedadConstants.SERV_ID_INSFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ID, SolicitudNovedadConstants.ARG_ID_INSFIRMASOLNOV_SOLICITUDNOVEDADINSFIRMASOLNOVPEVCFIRMA, SolicitudNovedadConstants.SERV_ALIAS_INSFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ALIAS, SolicitudNovedadConstants.ARG_ALIAS_INSFIRMASOLNOV_SOLICITUDNOVEDADINSFIRMASOLNOVPEVCFIRMA);
        }
    }
}
