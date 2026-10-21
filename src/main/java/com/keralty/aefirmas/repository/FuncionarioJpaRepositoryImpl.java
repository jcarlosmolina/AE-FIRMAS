package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.AdminConstants;
import com.keralty.aefirmas.global.AfiliacionesConstants;
import com.keralty.aefirmas.global.AreaMedicaConstants;
import com.keralty.aefirmas.global.AsesorConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.global.GestorConstants;
import com.keralty.aefirmas.global.MesaDeApoyoConstants;
import com.keralty.aefirmas.global.RespRegionalConstants;
import com.keralty.aefirmas.global.SuperAdminConstants;
import com.keralty.aefirmas.global.UserFunctions;
import com.keralty.aefirmas.persistence.Admin;
import com.keralty.aefirmas.persistence.Afiliaciones;
import com.keralty.aefirmas.persistence.AreaMedica;
import com.keralty.aefirmas.persistence.Asesor;
import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.persistence.Gestor;
import com.keralty.aefirmas.persistence.MesaDeApoyo;
import com.keralty.aefirmas.persistence.RespRegional;
import com.keralty.aefirmas.persistence.SuperAdmin;
import com.keralty.aefirmas.sqlutils.SQLCount;
import com.keralty.aefirmas.sqlutils.SQLJoin;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereConjunction;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.TypedQueryBuilder;
import com.keralty.aefirmas.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class FuncionarioJpaRepositoryImpl extends AbstractRepository implements FuncionarioJpaRepositoryCustom {

    @Inject
    public FuncionarioJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Funcionario> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Funcionario> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Funcionario> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Funcionario> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Funcionario.class);
    }

    @Override	
    public List<Funcionario> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Funcionario> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public Admin findChildAdmin(Long oid) {
        return em.find(Admin.class, oid);
    }

    @Override
    public SuperAdmin findChildSuperAdmin(Long oid) {
        return em.find(SuperAdmin.class, oid);
    }

    @Override
    public Asesor findChildAsesor(Long oid) {
        return em.find(Asesor.class, oid);
    }

    @Override
    public AreaMedica findChildAreaMedica(Long oid) {
        return em.find(AreaMedica.class, oid);
    }

    @Override
    public Afiliaciones findChildAfiliaciones(Long oid) {
        return em.find(Afiliaciones.class, oid);
    }

    @Override
    public RespRegional findChildRespRegional(Long oid) {
        return em.find(RespRegional.class, oid);
    }

    @Override
    public Gestor findChildGestor(Long oid) {
        return em.find(Gestor.class, oid);
    }

    @Override
    public MesaDeApoyo findChildMesaDeApoyo(Long oid) {
        return em.find(MesaDeApoyo.class, oid);
    }

    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWhereadmin (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + AdminConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWheresuperadmin (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + SuperAdminConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWhereasesor (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + AsesorConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWhereareamedica (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + AreaMedicaConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWhereafiliaciones (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + AfiliacionesConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWhererespregional (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + RespRegionalConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWheregestor (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + GestorConstants.FLD_FUNCIONARIOID);
    }


    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param childAlias Alias of the child entity.
     */
    public void addFacetWheremesadeapoyo (String myAlias, SQLJoin join, String childAlias) {
        join.addWhere(myAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID + " = " + childAlias + "." + MesaDeApoyoConstants.FLD_FUNCIONARIOID);
    }


    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(Funcionario instance) throws SystemException {
        try {
            // COUNT (Rol) WHERE (Rol.Nombre = "AFILIACIONES")
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Funcionario, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (Funcionario instance) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (Funcionario instance) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(FuncionarioConstants.CLASS_NAME, FuncionarioConstants.ROLE_NAME_ROL, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Rol.Nombre = "AFILIACIONES"
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    comparison1.setFirstOperand(new SQLWhereOperandLiteral(instance.getRol().getRolNombre(), Constants.Type.STRING.getTypeName()));
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral("AFILIACIONES", Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Funcionario, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
