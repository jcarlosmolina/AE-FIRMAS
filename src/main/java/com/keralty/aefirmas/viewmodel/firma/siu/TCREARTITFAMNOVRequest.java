package com.keralty.aefirmas.viewmodel.firma.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;

public class TCREARTITFAMNOVRequest {

    @JsonProperty("pt_p_atrimagenfirma")
    private byte[] firmaTCREARTITFAMNOVptpatrImagenFirma;

    @JsonProperty("pt_p_atrlatitud")
    private String firmaTCREARTITFAMNOVptpatrLatitud;

    @JsonProperty("pt_p_atrlongitud")
    private String firmaTCREARTITFAMNOVptpatrLongitud;

    @JsonProperty("ptfamilianovedad_oid")
    private FamiliaNovedadOid firmaTCREARTITFAMNOVptFamiliaNovedad;
    
    @JsonIgnore
    private FamiliaNovedad firmaTCREARTITFAMNOVptFamiliaNovedadInstance;

    @JsonIgnore
    public byte[] getFirmaTCREARTITFAMNOVptpatrImagenFirma() {
        return this.firmaTCREARTITFAMNOVptpatrImagenFirma;
    }
    
    public void setFirmaTCREARTITFAMNOVptpatrImagenFirma(byte[] firmaTCREARTITFAMNOVptpatrImagenFirma) {
        this.firmaTCREARTITFAMNOVptpatrImagenFirma = firmaTCREARTITFAMNOVptpatrImagenFirma;
    }

    @JsonIgnore
    public String getFirmaTCREARTITFAMNOVptpatrLatitud() {
        return this.firmaTCREARTITFAMNOVptpatrLatitud;
    }
    
    public void setFirmaTCREARTITFAMNOVptpatrLatitud(String firmaTCREARTITFAMNOVptpatrLatitud) {
        this.firmaTCREARTITFAMNOVptpatrLatitud = firmaTCREARTITFAMNOVptpatrLatitud;
    }

    @JsonIgnore
    public String getFirmaTCREARTITFAMNOVptpatrLongitud() {
        return this.firmaTCREARTITFAMNOVptpatrLongitud;
    }
    
    public void setFirmaTCREARTITFAMNOVptpatrLongitud(String firmaTCREARTITFAMNOVptpatrLongitud) {
        this.firmaTCREARTITFAMNOVptpatrLongitud = firmaTCREARTITFAMNOVptpatrLongitud;
    }

    @JsonIgnore
    public FamiliaNovedadOid getFirmaTCREARTITFAMNOVptFamiliaNovedad() {
        return this.firmaTCREARTITFAMNOVptFamiliaNovedad;
    }
    
    public void setFirmaTCREARTITFAMNOVptFamiliaNovedad(FamiliaNovedadOid firmaTCREARTITFAMNOVptFamiliaNovedad) {
        this.firmaTCREARTITFAMNOVptFamiliaNovedad = firmaTCREARTITFAMNOVptFamiliaNovedad;
    }

    @JsonIgnore
    public FamiliaNovedad getFirmaTCREARTITFAMNOVptFamiliaNovedadInstance() {
        return this.firmaTCREARTITFAMNOVptFamiliaNovedadInstance;
    }
    
    public void setFirmaTCREARTITFAMNOVptFamiliaNovedadInstance(FamiliaNovedad firmaTCREARTITFAMNOVptFamiliaNovedad) {
        this.firmaTCREARTITFAMNOVptFamiliaNovedadInstance = firmaTCREARTITFAMNOVptFamiliaNovedad;
        this.firmaTCREARTITFAMNOVptFamiliaNovedad = firmaTCREARTITFAMNOVptFamiliaNovedad != null ? firmaTCREARTITFAMNOVptFamiliaNovedad.getOid() : null;
    }
}
