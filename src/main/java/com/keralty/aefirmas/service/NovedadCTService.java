package com.keralty.aefirmas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aefirmas.exception.ModelException;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.NovedadCTConstants;
import com.keralty.aefirmas.global.UserFunctions;
import com.keralty.aefirmas.persistence.AbstractAppEntity;
import com.keralty.aefirmas.persistence.FamiliaNovedad;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.persistence.oid.FamiliaNovedadOid;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.persistence.oid.NovedadCTOid;
import com.keralty.aefirmas.persistence.oid.TipoIdentificacionOid;
import com.keralty.aefirmas.persistence.TipoIdentificacion;
import com.keralty.aefirmas.repository.NovedadCTJpaRepository;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathAttribute;
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
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class NovedadCTService extends AbstractService {

    NovedadCTJpaRepository repository;

    NovedadCTService novedadCTSrv;

    FirmaService firmaSrv;

    FamiliaNovedadService familiaNovedadSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public NovedadCTService(
            NovedadCTService novedadCTSrv,
            FirmaService firmaSrv,
            FamiliaNovedadService familiaNovedadSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            UserFunctions userFunctionsSrv,
            NovedadCTJpaRepository repository) {
        this.novedadCTSrv = novedadCTSrv;
        this.firmaSrv = firmaSrv;
        this.familiaNovedadSrv = familiaNovedadSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, NovedadCTConstants.CLASS_NAME);
    }

    private List<NovedadCT> solveNovedadCTTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, NovedadCTConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveNovedadCTTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, NovedadCTConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class NovedadCT
     *
     * @return Population of class NovedadCT
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<NovedadCT> get() {
        List<NovedadCT> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class NovedadCT that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class NovedadCT matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<NovedadCT> get(QueryRequest queryRequest) {
        return solveNovedadCTTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveNovedadCTTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveNovedadCTTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveNovedadCTTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of NovedadCT by ID
     *
     * @param novedadCTidNovedadCT Identification field novedadCTidNovedadCT
     *
     * @return Instance of NovedadCT
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public NovedadCT getById(Long novedadCTidNovedadCT) {
        return getByOID(new NovedadCTOid(novedadCTidNovedadCT));
    }
    
    /*
     * Returns an instance of NovedadCT by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of NovedadCT
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public NovedadCT getByOID(NovedadCTOid oid) {
        Optional<NovedadCT> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of NovedadCT by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of NovedadCT
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public NovedadCT getByOIDWithHV(NovedadCTOid oid) {
        Optional<NovedadCT> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<NovedadCT> getInstanceByOid(NovedadCTOid oid) {
        return oid != null && oid.getNovedadCTidNovedadCT() != null
               ? repository.findById(oid.getNovedadCTidNovedadCT())
               : Optional.empty();
    }

    /*
     * Returns an instance of NovedadCT by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of NovedadCT
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public NovedadCT getByJsonOid(String jsonOid) {
        NovedadCTOid oid = new NovedadCTOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of NovedadCT related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of NovedadCT
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<NovedadCT> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveNovedadCTTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of NovedadCT related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of NovedadCT
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<NovedadCT> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of NovedadCT
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of NovedadCT
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getNovedadCTDynamic(NovedadCTOid oid, String displaySetItems) {
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
    public String getSuppInfo(NovedadCTOid oid, String dsName) {
        NovedadCT instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(NovedadCT instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUNovedadCTNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for NovedadCT using displayset DSNovedadCTNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aefirmas.viewmodel.novedadct.ds.DSNovedadCTNull> piuPIUNovedadCTNull(QueryRequest queryRequest) {
        return solveNovedadCTTypedQuery(queryRequest).stream()
                .map(com.keralty.aefirmas.viewmodel.novedadct.ds.DSNovedadCTNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUNovedadCTNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUNovedadCTNull
     */
    public int getCount4PIUNovedadCTNull(QueryRequest queryRequest) {
        return solveNovedadCTTypedQuery(queryRequest).size();
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
            com.keralty.aefirmas.viewmodel.novedadct.siu.InsFirmaNovedadCTRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = novedadCTSrv.insFirmaNovedadCTTxn(request);
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
            com.keralty.aefirmas.viewmodel.novedadct.siu.InsFirmaNovedadCTRequest request) throws ModelException {
        request.setNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance(novedadCTSrv.getByOIDWithHV(request.getNovedadCTInsFirmaNovedadCTpthisNovedadCT()));
        request.setNovedadCTInsFirmaNovedadCTpevcFirmaInstance(firmaSrv.getByOIDWithHV(request.getNovedadCTInsFirmaNovedadCTpevcFirma()));
        return novedadCTSrv.insFirmaNovedadCTImplementation(request, true);
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
            com.keralty.aefirmas.viewmodel.novedadct.siu.InsFirmaNovedadCTRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        NovedadCT novedadCTInsFirmaNovedadCTpthisNovedadCT = request.getNovedadCTInsFirmaNovedadCTpthisNovedadCTInstance();
        Firma novedadCTInsFirmaNovedadCTpevcFirma = request.getNovedadCTInsFirmaNovedadCTpevcFirmaInstance();

        request.checkArguments();
        NovedadCT instance = novedadCTInsFirmaNovedadCTpthisNovedadCT;

        instance.add2Firma(novedadCTInsFirmaNovedadCTpevcFirma);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest relatedRequest = new com.keralty.aefirmas.viewmodel.firma.siu.InsFirmaNovedadCTRequest();
            relatedRequest.setFirmaInsFirmaNovedadCTpthisFirmaInstance(novedadCTInsFirmaNovedadCTpevcFirma);
            relatedRequest.setFirmaInsFirmaNovedadCTpevcNovedadCTInstance(novedadCTInsFirmaNovedadCTpthisNovedadCT);
            firmaSrv.insFirmaNovedadCTImplementation(relatedRequest, false);
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
            com.keralty.aefirmas.viewmodel.novedadct.siu.DelFirmaNovedadCTRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = novedadCTSrv.delFirmaNovedadCTTxn(request);
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
            com.keralty.aefirmas.viewmodel.novedadct.siu.DelFirmaNovedadCTRequest request) throws ModelException {
        request.setNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance(novedadCTSrv.getByOIDWithHV(request.getNovedadCTDelFirmaNovedadCTpthisNovedadCT()));
        request.setNovedadCTDelFirmaNovedadCTpevcFirmaInstance(firmaSrv.getByOIDWithHV(request.getNovedadCTDelFirmaNovedadCTpevcFirma()));
        return novedadCTSrv.delFirmaNovedadCTImplementation(request, true);
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
            com.keralty.aefirmas.viewmodel.novedadct.siu.DelFirmaNovedadCTRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        NovedadCT novedadCTDelFirmaNovedadCTpthisNovedadCT = request.getNovedadCTDelFirmaNovedadCTpthisNovedadCTInstance();
        Firma novedadCTDelFirmaNovedadCTpevcFirma = request.getNovedadCTDelFirmaNovedadCTpevcFirmaInstance();

        request.checkArguments();
        NovedadCT instance = novedadCTDelFirmaNovedadCTpthisNovedadCT;

        instance.add2Firma(null);
        if (isEntryPoint) {
            com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaNovedadCTRequest relatedRequest = new com.keralty.aefirmas.viewmodel.firma.siu.DelFirmaNovedadCTRequest();
            relatedRequest.setFirmaDelFirmaNovedadCTpthisFirmaInstance(novedadCTDelFirmaNovedadCTpevcFirma);
            relatedRequest.setFirmaDelFirmaNovedadCTpevcNovedadCTInstance(novedadCTDelFirmaNovedadCTpthisNovedadCT);
            firmaSrv.delFirmaNovedadCTImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

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
        select.getOrderByPart().addItem(Constants.normalizePath(NovedadCTConstants.CLASS_NAME, NovedadCTConstants.ATTR_FIELD_NOVEDADCTIDNOVEDADCT, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildNovedadCTRelatedInstance(relatedInstanceJsonOid);
        }
        if (NovedadCTConstants.ROLE_NAME_FAMILIANOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadCTRelatedInstance4FamiliaNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (NovedadCTConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadCTRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (NovedadCTConstants.ROLE_NAME_FIRMA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildNovedadCTRelatedInstance4Firma(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildNovedadCTRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new NovedadCT(mapper.readValue(relatedInstanceJsonOid, NovedadCTOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadCTRelatedInstance4FamiliaNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaNovedad(mapper.readValue(relatedInstanceJsonOid, FamiliaNovedadOid.class)): FamiliaNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadCTRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildNovedadCTRelatedInstance4Firma(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Firma(mapper.readValue(relatedInstanceJsonOid, FirmaOid.class)): Firma.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            NovedadCTOid oid = new NovedadCTOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(NovedadCTConstants.ATTR_FIELD_NOVEDADCTIDNOVEDADCT, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getNovedadCTidNovedadCT(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(NovedadCTService.class).error(e.getMessage());
        }
    }

}
