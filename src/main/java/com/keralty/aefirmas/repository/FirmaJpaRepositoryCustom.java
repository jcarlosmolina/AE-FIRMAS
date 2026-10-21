package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.sqlutils.SQLExists;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface FirmaJpaRepositoryCustom {

    List<Firma> solveQuery(SQLSelect query);
    
    List<Firma> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000() throws SystemException;

    Boolean assocOperator000Range1() throws SystemException;
    
    SQLExists assocOperator000Range1Query(SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Firma instance) throws SystemException;

    Boolean assocOperator001Range1(Firma instance) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Firma instance) throws SystemException;

    public List<Firma> executeQuery(String query);

    public List<Firma> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
