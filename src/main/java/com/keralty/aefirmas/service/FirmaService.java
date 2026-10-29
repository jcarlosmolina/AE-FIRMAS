package com.keralty.aefirmas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aefirmas.exception.MinCardinalityException;
import com.keralty.aefirmas.exception.ModelException;
import com.keralty.aefirmas.exception.PreconditionException;
import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FirmaConstants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.global.RegionalConstants;
import com.keralty.aefirmas.global.RespRegionalConstants;
import com.keralty.aefirmas.global.SucursalConstants;
import com.keralty.aefirmas.persistence.AbstractAppEntity;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;
import com.keralty.aefirmas.persistence.oid.RegionalOid;
import com.keralty.aefirmas.persistence.oid.RespRegionalOid;
import com.keralty.aefirmas.persistence.oid.SolicitudNovedadOid;
import com.keralty.aefirmas.persistence.oid.SolicitudOid;
import com.keralty.aefirmas.persistence.Regional;
import com.keralty.aefirmas.persistence.RespRegional;
import com.keralty.aefirmas.persistence.Solicitud;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import com.keralty.aefirmas.repository.FirmaJpaRepository;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereConjunction;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandSubQuery;
import com.keralty.aefirmas.utils.Utilities;
import com.keralty.aefirmas.viewmodel.FilterRequest;
import com.keralty.aefirmas.viewmodel.FilterVariableRequest;
import com.keralty.aefirmas.viewmodel.InstanceResponseViewModel;
import com.keralty.aefirmas.viewmodel.PopulationResponseViewModel;
import com.keralty.aefirmas.viewmodel.PreloadResponseViewModel;
import com.keralty.aefirmas.viewmodel.QueryRequest;
import com.keralty.aefirmas.viewmodel.QueryResponse;
import com.keralty.aefirmas.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class FirmaService extends AbstractService {

    FirmaJpaRepository repository;

    FirmaService firmaSrv;

    SolicitudService solicitudSrv;

    FamiliaNovedadService familiaNovedadSrv;

    NovedadCTService novedadCTSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    RegionalService regionalSrv;

    @Inject
    public FirmaService(
            FirmaService firmaSrv,
            SolicitudService solicitudSrv,
            FamiliaNovedadService familiaNovedadSrv,
            NovedadCTService novedadCTSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            RegionalService regionalSrv,
            FirmaJpaRepository repository) {
        this.firmaSrv = firmaSrv;
        this.solicitudSrv = solicitudSrv;
        this.familiaNovedadSrv = familiaNovedadSrv;
        this.novedadCTSrv = novedadCTSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.regionalSrv = regionalSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, FirmaConstants.CLASS_NAME);
    }

    private List<Firma> solveFirmaTypedQuery(QueryRequest request) {
        boolean hasInMemoryFilters = hasInMemoryFilters(request);
        int requestedPageSize = request.getPageSize();
        int pageSize = requestedPageSize >= 0
                && requestedPageSize <= Constants.MAX_BLOCKSIZE
                ? requestedPageSize
                : Constants.DEFAULT_BLOCKSIZE;
        int pageNumber = Math.max(1, request.getPageNumber());

    	List<Firma> results = repository.solveQuery(processQueryRequest(request, FirmaConstants.CLASS_NAME), (hasInMemoryFilters ? 0 : pageSize), (hasInMemoryFilters ? 1 : pageNumber)).stream()
                .filter(instance -> hasInMemoryFilters ? applyMemoryFilters(request, instance) : true).collect(Collectors.toList()); 

        if (hasInMemoryFilters && results != null && !results.isEmpty()) {
            long fromInstance = ((long) pageNumber - 1) * pageSize;
            long toInstance = (long) pageNumber * pageSize;
            if (pageSize == 0) {
               fromInstance = 0;
               toInstance = results.size();
            }
            int from = (int) Math.min(fromInstance, results.size());
            int to = (int) Math.min(toInstance, results.size());
            results = (fromInstance > results.size() - 1)
                    ? new ArrayList<>()
                    : results.subList(from, Math.max(from, to));
        }

        return results;
    }
    
    private int solveFirmaTypedCountQuery(QueryRequest request) {
        return hasInMemoryFilters(request)
               ? repository.solveQuery(processQueryRequest(request, FirmaConstants.CLASS_NAME)).stream().filter(instance -> applyMemoryFilters(request, instance)).collect(Collectors.toList()).size()
               : repository.solveCountQuery(processCountQueryRequest(request, FirmaConstants.CLASS_NAME));
    }

    private boolean hasInMemoryFilters(QueryRequest queryRequest) {
        for(FilterRequest filterRequest : queryRequest.getFilters()) {
            if (hasInMemoryConjunctions(filterRequest)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasInMemoryConjunctions(FilterRequest filterRequest) {
        if (FirmaConstants.FILTER_NAME_FFIRMASRESPONSABLES.equalsIgnoreCase(filterRequest.getName())) {
            return true;
        }
        return false;
    }

    /*
     * Returns the population of class Firma
     *
     * @return Population of class Firma
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Firma> get() {
        List<Firma> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class Firma that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Firma matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Firma> get(QueryRequest queryRequest) {
        return solveFirmaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveFirmaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveFirmaTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
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
        return new PopulationResponseViewModel(solveFirmaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Firma by ID
     *
     * @param firmaIdentificador Identification field firmaIdentificador
     *
     * @return Instance of Firma
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Firma getById(Long firmaIdentificador) {
        return getByOID(new FirmaOid(firmaIdentificador));
    }
    
    /*
     * Returns an instance of Firma by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Firma
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Firma getByOID(FirmaOid oid) {
        Optional<Firma> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Firma by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Firma
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Firma getByOIDWithHV(FirmaOid oid) {
        Optional<Firma> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Firma> getInstanceByOid(FirmaOid oid) {
        return oid != null && oid.getFirmaIdentificador() != null
               ? repository.findById(oid.getFirmaIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of Firma by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Firma
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Firma getByJsonOid(String jsonOid) {
        FirmaOid oid = new FirmaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Firma related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Firma
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Firma> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveFirmaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Firma related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Firma
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Firma> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Firma
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Firma
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getFirmaDynamic(FirmaOid oid, String displaySetItems) {
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
    public String getSuppInfo(FirmaOid oid, String dsName) {
        Firma instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Firma instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (FirmaConstants.FILTER_NAME_FFIRMASRESPONSABLES.equalsIgnoreCase(filterRequest.getName()))
            fFirmasResponsables(filterRequest, sql);
    }

    private boolean applyMemoryFilters(QueryRequest queryRequest, Firma instance) {
        for (FilterRequest filterRequest : queryRequest.getFilters()) {
            if (!applyMemoryFilter(filterRequest, instance))
                return false;
        }
        return true;
    }
    
    private boolean applyMemoryFilter(FilterRequest filterRequest, Firma instance) {
        return applyMemoryQueryFilter(filterRequest, instance);
    }

    private boolean applyMemoryQueryFilter(FilterRequest filterRequest, Firma entity) {
        if (FirmaConstants.FILTER_NAME_FFIRMASRESPONSABLES.equalsIgnoreCase(filterRequest.getName()))
            return inMemoryFFirmasResponsables(filterRequest,  entity);
        return true;
    }


    private void fFirmasResponsables(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vUsuario = filterRequest.hasFilterVariable(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VUSUARIO) ? (String) filterRequest.getFilterVariable(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VUSUARIO, FilterVariableRequest.STRING) : null;
        Regional voRegional = filterRequest.hasFilterVariable(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VOREGIONAL) ? regionalSrv.getByOID(new RegionalOid().fromJsonString(toJson(filterRequest.getFilterVariable(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VOREGIONAL)))) : null;

        // EXIST (RespRegional) = true
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(sql));
    conjunction.addOperand(operandSubQueryAssocOperator000);


        // RespRegional.Usuario LIKE "%" + vUsuario
        if (vUsuario != null) {
            // 'like' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FirmaConstants.ROLE_NAME_RESPREGIONAL + "." + FuncionarioConstants.ATTR_NAME_FUNCIONARIOUSUARIO, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral("%" + vUsuario, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // RespRegional.Sucursal.Regional = voRegional
        if (voRegional != null) {
            // '=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand114 = new SQLWhereOperandPathInstance(FirmaConstants.ROLE_NAME_RESPREGIONAL + "." + RespRegionalConstants.ROLE_NAME_SUCURSAL + "." + SucursalConstants.ROLE_NAME_REGIONAL, RegionalConstants.CLASS_NAME);
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            SQLWhereOperandOVParameter operand225 = new SQLWhereOperandOVParameter(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VOREGIONAL, RegionalConstants.CLASS_NAME, RegionalConstants.CLASS_NAME, voRegional.getOid(), FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VOREGIONAL);
            comparison3.setSecondOperand(operand225);
            conjunction.addOperand(comparison3);

        }        

        sql.addConjunction(conjunction);
    }

    private boolean inMemoryFFirmasResponsables(FilterRequest filterRequest, Firma entity) {
        boolean result = true;
        String vNombre = filterRequest.hasFilterVariable(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VNOMBRE) ? (String) filterRequest.getFilterVariable(FirmaConstants.VAR_NAME_FFIRMASRESPONSABLES_VNOMBRE, FilterVariableRequest.STRING) : null;

        // RespRegional.drvNombreCompleto LIKE "%" + vNombre
        try {
            if (vNombre != null) {
                result = Utilities.like(entity.getRespRegional().getFuncionariodrvNombreCompleto(), "%" + vNombre);
            }
        } catch(Exception e) {
           LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
           throw new SystemException(e.getMessage());
        }
        return result;
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUFirmasRespRegional
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Firma using displayset DSFirmaPIUResponsable that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.firma.ds.DSFirmaPIUResponsable> piuPIUFirmasRespRegional(QueryRequest queryRequest) {
        return solveFirmaTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.firma.ds.DSFirmaPIUResponsable::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFirmasRespRegional
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFirmasRespRegional
     */
    public int getCount4PIUFirmasRespRegional(QueryRequest queryRequest) {
        return solveFirmaTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUFirmaNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Firma using displayset DSFirmaNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.firma.ds.DSFirmaNull> piuPIUFirmaNull(QueryRequest queryRequest) {
        return solveFirmaTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.firma.ds.DSFirmaNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFirmaNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFirmaNull
     */
    public int getCount4PIUFirmaNull(QueryRequest queryRequest) {
        return solveFirmaTypedQuery(queryRequest).size();
    }

    // Service: crear

    /*
     * Entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse crear(
            com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.crearTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse crearTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest request) {
        request.setFirmacrearpagrSolicitudSegundaInstance(solicitudSrv.getByOIDWithHV(request.getFirmacrearpagrSolicitudSegunda()));
        request.setFirmacrearpagrRespRegionalInstance(respRegionalSrv.getByOIDWithHV(request.getFirmacrearpagrRespRegional()));
        request.setFirmacrearpagrSolicitudContratanteInstance(solicitudSrv.getByOIDWithHV(request.getFirmacrearpagrSolicitudContratante()));
        request.setFirmacrearpagrFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getFirmacrearpagrFamiliaNovedad()));
        request.setFirmacrearpagrNovedadesCTInstance(novedadCTSrv.getByOIDWithHV(request.getFirmacrearpagrNovedadesCT()));
        request.setFirmacrearpagrSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getFirmacrearpagrSolicitud()));
        request.setFirmacrearpagrSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getFirmacrearpagrSolicitudNovedad()));
        return firmaSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse crearImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        Solicitud firmacrearpagrSolicitudSegunda = request.getFirmacrearpagrSolicitudSegundaInstance();
        RespRegional firmacrearpagrRespRegional = request.getFirmacrearpagrRespRegionalInstance();
        Solicitud firmacrearpagrSolicitudContratante = request.getFirmacrearpagrSolicitudContratanteInstance();
        Solicitud firmacrearpagrSolicitud = request.getFirmacrearpagrSolicitudInstance();
        byte[] firmacrearpatrImagenFirma = request.getFirmacrearpatrImagenFirma();
        String firmacrearpatrLatitud = request.getFirmacrearpatrLatitud();
        String firmacrearpatrLongitud = request.getFirmacrearpatrLongitud();
        Firma instance = new Firma();
        if (firmacrearpagrSolicitudSegunda != null && !firmacrearpagrSolicitudSegunda.isNull()) {
            firmacrearpagrSolicitudSegunda.add2SegundaFirma(instance);
            instance.add2SolicitudSegunda(firmacrearpagrSolicitudSegunda);
        }

        if (firmacrearpagrRespRegional != null && !firmacrearpagrRespRegional.isNull()) {
            firmacrearpagrRespRegional.add2Firma(instance);
            instance.add2RespRegional(firmacrearpagrRespRegional);
        }

        if (firmacrearpagrSolicitudContratante != null && !firmacrearpagrSolicitudContratante.isNull()) {
            firmacrearpagrSolicitudContratante.add2FirmaContratanteIncl(instance);
            instance.add2SolicitudContratante(firmacrearpagrSolicitudContratante);
        }



        if (firmacrearpagrSolicitud != null && !firmacrearpagrSolicitud.isNull()) {
            firmacrearpagrSolicitud.add2Firma(instance);
            instance.add2Solicitud(firmacrearpagrSolicitud);
        }


        instance.setFirmaIdentificador(Long.valueOf(0));
        instance.setFirmaImagenFirma(firmacrearpatrImagenFirma);
        instance.setFirmaLatitud(firmacrearpatrLatitud);
        instance.setFirmaLongitud(firmacrearpatrLongitud);

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: eliminar

    /*
     * Entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse eliminar(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.eliminarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse eliminarTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest request) throws ModelException {
        request.setFirmaeliminarpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaeliminarpthisFirma()));
        return firmaSrv.eliminarImplementation(request);
    }

    /*
     * Implements service eliminar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse eliminarImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaeliminarpthisFirma = request.getFirmaeliminarpthisFirmaInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        Firma instance = firmaeliminarpthisFirma;
        if(firmaeliminarpthisFirma.getSolicitudNovedad() != null && !firmaeliminarpthisFirma.getSolicitudNovedad().isNull()) {
            throw new MinCardinalityException(FirmaConstants.CLASS_ALIAS, FirmaConstants.ROLE_ALIAS_SOLICITUDNOVEDAD);
        }
        if(firmaeliminarpthisFirma.getFamiliaNovedad() != null && !firmaeliminarpthisFirma.getFamiliaNovedad().isNull()) {
            throw new MinCardinalityException(FirmaConstants.CLASS_ALIAS, FirmaConstants.ROLE_ALIAS_FAMILIANOVEDAD);
        }
        if(firmaeliminarpthisFirma.getNovedadesCT() != null && !firmaeliminarpthisFirma.getNovedadesCT().isNull()) {
            throw new MinCardinalityException(FirmaConstants.CLASS_ALIAS, FirmaConstants.ROLE_ALIAS_NOVEDADESCT);
        }


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setFirmaeliminarpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaeliminarpthisFirma()));
        try {
            if("Clas_1432994709504560Pre_1".equals(request.getPreconditionId()))
                checkClas1432994709504560Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1432994709504560Pre1(request);
    }

    private void checkClas1432994709504560Pre1 (
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST( RespRegional ) = true

        Firma firmaeliminarpthisFirma = request.getFirmaeliminarpthisFirmaInstance();
        try {
            boolean preconditionHolds = repository.assocOperator001(firmaeliminarpthisFirma).equals(true);
            if (!preconditionHolds) throw new PreconditionException("Sólo se pueden eliminar las firmas de los responsables de regional");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    // Service: modificar

    /*
     * Entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificar(
            com.keralty.aefirmas.viewmodel.firma.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.modificarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.ModificarRequest request) throws ModelException {
        request.setFirmamodificarpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmamodificarpthisFirma()));
        return firmaSrv.modificarImplementation(request);
    }

    /*
     * Implements service modificar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmamodificarpthisFirma = request.getFirmamodificarpthisFirmaInstance();
        byte[] firmamodificarpImagenFirma = request.getFirmamodificarpImagenFirma();
        String firmamodificarpLatitud = request.getFirmamodificarpLatitud();
        String firmamodificarpLongitud = request.getFirmamodificarpLongitud();

        request.checkArguments();
        Firma instance = firmamodificarpthisFirma;
        instance.setFirmaImagenFirma(firmamodificarpImagenFirma);
        instance.setFirmaLatitud(firmamodificarpLatitud);
        instance.setFirmaLongitud(firmamodificarpLongitud);


        response.setInstance(repository.save(instance));

        return response;
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
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.insFirmaTitularFamiliaNovedadTxn(request);
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
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest request) throws ModelException {
        request.setFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaInsFirmaTitularFamiliaNovedadpthisFirma()));
        request.setFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad()));
        return firmaSrv.insFirmaTitularFamiliaNovedadImplementation(request, true);
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
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaInsFirmaTitularFamiliaNovedadpthisFirma = request.getFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance();
        FamiliaNovedad firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad = request.getFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance();

        request.checkArguments();
        Firma instance = firmaInsFirmaTitularFamiliaNovedadpthisFirma;

        instance.add2FamiliaNovedad(firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.familianovedad.siu.InsFirmaTitularFamiliaNovedadRequest relatedRequest = new com.keralty.aefirmas.viewmodel.familianovedad.siu.InsFirmaTitularFamiliaNovedadRequest();
            relatedRequest.setFamiliaNovedadInsFirmaTitularFamiliaNovedadpthisFamiliaNovedadInstance(firmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedad);
            relatedRequest.setFamiliaNovedadInsFirmaTitularFamiliaNovedadpevcFirmaInstance(firmaInsFirmaTitularFamiliaNovedadpthisFirma);
            familiaNovedadSrv.insFirmaTitularFamiliaNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

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
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaTitulaFamiliaNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.delFirmaTitulaFamiliaNovedadTxn(request);
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
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaTitulaFamiliaNovedadRequest request) throws ModelException {
        request.setFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaDelFirmaTitulaFamiliaNovedadpthisFirma()));
        request.setFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad()));
        return firmaSrv.delFirmaTitulaFamiliaNovedadImplementation(request, true);
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
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaTitulaFamiliaNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaDelFirmaTitulaFamiliaNovedadpthisFirma = request.getFirmaDelFirmaTitulaFamiliaNovedadpthisFirmaInstance();
        FamiliaNovedad firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad = request.getFirmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedadInstance();

        request.checkArguments();
        Firma instance = firmaDelFirmaTitulaFamiliaNovedadpthisFirma;

        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.familianovedad.siu.DelFirmaTitulaFamiliaNovedadRequest relatedRequest = new com.keralty.aefirmas.viewmodel.familianovedad.siu.DelFirmaTitulaFamiliaNovedadRequest();
            relatedRequest.setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpthisFamiliaNovedadInstance(firmaDelFirmaTitulaFamiliaNovedadpevcFamiliaNovedad);
            relatedRequest.setFamiliaNovedadDelFirmaTitulaFamiliaNovedadpevcFirmaInstance(firmaDelFirmaTitulaFamiliaNovedadpthisFirma);
            familiaNovedadSrv.delFirmaTitulaFamiliaNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsFirmaNovedadCT

    /*
     * Entry point for service InsFirmaNovedadCT
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insFirmaNovedadCT(
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.insFirmaNovedadCTTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsFirmaNovedadCT
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insFirmaNovedadCTTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest request) throws ModelException {
        request.setFirmaInsFirmaNovedadCTpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaInsFirmaNovedadCTpthisFirma()));
        request.setFirmaInsFirmaNovedadCTpevcNovedadCTInstance(novedadCTSrv.getByOIDWithHV(request.getFirmaInsFirmaNovedadCTpevcNovedadCT()));
        return firmaSrv.insFirmaNovedadCTImplementation(request, true);
    }

    /*
     * Implements service InsFirmaNovedadCT
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insFirmaNovedadCTImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaInsFirmaNovedadCTpthisFirma = request.getFirmaInsFirmaNovedadCTpthisFirmaInstance();
        NovedadCT firmaInsFirmaNovedadCTpevcNovedadCT = request.getFirmaInsFirmaNovedadCTpevcNovedadCTInstance();

        request.checkArguments();
        Firma instance = firmaInsFirmaNovedadCTpthisFirma;

        instance.add2NovedadesCT(firmaInsFirmaNovedadCTpevcNovedadCT);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.novedadct.siu.InsFirmaNovedadCTRequest relatedRequest = new com.keralty.aefirmas.viewmodel.novedadct.siu.InsFirmaNovedadCTRequest();
            relatedRequest.setNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance(firmaInsFirmaNovedadCTpevcNovedadCT);
            relatedRequest.setNovedadCTInsFirmaNovedadCTpevcFirmaInstance(firmaInsFirmaNovedadCTpthisFirma);
            novedadCTSrv.insFirmaNovedadCTImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelFirmaNovedadCT

    /*
     * Entry point for service DelFirmaNovedadCT
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delFirmaNovedadCT(
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaNovedadCTRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.delFirmaNovedadCTTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelFirmaNovedadCT
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delFirmaNovedadCTTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaNovedadCTRequest request) throws ModelException {
        request.setFirmaDelFirmaNovedadCTpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaDelFirmaNovedadCTpthisFirma()));
        request.setFirmaDelFirmaNovedadCTpevcNovedadCTInstance(novedadCTSrv.getByOIDWithHV(request.getFirmaDelFirmaNovedadCTpevcNovedadCT()));
        return firmaSrv.delFirmaNovedadCTImplementation(request, true);
    }

    /*
     * Implements service DelFirmaNovedadCT
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delFirmaNovedadCTImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaNovedadCTRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaDelFirmaNovedadCTpthisFirma = request.getFirmaDelFirmaNovedadCTpthisFirmaInstance();
        NovedadCT firmaDelFirmaNovedadCTpevcNovedadCT = request.getFirmaDelFirmaNovedadCTpevcNovedadCTInstance();

        request.checkArguments();
        Firma instance = firmaDelFirmaNovedadCTpthisFirma;

        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.novedadct.siu.DelFirmaNovedadCTRequest relatedRequest = new com.keralty.aefirmas.viewmodel.novedadct.siu.DelFirmaNovedadCTRequest();
            relatedRequest.setNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance(firmaDelFirmaNovedadCTpevcNovedadCT);
            relatedRequest.setNovedadCTDelFirmaNovedadCTpevcFirmaInstance(firmaDelFirmaNovedadCTpthisFirma);
            novedadCTSrv.delFirmaNovedadCTImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: eliminarInterno

    /*
     * Entry point for service eliminarInterno
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse eliminarInterno(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarInternoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.eliminarInternoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service eliminarInterno
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse eliminarInternoTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarInternoRequest request) throws ModelException {
        request.setFirmaeliminarInternopthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaeliminarInternopthisFirma()));
        return firmaSrv.eliminarInternoImplementation(request);
    }

    /*
     * Implements service eliminarInterno
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse eliminarInternoImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.EliminarInternoRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaeliminarInternopthisFirma = request.getFirmaeliminarInternopthisFirmaInstance();

        request.checkArguments();
        Firma instance = firmaeliminarInternopthisFirma;
        if(firmaeliminarInternopthisFirma.getSolicitudNovedad() != null && !firmaeliminarInternopthisFirma.getSolicitudNovedad().isNull()) {
            throw new MinCardinalityException(FirmaConstants.CLASS_ALIAS, FirmaConstants.ROLE_ALIAS_SOLICITUDNOVEDAD);
        }
        if(firmaeliminarInternopthisFirma.getFamiliaNovedad() != null && !firmaeliminarInternopthisFirma.getFamiliaNovedad().isNull()) {
            throw new MinCardinalityException(FirmaConstants.CLASS_ALIAS, FirmaConstants.ROLE_ALIAS_FAMILIANOVEDAD);
        }
        if(firmaeliminarInternopthisFirma.getNovedadesCT() != null && !firmaeliminarInternopthisFirma.getNovedadesCT().isNull()) {
            throw new MinCardinalityException(FirmaConstants.CLASS_ALIAS, FirmaConstants.ROLE_ALIAS_NOVEDADESCT);
        }


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    // Service: InsFirmaSolNov

    /*
     * Entry point for service InsFirmaSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insFirmaSolNov(
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaSolNovRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.insFirmaSolNovTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsFirmaSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insFirmaSolNovTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaSolNovRequest request) throws ModelException {
        request.setFirmaInsFirmaSolNovpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaInsFirmaSolNovpthisFirma()));
        request.setFirmaInsFirmaSolNovpevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getFirmaInsFirmaSolNovpevcSolicitudNovedad()));
        return firmaSrv.insFirmaSolNovImplementation(request, true);
    }

    /*
     * Implements service InsFirmaSolNov
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insFirmaSolNovImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaSolNovRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaInsFirmaSolNovpthisFirma = request.getFirmaInsFirmaSolNovpthisFirmaInstance();
        SolicitudNovedad firmaInsFirmaSolNovpevcSolicitudNovedad = request.getFirmaInsFirmaSolNovpevcSolicitudNovedadInstance();

        request.checkArguments();
        Firma instance = firmaInsFirmaSolNovpthisFirma;

        instance.add2SolicitudNovedad(firmaInsFirmaSolNovpevcSolicitudNovedad);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.solicitudnovedad.siu.InsFirmaSolNovRequest relatedRequest = new com.keralty.aefirmas.viewmodel.solicitudnovedad.siu.InsFirmaSolNovRequest();
            relatedRequest.setSolicitudNovedadInsFirmaSolNovpthisSolicitudNovedadInstance(firmaInsFirmaSolNovpevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadInsFirmaSolNovpevcFirmaInstance(firmaInsFirmaSolNovpthisFirma);
            solicitudNovedadSrv.insFirmaSolNovImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelFirmaSolNov

    /*
     * Entry point for service DelFirmaSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delFirmaSolNov(
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaSolNovRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.delFirmaSolNovTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelFirmaSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delFirmaSolNovTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaSolNovRequest request) throws ModelException {
        request.setFirmaDelFirmaSolNovpthisFirmaInstance(firmaSrv.getByOIDWithHV(request.getFirmaDelFirmaSolNovpthisFirma()));
        request.setFirmaDelFirmaSolNovpevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getFirmaDelFirmaSolNovpevcSolicitudNovedad()));
        return firmaSrv.delFirmaSolNovImplementation(request, true);
    }

    /*
     * Implements service DelFirmaSolNov
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delFirmaSolNovImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaSolNovRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Firma firmaDelFirmaSolNovpthisFirma = request.getFirmaDelFirmaSolNovpthisFirmaInstance();
        SolicitudNovedad firmaDelFirmaSolNovpevcSolicitudNovedad = request.getFirmaDelFirmaSolNovpevcSolicitudNovedadInstance();

        request.checkArguments();
        Firma instance = firmaDelFirmaSolNovpthisFirma;

        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.solicitudnovedad.siu.DelFirmaSolNovRequest relatedRequest = new com.keralty.aefirmas.viewmodel.solicitudnovedad.siu.DelFirmaSolNovRequest();
            relatedRequest.setSolicitudNovedadDelFirmaSolNovpthisSolicitudNovedadInstance(firmaDelFirmaSolNovpevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadDelFirmaSolNovpevcFirmaInstance(firmaDelFirmaSolNovpthisFirma);
            solicitudNovedadSrv.delFirmaSolNovImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TCREARSOLNOV

    /*
     * Entry point for service TCREARSOLNOV
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREARSOLNOV(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARSOLNOVRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.tCREARSOLNOVTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREARSOLNOV
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARSOLNOVTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARSOLNOVRequest request) throws ModelException {
        request.setFirmaTCREARSOLNOVptpagrSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getFirmaTCREARSOLNOVptpagrSolicitudNovedad()));
        return firmaSrv.tCREARSOLNOVImplementation(request);
    }

    /*
     * Implements service TCREARSOLNOV
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARSOLNOVImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARSOLNOVRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        SolicitudNovedad firmaTCREARSOLNOVptpagrSolicitudNovedad = request.getFirmaTCREARSOLNOVptpagrSolicitudNovedadInstance();
        byte[] firmaTCREARSOLNOVptpatrImagenFirma = request.getFirmaTCREARSOLNOVptpatrImagenFirma();
        String firmaTCREARSOLNOVptpatrLatitud = request.getFirmaTCREARSOLNOVptpatrLatitud();
        String firmaTCREARSOLNOVptpatrLongitud = request.getFirmaTCREARSOLNOVptpatrLongitud();
        Firma instance;

        com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest step1Request = new com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest();
        step1Request.setFirmacrearpagrSolicitudSegundaInstance(null);
        step1Request.setFirmacrearpagrRespRegionalInstance(null);
        step1Request.setFirmacrearpagrSolicitudContratanteInstance(null);
        step1Request.setFirmacrearpagrFamiliaNovedadInstance(null);
        step1Request.setFirmacrearpagrNovedadesCTInstance(null);
        step1Request.setFirmacrearpagrSolicitudInstance(null);
        step1Request.setFirmacrearpagrSolicitudNovedadInstance(firmaTCREARSOLNOVptpagrSolicitudNovedad);
        step1Request.setFirmacrearpatrImagenFirma(firmaTCREARSOLNOVptpatrImagenFirma);
        step1Request.setFirmacrearpatrLatitud(firmaTCREARSOLNOVptpatrLatitud);
        step1Request.setFirmacrearpatrLongitud(firmaTCREARSOLNOVptpatrLongitud);
        instance = (Firma) firmaSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaSolNovRequest step2Request = new com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaSolNovRequest();
        step2Request.setFirmaInsFirmaSolNovpthisFirmaInstance(instance);
        step2Request.setFirmaInsFirmaSolNovpevcSolicitudNovedadInstance(firmaTCREARSOLNOVptpagrSolicitudNovedad);
        firmaSrv.insFirmaSolNovImplementation(step2Request, true);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TCREARTITFAMNOV

    /*
     * Entry point for service TCREARTITFAMNOV
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREARTITFAMNOV(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARTITFAMNOVRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.tCREARTITFAMNOVTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREARTITFAMNOV
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTITFAMNOVTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARTITFAMNOVRequest request) throws ModelException {
        request.setFirmaTCREARTITFAMNOVptFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getFirmaTCREARTITFAMNOVptFamiliaNovedad()));
        return firmaSrv.tCREARTITFAMNOVImplementation(request);
    }

    /*
     * Implements service TCREARTITFAMNOV
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARTITFAMNOVImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARTITFAMNOVRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FamiliaNovedad firmaTCREARTITFAMNOVptFamiliaNovedad = request.getFirmaTCREARTITFAMNOVptFamiliaNovedadInstance();
        byte[] firmaTCREARTITFAMNOVptpatrImagenFirma = request.getFirmaTCREARTITFAMNOVptpatrImagenFirma();
        String firmaTCREARTITFAMNOVptpatrLatitud = request.getFirmaTCREARTITFAMNOVptpatrLatitud();
        String firmaTCREARTITFAMNOVptpatrLongitud = request.getFirmaTCREARTITFAMNOVptpatrLongitud();
        Firma instance;

        com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest step1Request = new com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest();
        step1Request.setFirmacrearpagrSolicitudSegundaInstance(null);
        step1Request.setFirmacrearpagrRespRegionalInstance(null);
        step1Request.setFirmacrearpagrSolicitudContratanteInstance(null);
        step1Request.setFirmacrearpagrFamiliaNovedadInstance(firmaTCREARTITFAMNOVptFamiliaNovedad);
        step1Request.setFirmacrearpagrNovedadesCTInstance(null);
        step1Request.setFirmacrearpagrSolicitudInstance(null);
        step1Request.setFirmacrearpagrSolicitudNovedadInstance(null);
        step1Request.setFirmacrearpatrImagenFirma(firmaTCREARTITFAMNOVptpatrImagenFirma);
        step1Request.setFirmacrearpatrLatitud(firmaTCREARTITFAMNOVptpatrLatitud);
        step1Request.setFirmacrearpatrLongitud(firmaTCREARTITFAMNOVptpatrLongitud);
        instance = (Firma) firmaSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest step2Request = new com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaTitularFamiliaNovedadRequest();
        step2Request.setFirmaInsFirmaTitularFamiliaNovedadpthisFirmaInstance(instance);
        step2Request.setFirmaInsFirmaTitularFamiliaNovedadpevcFamiliaNovedadInstance(firmaTCREARTITFAMNOVptFamiliaNovedad);
        firmaSrv.insFirmaTitularFamiliaNovedadImplementation(step2Request, true);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TCREARNOVEDADCT

    /*
     * Entry point for service TCREARNOVEDADCT
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREARNOVEDADCT(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARNOVEDADCTRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = firmaSrv.tCREARNOVEDADCTTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREARNOVEDADCT
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARNOVEDADCTTxn(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARNOVEDADCTRequest request) throws ModelException {
        request.setFirmaTCREARNOVEDADCTptNovedadCTInstance(novedadCTSrv.getByOIDWithHV(request.getFirmaTCREARNOVEDADCTptNovedadCT()));
        return firmaSrv.tCREARNOVEDADCTImplementation(request);
    }

    /*
     * Implements service TCREARNOVEDADCT
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARNOVEDADCTImplementation(
            com.keralty.aefirmas.viewmodel.firma.siu.TCREARNOVEDADCTRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        NovedadCT firmaTCREARNOVEDADCTptNovedadCT = request.getFirmaTCREARNOVEDADCTptNovedadCTInstance();
        byte[] firmaTCREARNOVEDADCTptpatrImagenFirma = request.getFirmaTCREARNOVEDADCTptpatrImagenFirma();
        String firmaTCREARNOVEDADCTptpatrLatitud = request.getFirmaTCREARNOVEDADCTptpatrLatitud();
        String firmaTCREARNOVEDADCTptpatrLongitud = request.getFirmaTCREARNOVEDADCTptpatrLongitud();

        request.checkArguments();
        Firma instance;

        com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest step1Request = new com.keralty.aefirmas.viewmodel.firma.siu.CrearRequest();
        step1Request.setFirmacrearpagrSolicitudSegundaInstance(null);
        step1Request.setFirmacrearpagrRespRegionalInstance(null);
        step1Request.setFirmacrearpagrSolicitudContratanteInstance(null);
        step1Request.setFirmacrearpagrFamiliaNovedadInstance(null);
        step1Request.setFirmacrearpagrNovedadesCTInstance(firmaTCREARNOVEDADCTptNovedadCT);
        step1Request.setFirmacrearpagrSolicitudInstance(null);
        step1Request.setFirmacrearpagrSolicitudNovedadInstance(null);
        step1Request.setFirmacrearpatrImagenFirma(firmaTCREARNOVEDADCTptpatrImagenFirma);
        step1Request.setFirmacrearpatrLatitud(firmaTCREARNOVEDADCTptpatrLatitud);
        step1Request.setFirmacrearpatrLongitud(firmaTCREARNOVEDADCTptpatrLongitud);
        instance = (Firma) firmaSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest step2Request = new com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest();
        step2Request.setFirmaInsFirmaNovedadCTpthisFirmaInstance(instance);
        step2Request.setFirmaInsFirmaNovedadCTpevcNovedadCTInstance(firmaTCREARNOVEDADCTptNovedadCT);
        firmaSrv.insFirmaNovedadCTImplementation(step2Request, true);

        response.setInstance(getByOID(instance.getOid()));

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
        select.getOrderByPart().addItem(Constants.normalizePath(FirmaConstants.CLASS_NAME, FirmaConstants.ATTR_FIELD_FIRMAIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildFirmaRelatedInstance(relatedInstanceJsonOid);
        }
        if (FirmaConstants.ROLE_NAME_SOLICITUDNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFirmaRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (FirmaConstants.ROLE_NAME_FAMILIANOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFirmaRelatedInstance4FamiliaNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (FirmaConstants.ROLE_NAME_NOVEDADESCT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFirmaRelatedInstance4NovedadCT(relatedInstanceJsonOid, remainingPath);
        }
        if (FirmaConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem) || FirmaConstants.ROLE_NAME_SOLICITUDSEGUNDA.equalsIgnoreCase(relatedInstancePathFirstItem) || FirmaConstants.ROLE_NAME_SOLICITUDCONTRATANTE.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFirmaRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (FirmaConstants.ROLE_NAME_RESPREGIONAL.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFirmaRelatedInstance4RespRegional(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildFirmaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Firma(mapper.readValue(relatedInstanceJsonOid, FirmaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFirmaRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFirmaRelatedInstance4FamiliaNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaNovedad(mapper.readValue(relatedInstanceJsonOid, FamiliaNovedadOid.class)): FamiliaNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFirmaRelatedInstance4NovedadCT(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new NovedadCT(mapper.readValue(relatedInstanceJsonOid, NovedadCTOid.class)): NovedadCT.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFirmaRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFirmaRelatedInstance4RespRegional(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new RespRegional(mapper.readValue(relatedInstanceJsonOid, RespRegionalOid.class)): RespRegional.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            FirmaOid oid = new FirmaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FirmaConstants.ATTR_FIELD_FIRMAIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFirmaIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(FirmaService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
