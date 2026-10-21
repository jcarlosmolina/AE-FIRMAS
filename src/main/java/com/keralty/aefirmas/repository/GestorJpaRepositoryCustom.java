package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.persistence.Gestor;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface GestorJpaRepositoryCustom {

    List<Gestor> solveQuery(SQLSelect query);
    
    List<Gestor> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Funcionario findParent(Long oid);

    public List<Gestor> executeQuery(String query);

    public List<Gestor> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
