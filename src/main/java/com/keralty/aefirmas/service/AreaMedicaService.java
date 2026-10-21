package com.keralty.aefirmas.service;

import com.keralty.aefirmas.global.AreaMedicaConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.persistence.AreaMedica;
import com.keralty.aefirmas.persistence.oid.AreaMedicaOid;
import com.keralty.aefirmas.repository.AreaMedicaJpaRepository;
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
public class AreaMedicaService extends AbstractService {

    AreaMedicaJpaRepository repository;

    @Inject
    public AreaMedicaService(
            AreaMedicaJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of AreaMedica by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of AreaMedica
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public AreaMedica getById(Long funcionarioId) {
        return getByOID(new AreaMedicaOid(funcionarioId));
    }
    
    /*
     * Returns an instance of AreaMedica by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of AreaMedica
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public AreaMedica getByOID(AreaMedicaOid oid) {
        Optional<AreaMedica> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of AreaMedica by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of AreaMedica
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public AreaMedica getByOIDWithHV(AreaMedicaOid oid) {
        Optional<AreaMedica> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<AreaMedica> getInstanceByOid(AreaMedicaOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of AreaMedica by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of AreaMedica
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public AreaMedica getByJsonOid(String jsonOid) {
        AreaMedicaOid oid = new AreaMedicaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AreaMedicaOid oid = new AreaMedicaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(AreaMedicaService.class).error(e.getMessage());
        }
    }

}
