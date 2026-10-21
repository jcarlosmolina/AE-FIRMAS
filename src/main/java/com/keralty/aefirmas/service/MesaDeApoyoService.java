package com.keralty.aefirmas.service;

import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.global.MesaDeApoyoConstants;
import com.keralty.aefirmas.persistence.MesaDeApoyo;
import com.keralty.aefirmas.persistence.oid.MesaDeApoyoOid;
import com.keralty.aefirmas.repository.MesaDeApoyoJpaRepository;
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
public class MesaDeApoyoService extends AbstractService {

    MesaDeApoyoJpaRepository repository;

    @Inject
    public MesaDeApoyoService(
            MesaDeApoyoJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of MesaDeApoyo by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of MesaDeApoyo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public MesaDeApoyo getById(Long funcionarioId) {
        return getByOID(new MesaDeApoyoOid(funcionarioId));
    }
    
    /*
     * Returns an instance of MesaDeApoyo by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of MesaDeApoyo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public MesaDeApoyo getByOID(MesaDeApoyoOid oid) {
        Optional<MesaDeApoyo> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of MesaDeApoyo by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of MesaDeApoyo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public MesaDeApoyo getByOIDWithHV(MesaDeApoyoOid oid) {
        Optional<MesaDeApoyo> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<MesaDeApoyo> getInstanceByOid(MesaDeApoyoOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of MesaDeApoyo by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of MesaDeApoyo
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public MesaDeApoyo getByJsonOid(String jsonOid) {
        MesaDeApoyoOid oid = new MesaDeApoyoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            MesaDeApoyoOid oid = new MesaDeApoyoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(MesaDeApoyoService.class).error(e.getMessage());
        }
    }

}
