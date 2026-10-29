package com.keralty.aefirmas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aefirmas.exception.ModelException;
import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FamiliaNovedadConstants;
import com.keralty.aefirmas.global.UserFunctions;
import com.keralty.aefirmas.persistence.AbstractAppEntity;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.NovedadMD;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;
import com.keralty.aefirmas.persistence.oid.NovedadMDOid;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.oid.TipoIdentificacionOid;
import com.keralty.aefirmas.persistence.oid.UsuarioNovedadOid;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import com.keralty.aefirmas.persistence.TipoIdentificacion;
import com.keralty.aefirmas.persistence.UsuarioNovedad;
import com.keralty.aefirmas.repository.FamiliaNovedadJpaRepository;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aefirmas.viewmodel.FilterRequest;
import com.keralty.aefirmas.viewmodel.FilterVariableRequest;
import com.keralty.aefirmas.viewmodel.InstanceResponseViewModel;
import com.keralty.aefirmas.viewmodel.PopulationResponseViewModel;
import com.keralty.aefirmas.viewmodel.PreloadResponseViewModel;
import com.keralty.aefirmas.viewmodel.PreloadViewModel;
import com.keralty.aefirmas.viewmodel.QueryRequest;
import com.keralty.aefirmas.viewmodel.QueryResponse;
import com.keralty.aefirmas.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class FamiliaNovedadService extends AbstractService {

    FamiliaNovedadJpaRepository repository;

    FamiliaNovedadService familiaNovedadSrv;

    FirmaService firmaSrv;

    UsuarioNovedadService usuarioNovedadSrv;

    NovedadCTService novedadCTSrv;

    NovedadMDService novedadMDSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public FamiliaNovedadService(
            FamiliaNovedadService familiaNovedadSrv,
            FirmaService firmaSrv,
            UsuarioNovedadService usuarioNovedadSrv,
            NovedadCTService novedadCTSrv,
            NovedadMDService novedadMDSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            UserFunctions userFunctionsSrv,
            FamiliaNovedadJpaRepository repository) {
        this.familiaNovedadSrv = familiaNovedadSrv;
        this.firmaSrv = firmaSrv;
        this.usuarioNovedadSrv = usuarioNovedadSrv;
        this.novedadCTSrv = novedadCTSrv;
        this.novedadMDSrv = novedadMDSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, FamiliaNovedadConstants.CLASS_NAME);
    }

    private List<FamiliaNovedad> solveFamiliaNovedadTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, FamiliaNovedadConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveFamiliaNovedadTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, FamiliaNovedadConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class FamiliaNovedad
     *
     * @return Population of class FamiliaNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaNovedad> get() {
        List<FamiliaNovedad> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class FamiliaNovedad
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class FamiliaNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<FamiliaNovedad> instances = get(queryRequest);
        if(FamiliaNovedadConstants.DSDSFAMNOVTIPOTITULAR.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSFamNovTipoTitular())).collect(Collectors.toList());
        }
        if(FamiliaNovedadConstants.DSDSFAMNOVTIPOUSUARIO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSFamNovTipoUsuario())).collect(Collectors.toList());
        }
        if(FamiliaNovedadConstants.DSICFAMILIANOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICFamiliaNovedad())).collect(Collectors.toList());
        }
        if(FamiliaNovedadConstants.DSDSFAMILIANOVEDADNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSFamiliaNovedadNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class FamiliaNovedad that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class FamiliaNovedad matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaNovedad> get(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveFamiliaNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveFamiliaNovedadTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveFamiliaNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveFamiliaNovedadTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of FamiliaNovedad by ID
     *
     * @param familiaNovedadidFamiliaNovedad Identification field familiaNovedadidFamiliaNovedad
     *
     * @return Instance of FamiliaNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaNovedad getById(Long familiaNovedadidFamiliaNovedad) {
        return getByOID(new FamiliaNovedadOid(familiaNovedadidFamiliaNovedad));
    }
    
    /*
     * Returns an instance of FamiliaNovedad by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FamiliaNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaNovedad getByOID(FamiliaNovedadOid oid) {
        Optional<FamiliaNovedad> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of FamiliaNovedad by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FamiliaNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public FamiliaNovedad getByOIDWithHV(FamiliaNovedadOid oid) {
        Optional<FamiliaNovedad> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<FamiliaNovedad> getInstanceByOid(FamiliaNovedadOid oid) {
        return oid != null && oid.getFamiliaNovedadidFamiliaNovedad() != null
               ? repository.findById(oid.getFamiliaNovedadidFamiliaNovedad())
               : Optional.empty();
    }

    /*
     * Returns an instance of FamiliaNovedad by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of FamiliaNovedad
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaNovedad getByJsonOid(String jsonOid) {
        FamiliaNovedadOid oid = new FamiliaNovedadOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of FamiliaNovedad related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of FamiliaNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaNovedad> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveFamiliaNovedadTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of FamiliaNovedad related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of FamiliaNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaNovedad> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of FamiliaNovedad
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of FamiliaNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getFamiliaNovedadDynamic(FamiliaNovedadOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(FamiliaNovedadOid oid, String dsName) {
        FamiliaNovedad instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(FamiliaNovedad instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (FamiliaNovedadConstants.DSICFAMILIANOVEDAD.equals(dsName))
            return instance.buildICFamiliaNovedad();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(FamiliaNovedad instance, String dsName) {
        if (FamiliaNovedadConstants.DSDSFAMNOVTIPOTITULAR.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSFamNovTipoTitular());
        if (FamiliaNovedadConstants.DSDSFAMNOVTIPOUSUARIO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSFamNovTipoUsuario());
        if (FamiliaNovedadConstants.DSICFAMILIANOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICFamiliaNovedad());
        if (FamiliaNovedadConstants.DSDSFAMILIANOVEDADNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSFamiliaNovedadNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUFamNovMTipoTitular
     *
     * @param queryRequest Query request
     * @return List of viewmodels for FamiliaNovedad using displayset DSFamNovTipoTitular that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular> piuPIUFamNovMTipoTitular(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFamNovMTipoTitular
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFamNovMTipoTitular
     */
    public int getCount4PIUFamNovMTipoTitular(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUFamNovMTipoUsuario
     *
     * @param queryRequest Query request
     * @return List of viewmodels for FamiliaNovedad using displayset DSFamNovTipoTitular that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular> piuPIUFamNovMTipoUsuario(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFamNovMTipoUsuario
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFamNovMTipoUsuario
     */
    public int getCount4PIUFamNovMTipoUsuario(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUFamiliaNovedadNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for FamiliaNovedad using displayset DSFamiliaNovedadNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamiliaNovedadNull> piuPIUFamiliaNovedadNull(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamiliaNovedadNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFamiliaNovedadNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFamiliaNovedadNull
     */
    public int getCount4PIUFamiliaNovedadNull(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUFamNovMTipoTitularObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for FamiliaNovedad using displayset DSFamNovTipoTitular that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular> piuPIUFamNovMTipoTitularObs(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFamNovMTipoTitularObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFamNovMTipoTitularObs
     */
    public int getCount4PIUFamNovMTipoTitularObs(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUFamNovMTipoUsuarioObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for FamiliaNovedad using displayset DSFamNovTipoTitular that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular> piuPIUFamNovMTipoUsuarioObs(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFamNovMTipoUsuarioObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFamNovMTipoUsuarioObs
     */
    public int getCount4PIUFamNovMTipoUsuarioObs(QueryRequest queryRequest) {
        return solveFamiliaNovedadTypedQuery(queryRequest).size();
    }

    // Service: InsFirmaTitularFamiliaNovedad

    /*
     * Entry point for service InsFirmaTitularFamiliaNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insFirmaTitularFamiliaNovedad(
            com.keralty.aefirmas.viewmodel.familianovedad.siu.InsFirmaTitularFamiliaNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = familiaNovedadSrv.insFirmaTitularFamiliaNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsFirmaTitularFamiliaNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insFirmaTitularFamiliaNovedadTxn(
            com.keralty.aefirmas.viewmodel.familianovedad.siu.InsFirmaTitularFamiliaNovedadRequest request) throws ModelException {
        request.setFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad()));
        request.setFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance(firmaSrv.getByOIDWithHV(request.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirma()));
        return familiaNovedadSrv.insFirmaTitularFamiliaNovedadImplementation(request, true);
    }

    /*
     * Implements service InsFirmaTitularFamiliaNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insFirmaTitularFamiliaNovedadImplementation(
            com.keralty.aefirmas.viewmodel.familianovedad.siu.InsFirmaTitularFamiliaNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FamiliaNovedad familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad = request.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance();
        Firma familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma = request.getFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance();

        request.checkArguments();
        FamiliaNovedad instance = familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad;

        instance.add2Firma(familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest relatedRequest = new com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest();
            relatedRequest.setFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance(familiaNovedadInsFirmaTitularFamiliaNovedadpevcFirma);
            relatedRequest.setFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance(familiaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedad);
            firmaSrv.insFirmaTitularFamiliaNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICFamiliaNovedad());

        return response;
    }

    // Service: DelFirmaTitulaFamiliaNovedad

    /*
     * Entry point for service DelFirmaTitulaFamiliaNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delFirmaTitulaFamiliaNovedad(
            com.keralty.aefirmas.viewmodel.familianovedad.siu.DelFirmaTitulaFamiliaNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = familiaNovedadSrv.delFirmaTitulaFamiliaNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelFirmaTitulaFamiliaNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delFirmaTitulaFamiliaNovedadTxn(
            com.keralty.aefirmas.viewmodel.familianovedad.siu.DelFirmaTitulaFamiliaNovedadRequest request) throws ModelException {
        request.setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad()));
        request.setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance(firmaSrv.getByOIDWithHV(request.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma()));
        return familiaNovedadSrv.delFirmaTitulaFamiliaNovedadImplementation(request, true);
    }

    /*
     * Implements service DelFirmaTitulaFamiliaNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delFirmaTitulaFamiliaNovedadImplementation(
            com.keralty.aefirmas.viewmodel.familianovedad.siu.DelFirmaTitulaFamiliaNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FamiliaNovedad familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad = request.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance();
        Firma familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma = request.getFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance();

        request.checkArguments();
        FamiliaNovedad instance = familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad;

        instance.add2Firma(null);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaTitulaFamiliaNovedadRequest relatedRequest = new com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaTitulaFamiliaNovedadRequest();
            relatedRequest.setFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance(familiaNovedadDelFirmaTitulaFamiliaNovedadpevcFirma);
            relatedRequest.setFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance(familiaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedad);
            firmaSrv.delFirmaTitulaFamiliaNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICFamiliaNovedad());

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(FamiliaNovedadConstants.CLASS_NAME, FamiliaNovedadConstants.ATTR_FIELD_FAMILIANOVEDADIDFAMILIANOVEDAD, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildFamiliaNovedadRelatedInstance(relatedInstanceJsonOid);
        }
        if (FamiliaNovedadConstants.ROLE_NAME_USUARIONOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaNovedadRelatedInstance4UsuarioNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaNovedadConstants.ROLE_NAME_NOVEDADCT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaNovedadRelatedInstance4NovedadCT(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaNovedadConstants.ROLE_NAME_NOVEDADMD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaNovedadRelatedInstance4NovedadMD(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaNovedadConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaNovedadRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaNovedadConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaNovedadRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaNovedadConstants.ROLE_NAME_FIRMA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaNovedadRelatedInstance4Firma(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildFamiliaNovedadRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new FamiliaNovedad(mapper.readValue(relatedInstanceJsonOid, FamiliaNovedadOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaNovedadRelatedInstance4UsuarioNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new UsuarioNovedad(mapper.readValue(relatedInstanceJsonOid, UsuarioNovedadOid.class)): UsuarioNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaNovedadRelatedInstance4NovedadCT(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new NovedadCT(mapper.readValue(relatedInstanceJsonOid, NovedadCTOid.class)): NovedadCT.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaNovedadRelatedInstance4NovedadMD(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new NovedadMD(mapper.readValue(relatedInstanceJsonOid, NovedadMDOid.class)): NovedadMD.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaNovedadRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaNovedadRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaNovedadRelatedInstance4Firma(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Firma(mapper.readValue(relatedInstanceJsonOid, FirmaOid.class)): Firma.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            FamiliaNovedadOid oid = new FamiliaNovedadOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FamiliaNovedadConstants.ATTR_FIELD_FAMILIANOVEDADIDFAMILIANOVEDAD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFamiliaNovedadidFamiliaNovedad(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(FamiliaNovedadService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
