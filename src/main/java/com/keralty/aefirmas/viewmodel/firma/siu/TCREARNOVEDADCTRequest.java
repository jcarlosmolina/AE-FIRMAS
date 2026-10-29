package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.exception.NotNullArgumentException;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;

public class TCREARNOVEDADCTRequest {

    @JsonProperty("pt_p_atrimagenfirma")
    private byte[] firmaTCREARNOVEDADCTptpatrImagenFirma;

    @JsonProperty("pt_p_atrlatitud")
    private String firmaTCREARNOVEDADCTptpatrLatitud;

    @JsonProperty("pt_p_atrlongitud")
    private String firmaTCREARNOVEDADCTptpatrLongitud;

    @JsonProperty("ptnovedadct_oid")
    private NovedadCTOid firmaTCREARNOVEDADCTptNovedadCT;
    
    @JsonIgnore
    private NovedadCT firmaTCREARNOVEDADCTptNovedadCTInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFirmaTCREARNOVEDADCTptNovedadCT();
    }

    @JsonIgnore
    public byte[] getFirmaTCREARNOVEDADCTptpatrImagenFirma() {
        return this.firmaTCREARNOVEDADCTptpatrImagenFirma;
    }
    
    public void setFirmaTCREARNOVEDADCTptpatrImagenFirma(byte[] firmaTCREARNOVEDADCTptpatrImagenFirma) {
        this.firmaTCREARNOVEDADCTptpatrImagenFirma = firmaTCREARNOVEDADCTptpatrImagenFirma;
    }

    @JsonIgnore
    public String getFirmaTCREARNOVEDADCTptpatrLatitud() {
        return this.firmaTCREARNOVEDADCTptpatrLatitud;
    }
    
    public void setFirmaTCREARNOVEDADCTptpatrLatitud(String firmaTCREARNOVEDADCTptpatrLatitud) {
        this.firmaTCREARNOVEDADCTptpatrLatitud = firmaTCREARNOVEDADCTptpatrLatitud;
    }

    @JsonIgnore
    public String getFirmaTCREARNOVEDADCTptpatrLongitud() {
        return this.firmaTCREARNOVEDADCTptpatrLongitud;
    }
    
    public void setFirmaTCREARNOVEDADCTptpatrLongitud(String firmaTCREARNOVEDADCTptpatrLongitud) {
        this.firmaTCREARNOVEDADCTptpatrLongitud = firmaTCREARNOVEDADCTptpatrLongitud;
    }

    @JsonIgnore
    public NovedadCTOid getFirmaTCREARNOVEDADCTptNovedadCT() {
        return this.firmaTCREARNOVEDADCTptNovedadCT;
    }
    
    public void setFirmaTCREARNOVEDADCTptNovedadCT(NovedadCTOid firmaTCREARNOVEDADCTptNovedadCT) {
        this.firmaTCREARNOVEDADCTptNovedadCT = firmaTCREARNOVEDADCTptNovedadCT;
    }

    @JsonIgnore
    public NovedadCT getFirmaTCREARNOVEDADCTptNovedadCTInstance() {
        return this.firmaTCREARNOVEDADCTptNovedadCTInstance;
    }
    
    public void setFirmaTCREARNOVEDADCTptNovedadCTInstance(NovedadCT firmaTCREARNOVEDADCTptNovedadCT) {
        this.firmaTCREARNOVEDADCTptNovedadCTInstance = firmaTCREARNOVEDADCTptNovedadCT;
        this.firmaTCREARNOVEDADCTptNovedadCT = firmaTCREARNOVEDADCTptNovedadCT != null ? firmaTCREARNOVEDADCTptNovedadCT.getOid() : null;
    }

    private void checkFirmaTCREARNOVEDADCTptNovedadCT() throws NotNullArgumentException {
        if(this.getFirmaTCREARNOVEDADCTptNovedadCTInstance() == null || this.getFirmaTCREARNOVEDADCTptNovedadCTInstance().isNull()) {
            throw new NotNullArgumentException(FirmaConstants.SERV_ID_TCREARNOVEDADCT, FirmaConstants.CLASS_ID, FirmaConstants.ARG_ID_TCREARNOVEDADCT_FIRMATCREARNOVEDADCTPTNOVEDADCT, FirmaConstants.SERV_ALIAS_TCREARNOVEDADCT, FirmaConstants.CLASS_ALIAS, FirmaConstants.ARG_ALIAS_TCREARNOVEDADCT_FIRMATCREARNOVEDADCTPTNOVEDADCT);
        }
    }
}
