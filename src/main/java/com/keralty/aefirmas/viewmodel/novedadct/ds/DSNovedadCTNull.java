package com.keralty.aefirmas.viewmodel.novedadct.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.persistence.NovedadCT;
public class DSNovedadCTNull {

    @JsonProperty("idnovedadct")
    private Long dSNovedadCTNullIdNovedadCT;

    public DSNovedadCTNull() {
    
    }

    public DSNovedadCTNull(NovedadCT instance) {
        if (instance != null) {
            this.dSNovedadCTNullIdNovedadCT = instance.getNovedadCTidNovedadCT();
        }
    }

    @JsonIgnore
    public Long getDSNovedadCTNullIdNovedadCT() {
        return this.dSNovedadCTNullIdNovedadCT;
    }
    
    public void setDSNovedadCTNullIdNovedadCT(Long dSNovedadCTNullIdNovedadCT) {
        this.dSNovedadCTNullIdNovedadCT = dSNovedadCTNullIdNovedadCT;
    }
}
