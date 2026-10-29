package com.keralty.aefirmas.viewmodel.familianovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
public class DSFamiliaNovedadNull {

    @JsonProperty("idfamilianovedad")
    private Long dSFamiliaNovedadNullIdFamiliaNovedad;

    public DSFamiliaNovedadNull() {
    
    }

    public DSFamiliaNovedadNull(FamiliaNovedad instance) {
        if (instance != null) {
            this.dSFamiliaNovedadNullIdFamiliaNovedad = instance.getFamiliaNovedadidFamiliaNovedad();
        }
    }

    @JsonIgnore
    public Long getDSFamiliaNovedadNullIdFamiliaNovedad() {
        return this.dSFamiliaNovedadNullIdFamiliaNovedad;
    }
    
    public void setDSFamiliaNovedadNullIdFamiliaNovedad(Long dSFamiliaNovedadNullIdFamiliaNovedad) {
        this.dSFamiliaNovedadNullIdFamiliaNovedad = dSFamiliaNovedadNullIdFamiliaNovedad;
    }
}
