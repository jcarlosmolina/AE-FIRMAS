package com.keralty.aefirmas.viewmodel.solicitudnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.SolicitudNovedadConstants;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.SolicitudNovedad;

public class DelFirmaSolNovRequest {

    @JsonProperty("p_thissolicitudnovedad_oid")
    private SolicitudNovedadOid solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad;
    
    @JsonIgnore
    private SolicitudNovedad solicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance;

    @JsonProperty("p_evcfirma_oid")
    private FirmaOid solicitudNovedadDelFirmaSolNovpevcFirma;
    
    @JsonIgnore
    private Firma solicitudNovedadDelFirmaSolNovpevcFirmaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedad();
        checkSolicitudNovedadDelFirmaSolNovpevcFirma();
    }

    @JsonIgnore
    public SolicitudNovedadOid getSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedad() {
        return this.solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad;
    }
    
    public void setSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedad(SolicitudNovedadOid solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad) {
        this.solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad = solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad;
    }

    @JsonIgnore
    public SolicitudNovedad getSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance() {
        return this.solicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance;
    }
    
    public void setSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance(SolicitudNovedad solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad) {
        this.solicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance = solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad;
        this.solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad = solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad != null ? solicitudNovedadDelFirmaSolNovpthisSolicitudNovedad.getOid() : null;
    }

    private void checkSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedad() throws NotNullArgumentException {
        if(this.getSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance() == null || this.getSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudNovedadConstants.SERV_ID_DELFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ID, SolicitudNovedadConstants.ARG_ID_DELFIRMASOLNOV_SOLICITUDNOVEDADDELFIRMASOLNOVPTHISSOLICITUDNOVEDAD, SolicitudNovedadConstants.SERV_ALIAS_DELFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ALIAS, SolicitudNovedadConstants.ARG_ALIAS_DELFIRMASOLNOV_SOLICITUDNOVEDADDELFIRMASOLNOVPTHISSOLICITUDNOVEDAD);
        }
    }

    @JsonIgnore
    public FirmaOid getSolicitudNovedadDelFirmaSolNovpevcFirma() {
        return this.solicitudNovedadDelFirmaSolNovpevcFirma;
    }
    
    public void setSolicitudNovedadDelFirmaSolNovpevcFirma(FirmaOid solicitudNovedadDelFirmaSolNovpevcFirma) {
        this.solicitudNovedadDelFirmaSolNovpevcFirma = solicitudNovedadDelFirmaSolNovpevcFirma;
    }

    @JsonIgnore
    public Firma getSolicitudNovedadDelFirmaSolNovpevcFirmaInstance() {
        return this.solicitudNovedadDelFirmaSolNovpevcFirmaInstance;
    }
    
    public void setSolicitudNovedadDelFirmaSolNovpevcFirmaInstance(Firma solicitudNovedadDelFirmaSolNovpevcFirma) {
        this.solicitudNovedadDelFirmaSolNovpevcFirmaInstance = solicitudNovedadDelFirmaSolNovpevcFirma;
        this.solicitudNovedadDelFirmaSolNovpevcFirma = solicitudNovedadDelFirmaSolNovpevcFirma != null ? solicitudNovedadDelFirmaSolNovpevcFirma.getOid() : null;
    }

    private void checkSolicitudNovedadDelFirmaSolNovpevcFirma() throws NotNullArgumentException {
        if(this.getSolicitudNovedadDelFirmaSolNovpevcFirmaInstance() == null || this.getSolicitudNovedadDelFirmaSolNovpevcFirmaInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudNovedadConstants.SERV_ID_DELFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ID, SolicitudNovedadConstants.ARG_ID_DELFIRMASOLNOV_SOLICITUDNOVEDADDELFIRMASOLNOVPEVCFIRMA, SolicitudNovedadConstants.SERV_ALIAS_DELFIRMASOLNOV, SolicitudNovedadConstants.CLASS_ALIAS, SolicitudNovedadConstants.ARG_ALIAS_DELFIRMASOLNOV_SOLICITUDNOVEDADDELFIRMASOLNOVPEVCFIRMA);
        }
    }
}
