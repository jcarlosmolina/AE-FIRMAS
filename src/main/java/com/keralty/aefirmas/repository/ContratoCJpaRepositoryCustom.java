package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.persistence.BeneficiarioC;
import com.keralty.aefirmas.persistence.ContratoC;
import com.keralty.aefirmas.sqlutils.SQLExists;
import com.keralty.aefirmas.sqlutils.SQLGetOne;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLPopulation;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface ContratoCJpaRepositoryCustom {

    List<ContratoC> solveQuery(SQLSelect query);
    
    List<ContratoC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Double assocOperator000(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator000Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator000Range1Query(ContratoC instance) throws SystemException;

    Double assocOperator001(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator001Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator001Range1Query(ContratoC instance) throws SystemException;

    Double assocOperator002(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator002Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(ContratoC instance) throws SystemException;

    Boolean assocOperator003(ContratoC instance) throws SystemException;

    Boolean assocOperator003Range1(ContratoC instance) throws SystemException;
    
    SQLExists assocOperator003Range1Query(ContratoC instance) throws SystemException;

    Boolean assocOperator004(ContratoC instance) throws SystemException;

    Boolean assocOperator004Range1(ContratoC instance) throws SystemException;
    
    SQLExists assocOperator004Range1Query(ContratoC instance) throws SystemException;

    Boolean assocOperator005(ContratoC instance) throws SystemException;

    Boolean assocOperator005Range1(ContratoC instance) throws SystemException;
    
    SQLExists assocOperator005Range1Query(ContratoC instance) throws SystemException;

    String assocOperator006(ContratoC instance) throws SystemException;

    String assocOperator006Range1(ContratoC instance) throws SystemException;
    
    SQLGetOne assocOperator006Range1Query(ContratoC instance) throws SystemException;

    public List<ContratoC> executeQuery(String query);

    public List<ContratoC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
