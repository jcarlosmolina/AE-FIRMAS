package com.keralty.aefirmas.service;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.AesolicitudesConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.SolicitudConstants;
import com.keralty.aefirmas.global.UsuarioConstants;
import com.keralty.aefirmas.persistence.oid.UsuarioOid;
import com.keralty.aefirmas.persistence.Usuario;
import com.keralty.aefirmas.repository.UsuarioJpaRepository;
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
public class UsuarioService extends AbstractService {

    UsuarioJpaRepository repository;

    UsuarioService usuarioSrv;

    @Inject
    public UsuarioService(
            UsuarioService usuarioSrv,
            UsuarioJpaRepository repository) {
        this.usuarioSrv = usuarioSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of Usuario by ID
     *
     * @param aesolicitudesidsolicitud Identification field aesolicitudesidsolicitud
     *
     * @param usuarioIdentificador Identification field usuarioIdentificador
     *
     * @return Instance of Usuario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario getById(Long aesolicitudesidsolicitud, Long usuarioIdentificador) {
        return getByOID(new UsuarioOid(aesolicitudesidsolicitud, usuarioIdentificador));
    }
    
    /*
     * Returns an instance of Usuario by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Usuario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario getByOID(UsuarioOid oid) {
        Optional<Usuario> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Usuario by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Usuario getByOIDWithHV(UsuarioOid oid) {
        Optional<Usuario> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Usuario> getInstanceByOid(UsuarioOid oid) {
        return oid != null && oid.getAesolicitudesidsolicitud() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of Usuario by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Usuario
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario getByJsonOid(String jsonOid) {
        UsuarioOid oid = new UsuarioOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            UsuarioOid oid = new UsuarioOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAesolicitudesidsolicitud(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioConstants.ATTR_FIELD_USUARIOIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getUsuarioIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(UsuarioService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
