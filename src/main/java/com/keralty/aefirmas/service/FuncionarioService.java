package com.keralty.aefirmas.service;

import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.persistence.oid.FuncionarioOid;
import com.keralty.aefirmas.repository.FuncionarioJpaRepository;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathAttribute;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class FuncionarioService extends AbstractService {

    FuncionarioJpaRepository repository;

    @Inject
    public FuncionarioService(
            FuncionarioJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of Funcionario by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of Funcionario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Funcionario getById(Long funcionarioId) {
        return getByOID(new FuncionarioOid(funcionarioId));
    }
    
    /*
     * Returns an instance of Funcionario by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Funcionario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Funcionario getByOID(FuncionarioOid oid) {
        Optional<Funcionario> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Funcionario by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Funcionario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Funcionario getByOIDWithHV(FuncionarioOid oid) {
        Optional<Funcionario> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Funcionario> getInstanceByOid(FuncionarioOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of Funcionario by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Funcionario
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Funcionario getByJsonOid(String jsonOid) {
        FuncionarioOid oid = new FuncionarioOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            FuncionarioOid oid = new FuncionarioOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(FuncionarioService.class).error(e.getMessage());
        }
    }

}
