package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Productos;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.sql.Date;
import java.util.List;

public interface ProductosJpaRepositoryCustom {

    List<Productos> solveQuery(SQLSelect query);
    
    List<Productos> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Productos> executeQuery(String query);

    public List<Productos> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
