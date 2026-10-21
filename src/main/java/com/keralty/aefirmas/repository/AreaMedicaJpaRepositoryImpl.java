package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.global.AreaMedicaConstants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.global.UserFunctions;
import com.keralty.aefirmas.persistence.AreaMedica;
import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.sqlutils.SQLJoin;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.TypedQueryBuilder;
import com.keralty.aefirmas.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class AreaMedicaJpaRepositoryImpl extends AbstractRepository implements AreaMedicaJpaRepositoryCustom {

    @Inject
    public AreaMedicaJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<AreaMedica> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<AreaMedica> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<AreaMedica> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<AreaMedica> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, AreaMedica.class);
    }

    @Override	
    public List<AreaMedica> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<AreaMedica> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public Funcionario findParent(Long oid) {
        return em.find(Funcionario.class, oid);
    }

    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param parentAlias Alias of the parent entity.
     */
    public void addFacetWhereparentfuncionario (String myAlias, SQLJoin join, String parentAlias) {
        join.addWhere(myAlias + "." + AreaMedicaConstants.FLD_FUNCIONARIOID + " = " + parentAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID);
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
