package com.keralty.aefirmas.service;

import com.keralty.aefirmas.global.AesolicitudesConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.SolicitudConstants;
import com.keralty.aefirmas.persistence.oid.SolicitudOid;
import com.keralty.aefirmas.persistence.Solicitud;
import com.keralty.aefirmas.repository.SolicitudJpaRepository;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathAttribute;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class SolicitudService extends AbstractService {

    SolicitudJpaRepository repository;

    SolicitudService solicitudSrv;

    @Inject
    public SolicitudService(
            SolicitudService solicitudSrv,
            SolicitudJpaRepository repository) {
        this.solicitudSrv = solicitudSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of Solicitud by ID
     *
     * @param aesolicitudesidsolicitud Identification field aesolicitudesidsolicitud
     *
     * @return Instance of Solicitud
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Solicitud getById(Long aesolicitudesidsolicitud) {
        return getByOID(new SolicitudOid(aesolicitudesidsolicitud));
    }
    
    /*
     * Returns an instance of Solicitud by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Solicitud
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Solicitud getByOID(SolicitudOid oid) {
        Optional<Solicitud> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Solicitud by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Solicitud
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Solicitud getByOIDWithHV(SolicitudOid oid) {
        Optional<Solicitud> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Solicitud> getInstanceByOid(SolicitudOid oid) {
        return oid != null && oid.getAesolicitudesidsolicitud() != null
               ? repository.findById(oid.getAesolicitudesidsolicitud())
               : Optional.empty();
    }

    /*
     * Returns an instance of Solicitud by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Solicitud
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Solicitud getByJsonOid(String jsonOid) {
        SolicitudOid oid = new SolicitudOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            SolicitudOid oid = new SolicitudOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAesolicitudesidsolicitud(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(SolicitudService.class).error(e.getMessage());
        }
    }

}
