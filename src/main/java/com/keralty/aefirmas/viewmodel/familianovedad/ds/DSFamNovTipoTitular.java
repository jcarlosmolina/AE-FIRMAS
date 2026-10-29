package com.keralty.aefirmas.viewmodel.familianovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
public class DSFamNovTipoTitular {

    @JsonProperty("numfamilia")
    private Long dSFamNovTipoTitularNumFamilia;

    @JsonProperty("famactiva")
    private Boolean dSFamNovTipoTitularFamActiva;

    @JsonProperty("titdrvidentificacion")
    private String dSFamNovTipoTitularTitDrvIdentificacion;

    @JsonProperty("titdrvnombrecompleto")
    private String dSFamNovTipoTitularTitDrvNombreCompleto;

    @JsonProperty("titdrvnombreanterior")
    private String dSFamNovTipoTitularTitDrvNombreAnterior;

    @JsonProperty("drvtienenuevadir")
    private Boolean dSFamNovTipoTitularDrvTieneNuevaDir;

    @JsonProperty("drvtienefirmatit")
    private Boolean dSFamNovTipoTitularDrvTieneFirmaTit;

    @JsonProperty("drvtienefirmanuevotit")
    private Boolean dSFamNovTipoTitularDrvTieneFirmaNuevoTit;

    public DSFamNovTipoTitular() {
    
    }

    public DSFamNovTipoTitular(FamiliaNovedad instance) {
        if (instance != null) {
            try {
                this.dSFamNovTipoTitularNumFamilia = instance.getFamiliaNovedadNumFamilia();
                this.dSFamNovTipoTitularFamActiva = instance.getFamiliaNovedadFamActiva();
                this.dSFamNovTipoTitularTitDrvIdentificacion = instance.getFamiliaNovedadTitDrvIdentificacion();
                this.dSFamNovTipoTitularTitDrvNombreCompleto = instance.getFamiliaNovedadTitDrvNombreCompleto();
                this.dSFamNovTipoTitularTitDrvNombreAnterior = instance.getFamiliaNovedadTitDrvNombreAnterior();
                this.dSFamNovTipoTitularDrvTieneNuevaDir = instance.getFamiliaNovedadDrvTieneNuevaDir();
                this.dSFamNovTipoTitularDrvTieneFirmaTit = instance.getFamiliaNovedadDrvTieneFirmaTit();
                this.dSFamNovTipoTitularDrvTieneFirmaNuevoTit = instance.getFamiliaNovedadDrvTieneFirmaNuevoTit();
            } catch (Exception e) {
                org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            }
        }
    }

    @JsonIgnore
    public Long getDSFamNovTipoTitularNumFamilia() {
        return this.dSFamNovTipoTitularNumFamilia;
    }
    
    public void setDSFamNovTipoTitularNumFamilia(Long dSFamNovTipoTitularNumFamilia) {
        this.dSFamNovTipoTitularNumFamilia = dSFamNovTipoTitularNumFamilia;
    }

    @JsonIgnore
    public Boolean getDSFamNovTipoTitularFamActiva() {
        return this.dSFamNovTipoTitularFamActiva;
    }
    
    public void setDSFamNovTipoTitularFamActiva(Boolean dSFamNovTipoTitularFamActiva) {
        this.dSFamNovTipoTitularFamActiva = dSFamNovTipoTitularFamActiva;
    }

    @JsonIgnore
    public String getDSFamNovTipoTitularTitDrvIdentificacion() {
        return this.dSFamNovTipoTitularTitDrvIdentificacion;
    }
    
    public void setDSFamNovTipoTitularTitDrvIdentificacion(String dSFamNovTipoTitularTitDrvIdentificacion) {
        this.dSFamNovTipoTitularTitDrvIdentificacion = dSFamNovTipoTitularTitDrvIdentificacion;
    }

    @JsonIgnore
    public String getDSFamNovTipoTitularTitDrvNombreCompleto() {
        return this.dSFamNovTipoTitularTitDrvNombreCompleto;
    }
    
    public void setDSFamNovTipoTitularTitDrvNombreCompleto(String dSFamNovTipoTitularTitDrvNombreCompleto) {
        this.dSFamNovTipoTitularTitDrvNombreCompleto = dSFamNovTipoTitularTitDrvNombreCompleto;
    }

    @JsonIgnore
    public String getDSFamNovTipoTitularTitDrvNombreAnterior() {
        return this.dSFamNovTipoTitularTitDrvNombreAnterior;
    }
    
    public void setDSFamNovTipoTitularTitDrvNombreAnterior(String dSFamNovTipoTitularTitDrvNombreAnterior) {
        this.dSFamNovTipoTitularTitDrvNombreAnterior = dSFamNovTipoTitularTitDrvNombreAnterior;
    }

    @JsonIgnore
    public Boolean getDSFamNovTipoTitularDrvTieneNuevaDir() {
        return this.dSFamNovTipoTitularDrvTieneNuevaDir;
    }
    
    public void setDSFamNovTipoTitularDrvTieneNuevaDir(Boolean dSFamNovTipoTitularDrvTieneNuevaDir) {
        this.dSFamNovTipoTitularDrvTieneNuevaDir = dSFamNovTipoTitularDrvTieneNuevaDir;
    }

    @JsonIgnore
    public Boolean getDSFamNovTipoTitularDrvTieneFirmaTit() {
        return this.dSFamNovTipoTitularDrvTieneFirmaTit;
    }
    
    public void setDSFamNovTipoTitularDrvTieneFirmaTit(Boolean dSFamNovTipoTitularDrvTieneFirmaTit) {
        this.dSFamNovTipoTitularDrvTieneFirmaTit = dSFamNovTipoTitularDrvTieneFirmaTit;
    }

    @JsonIgnore
    public Boolean getDSFamNovTipoTitularDrvTieneFirmaNuevoTit() {
        return this.dSFamNovTipoTitularDrvTieneFirmaNuevoTit;
    }
    
    public void setDSFamNovTipoTitularDrvTieneFirmaNuevoTit(Boolean dSFamNovTipoTitularDrvTieneFirmaNuevoTit) {
        this.dSFamNovTipoTitularDrvTieneFirmaNuevoTit = dSFamNovTipoTitularDrvTieneFirmaNuevoTit;
    }
}
