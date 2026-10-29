package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.SolicitudNovedad;

public class TCREARSOLNOVRequest {

    @JsonProperty("pt_p_agrsolicitudnovedad_oid")
    private SolicitudNovedadOid firmaTCREARSOLNOVptpagrSolicitudNovedad;
    
    @JsonIgnore
    private SolicitudNovedad firmaTCREARSOLNOVptpagrSolicitudNovedadInstance;

    @JsonProperty("pt_p_atrimagenfirma")
    private byte[] firmaTCREARSOLNOVptpatrImagenFirma;

    @JsonProperty("pt_p_atrlatitud")
    private String firmaTCREARSOLNOVptpatrLatitud;

    @JsonProperty("pt_p_atrlongitud")
    private String firmaTCREARSOLNOVptpatrLongitud;

    @JsonIgnore
    public SolicitudNovedadOid getFirmaTCREARSOLNOVptpagrSolicitudNovedad() {
        return this.firmaTCREARSOLNOVptpagrSolicitudNovedad;
    }
    
    public void setFirmaTCREARSOLNOVptpagrSolicitudNovedad(SolicitudNovedadOid firmaTCREARSOLNOVptpagrSolicitudNovedad) {
        this.firmaTCREARSOLNOVptpagrSolicitudNovedad = firmaTCREARSOLNOVptpagrSolicitudNovedad;
    }

    @JsonIgnore
    public SolicitudNovedad getFirmaTCREARSOLNOVptpagrSolicitudNovedadInstance() {
        return this.firmaTCREARSOLNOVptpagrSolicitudNovedadInstance;
    }
    
    public void setFirmaTCREARSOLNOVptpagrSolicitudNovedadInstance(SolicitudNovedad firmaTCREARSOLNOVptpagrSolicitudNovedad) {
        this.firmaTCREARSOLNOVptpagrSolicitudNovedadInstance = firmaTCREARSOLNOVptpagrSolicitudNovedad;
        this.firmaTCREARSOLNOVptpagrSolicitudNovedad = firmaTCREARSOLNOVptpagrSolicitudNovedad != null ? firmaTCREARSOLNOVptpagrSolicitudNovedad.getOid() : null;
    }

    @JsonIgnore
    public byte[] getFirmaTCREARSOLNOVptpatrImagenFirma() {
        return this.firmaTCREARSOLNOVptpatrImagenFirma;
    }
    
    public void setFirmaTCREARSOLNOVptpatrImagenFirma(byte[] firmaTCREARSOLNOVptpatrImagenFirma) {
        this.firmaTCREARSOLNOVptpatrImagenFirma = firmaTCREARSOLNOVptpatrImagenFirma;
    }

    @JsonIgnore
    public String getFirmaTCREARSOLNOVptpatrLatitud() {
        return this.firmaTCREARSOLNOVptpatrLatitud;
    }
    
    public void setFirmaTCREARSOLNOVptpatrLatitud(String firmaTCREARSOLNOVptpatrLatitud) {
        this.firmaTCREARSOLNOVptpatrLatitud = firmaTCREARSOLNOVptpatrLatitud;
    }

    @JsonIgnore
    public String getFirmaTCREARSOLNOVptpatrLongitud() {
        return this.firmaTCREARSOLNOVptpatrLongitud;
    }
    
    public void setFirmaTCREARSOLNOVptpatrLongitud(String firmaTCREARSOLNOVptpatrLongitud) {
        this.firmaTCREARSOLNOVptpatrLongitud = firmaTCREARSOLNOVptpatrLongitud;
    }
}
