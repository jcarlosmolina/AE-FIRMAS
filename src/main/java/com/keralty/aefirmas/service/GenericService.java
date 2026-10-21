package com.keralty.aefirmas.service;

import com.keralty.aefirmas.repository.AnexoMatJpaRepository;
import com.keralty.aefirmas.repository.AnexoNeoJpaRepository;
import com.keralty.aefirmas.repository.BeneficiarioCJpaRepository;
import com.keralty.aefirmas.repository.ConfiguracionANSJpaRepository;
import com.keralty.aefirmas.repository.ContratoCJpaRepository;
import com.keralty.aefirmas.repository.DireccionCJpaRepository;
import com.keralty.aefirmas.repository.FuncionarioJpaRepository;
import com.keralty.aefirmas.repository.NovedadJpaRepository;
import com.keralty.aefirmas.repository.PersonaCJpaRepository;
import com.keralty.aefirmas.repository.PersonaJpaRepository;
import com.keralty.aefirmas.repository.ReportesDominioJpaRepository;
import com.keralty.aefirmas.repository.SolicitudJpaRepository;
import com.keralty.aefirmas.repository.UsuarioJpaRepository;
import com.keralty.aefirmas.repository.UsuarioNovedadJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class GenericService {

    private FuncionarioJpaRepository funcionarioJpaRepository;

    private PersonaJpaRepository personaJpaRepository;

    private SolicitudJpaRepository solicitudJpaRepository;

    private PersonaCJpaRepository personaCJpaRepository;

    private DireccionCJpaRepository direccionCJpaRepository;

    private UsuarioJpaRepository usuarioJpaRepository;

    private AnexoMatJpaRepository anexoMatJpaRepository;

    private AnexoNeoJpaRepository anexoNeoJpaRepository;

    private ContratoCJpaRepository contratoCJpaRepository;

    private BeneficiarioCJpaRepository beneficiarioCJpaRepository;

    private NovedadJpaRepository novedadJpaRepository;

    private UsuarioNovedadJpaRepository usuarioNovedadJpaRepository;

    private ConfiguracionANSJpaRepository configuracionANSJpaRepository;

    private ReportesDominioJpaRepository reportesDominioJpaRepository;
	
	@Inject
	public GenericService(
            FuncionarioJpaRepository funcionarioJpaRepository,
            PersonaJpaRepository personaJpaRepository,
            SolicitudJpaRepository solicitudJpaRepository,
            PersonaCJpaRepository personaCJpaRepository,
            DireccionCJpaRepository direccionCJpaRepository,
            UsuarioJpaRepository usuarioJpaRepository,
            AnexoMatJpaRepository anexoMatJpaRepository,
            AnexoNeoJpaRepository anexoNeoJpaRepository,
            ContratoCJpaRepository contratoCJpaRepository,
            BeneficiarioCJpaRepository beneficiarioCJpaRepository,
            NovedadJpaRepository novedadJpaRepository,
            UsuarioNovedadJpaRepository usuarioNovedadJpaRepository,
            ConfiguracionANSJpaRepository configuracionANSJpaRepository,
            ReportesDominioJpaRepository reportesDominioJpaRepository
			) {
        this.funcionarioJpaRepository = funcionarioJpaRepository;
        this.personaJpaRepository = personaJpaRepository;
        this.solicitudJpaRepository = solicitudJpaRepository;
        this.personaCJpaRepository = personaCJpaRepository;
        this.direccionCJpaRepository = direccionCJpaRepository;
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.anexoMatJpaRepository = anexoMatJpaRepository;
        this.anexoNeoJpaRepository = anexoNeoJpaRepository;
        this.contratoCJpaRepository = contratoCJpaRepository;
        this.beneficiarioCJpaRepository = beneficiarioCJpaRepository;
        this.novedadJpaRepository = novedadJpaRepository;
        this.usuarioNovedadJpaRepository = usuarioNovedadJpaRepository;
        this.configuracionANSJpaRepository = configuracionANSJpaRepository;
        this.reportesDominioJpaRepository = reportesDominioJpaRepository;
	}

	public FuncionarioJpaRepository getFuncionarioJpaRepository() {
		return this.funcionarioJpaRepository;
	}
	public PersonaJpaRepository getPersonaJpaRepository() {
		return this.personaJpaRepository;
	}
	public SolicitudJpaRepository getSolicitudJpaRepository() {
		return this.solicitudJpaRepository;
	}
	public PersonaCJpaRepository getPersonaCJpaRepository() {
		return this.personaCJpaRepository;
	}
	public DireccionCJpaRepository getDireccionCJpaRepository() {
		return this.direccionCJpaRepository;
	}
	public UsuarioJpaRepository getUsuarioJpaRepository() {
		return this.usuarioJpaRepository;
	}
	public AnexoMatJpaRepository getAnexoMatJpaRepository() {
		return this.anexoMatJpaRepository;
	}
	public AnexoNeoJpaRepository getAnexoNeoJpaRepository() {
		return this.anexoNeoJpaRepository;
	}
	public ContratoCJpaRepository getContratoCJpaRepository() {
		return this.contratoCJpaRepository;
	}
	public BeneficiarioCJpaRepository getBeneficiarioCJpaRepository() {
		return this.beneficiarioCJpaRepository;
	}
	public NovedadJpaRepository getNovedadJpaRepository() {
		return this.novedadJpaRepository;
	}
	public UsuarioNovedadJpaRepository getUsuarioNovedadJpaRepository() {
		return this.usuarioNovedadJpaRepository;
	}
	public ConfiguracionANSJpaRepository getConfiguracionANSJpaRepository() {
		return this.configuracionANSJpaRepository;
	}
	public ReportesDominioJpaRepository getReportesDominioJpaRepository() {
		return this.reportesDominioJpaRepository;
	}
}
