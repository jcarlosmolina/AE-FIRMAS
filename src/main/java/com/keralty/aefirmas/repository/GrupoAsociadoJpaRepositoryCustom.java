package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.GrupoAsociado;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface GrupoAsociadoJpaRepositoryCustom {

    List<GrupoAsociado> solveQuery(SQLSelect query);
    
    List<GrupoAsociado> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<GrupoAsociado> executeQuery(String query);

    public List<GrupoAsociado> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
