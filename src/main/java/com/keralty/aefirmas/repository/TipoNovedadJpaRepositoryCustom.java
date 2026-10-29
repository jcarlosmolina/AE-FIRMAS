package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.TipoNovedad;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface TipoNovedadJpaRepositoryCustom {

    List<TipoNovedad> solveQuery(SQLSelect query);
    
    List<TipoNovedad> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<TipoNovedad> executeQuery(String query);

    public List<TipoNovedad> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
