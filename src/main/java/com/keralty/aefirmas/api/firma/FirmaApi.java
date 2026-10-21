package com.keralty.aefirmas.api.firma;

import com.keralty.aefirmas.persistence.AbstractAppEntity;
import com.keralty.aefirmas.persistence.Firma;
import com.keralty.aefirmas.persistence.oid.FirmaOid;
import com.keralty.aefirmas.service.FirmaService;
import com.keralty.aefirmas.utils.TokenUtilities;
import com.keralty.aefirmas.viewmodel.firma.piu.PIUFirmaNullResponse;
import com.keralty.aefirmas.viewmodel.firma.piu.PIUFirmasRespRegionalRequest;
import com.keralty.aefirmas.viewmodel.firma.piu.PIUFirmasRespRegionalResponse;
import com.keralty.aefirmas.viewmodel.firma.siu.EliminarRequest;
import com.keralty.aefirmas.viewmodel.InstanceResponseViewModel;
import com.keralty.aefirmas.viewmodel.PopulationRequestViewModel;
import com.keralty.aefirmas.viewmodel.PopulationResponseViewModel;
import com.keralty.aefirmas.viewmodel.QueryRequest;
import com.keralty.aefirmas.viewmodel.ServiceResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/FirmaApi")
@RequestScoped
public class FirmaApi {

    FirmaService service;

    public FirmaApi(FirmaService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{firmaIdentificador}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Firma getInstance(Long firmaIdentificador) {
    	return service.getById(firmaIdentificador);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<Firma> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Firma getByOid(FirmaOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Firma getByOIDWithHV(FirmaOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetFirmaDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getFirmaDynamic(QueryRequest request) {
        FirmaOid oid = new FirmaOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getFirmaDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getFirmaPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse firmaEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/eliminarCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse firmaEliminarCheckPreconditions(EliminarRequest request) {

        return service.eliminarCheckPreconditions(request);
    }


    @POST
    @Path(value = "/PIU_FirmasRespRegional")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUFirmasRespRegionalResponse piuPIUFirmasRespRegional(PIUFirmasRespRegionalRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUFirmasRespRegionalResponse response;
        try
        {
            response = new PIUFirmasRespRegionalResponse(service.piuPIUFirmasRespRegional(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUFirmasRespRegional(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUFirmasRespRegionalResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_Firma_Null")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUFirmaNullResponse piuPIUFirmaNull(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUFirmaNullResponse response;
        try
        {
            response = new PIUFirmaNullResponse(service.piuPIUFirmaNull(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUFirmaNull(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUFirmaNullResponse();
            response.setException(e);
        }
        return response;
    }


    @GET
    @Path("buildRelatedInstance/{relatedInstanceJsonOid}/{relatedInstancePath}")
    @Operation(hidden = true)
    public AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePath) {
        return service.buildRelatedInstance(relatedInstanceJsonOid, relatedInstancePath);
    }
}
