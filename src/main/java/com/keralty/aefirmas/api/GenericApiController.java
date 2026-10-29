package com.keralty.aefirmas.api;

import com.keralty.aefirmas.service.GenericService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/api/GenericApiController")
@RequestScoped
public class GenericApiController {
	
    GenericService genericService;

    @Inject
	public GenericApiController(GenericService genericService) {
        this.genericService = genericService;
    }

    @Path("/isFuncionarioJpaRepositoryAvailable")
    public boolean isFuncionarioJpaRepositoryAvailable() {
        return genericService.getFuncionarioJpaRepository() != null;
    }
    @Path("/isPersonaJpaRepositoryAvailable")
    public boolean isPersonaJpaRepositoryAvailable() {
        return genericService.getPersonaJpaRepository() != null;
    }
    @Path("/isSolicitudJpaRepositoryAvailable")
    public boolean isSolicitudJpaRepositoryAvailable() {
        return genericService.getSolicitudJpaRepository() != null;
    }
    @Path("/isPersonaCJpaRepositoryAvailable")
    public boolean isPersonaCJpaRepositoryAvailable() {
        return genericService.getPersonaCJpaRepository() != null;
    }
    @Path("/isDireccionCJpaRepositoryAvailable")
    public boolean isDireccionCJpaRepositoryAvailable() {
        return genericService.getDireccionCJpaRepository() != null;
    }
    @Path("/isUsuarioJpaRepositoryAvailable")
    public boolean isUsuarioJpaRepositoryAvailable() {
        return genericService.getUsuarioJpaRepository() != null;
    }
    @Path("/isAnexoMatJpaRepositoryAvailable")
    public boolean isAnexoMatJpaRepositoryAvailable() {
        return genericService.getAnexoMatJpaRepository() != null;
    }
    @Path("/isAnexoNeoJpaRepositoryAvailable")
    public boolean isAnexoNeoJpaRepositoryAvailable() {
        return genericService.getAnexoNeoJpaRepository() != null;
    }
    @Path("/isContratoCJpaRepositoryAvailable")
    public boolean isContratoCJpaRepositoryAvailable() {
        return genericService.getContratoCJpaRepository() != null;
    }
    @Path("/isBeneficiarioCJpaRepositoryAvailable")
    public boolean isBeneficiarioCJpaRepositoryAvailable() {
        return genericService.getBeneficiarioCJpaRepository() != null;
    }
    @Path("/isNovedadJpaRepositoryAvailable")
    public boolean isNovedadJpaRepositoryAvailable() {
        return genericService.getNovedadJpaRepository() != null;
    }
    @Path("/isUsuarioNovedadJpaRepositoryAvailable")
    public boolean isUsuarioNovedadJpaRepositoryAvailable() {
        return genericService.getUsuarioNovedadJpaRepository() != null;
    }
    @Path("/isConfiguracionANSJpaRepositoryAvailable")
    public boolean isConfiguracionANSJpaRepositoryAvailable() {
        return genericService.getConfiguracionANSJpaRepository() != null;
    }
    @Path("/isReportesDominioJpaRepositoryAvailable")
    public boolean isReportesDominioJpaRepositoryAvailable() {
        return genericService.getReportesDominioJpaRepository() != null;
    }
}
