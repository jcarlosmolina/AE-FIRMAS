package com.keralty.aefirmas.viewmodel.familianovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
public class ICFamiliaNovedad {

    @JsonProperty("numfamilia")
    private Long iCFamiliaNovedadNumFamilia;

    public ICFamiliaNovedad() {
    
    }

    public ICFamiliaNovedad(FamiliaNovedad instance) {
        if (instance != null) {
            this.iCFamiliaNovedadNumFamilia = instance.getFamiliaNovedadNumFamilia();
        }
    }

    @JsonIgnore
    public Long getICFamiliaNovedadNumFamilia() {
        return this.iCFamiliaNovedadNumFamilia;
    }
    
    public void setICFamiliaNovedadNumFamilia(Long iCFamiliaNovedadNumFamilia) {
        this.iCFamiliaNovedadNumFamilia = iCFamiliaNovedadNumFamilia;
    }
}
