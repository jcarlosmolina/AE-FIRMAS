package com.keralty.aefirmas.global;

import com.keralty.aefirmas.persistence.AreaMedica;
import com.keralty.aefirmas.persistence.Asesor;
import com.keralty.aefirmas.persistence.CuestionarioMed;
import com.keralty.aefirmas.persistence.Departamento;
import com.keralty.aefirmas.persistence.Direccion;
import com.keralty.aefirmas.persistence.Eps;
import com.keralty.aefirmas.persistence.EstadoBeneficiario;
import com.keralty.aefirmas.persistence.EstadoCivil;
import com.keralty.aefirmas.persistence.EstadoContrato;
import com.keralty.aefirmas.persistence.EstadoPersona;
import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.persistence.GrupoAsociado;
import com.keralty.aefirmas.persistence.Municipio;
import com.keralty.aefirmas.persistence.Pais;
import com.keralty.aefirmas.persistence.Parentesco;
import com.keralty.aefirmas.persistence.Periodicidad;
import com.keralty.aefirmas.persistence.Planes;
import com.keralty.aefirmas.persistence.PlantillaEmail;
import com.keralty.aefirmas.persistence.Productos;
import com.keralty.aefirmas.persistence.Rol;
import com.keralty.aefirmas.persistence.Solicitud;
import com.keralty.aefirmas.persistence.SolicitudNovedad;
import com.keralty.aefirmas.persistence.TipoDireccion;
import com.keralty.aefirmas.persistence.TipoIdentificacion;
import com.keralty.aefirmas.viewmodel.userfunctions.FuAnadirTextoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuBuscarDocGestorNumIdRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuBuscarDocGestorSolicitudRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCalcularIMCRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCalcularIMCUsuarioRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCallSPgetsemaforoareamedRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCallSPgetsemaforograbadornovRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCallSPgetsemaforograbadorRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCallSPgetusuareamedRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCallSPgetusugrabadornovRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCallSPgetusugrabadorRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuCheckRegimenSubsidiadoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuconcatenarConNulosRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuContieneCharEspecialRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuContratoExentoDocSolRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuEnviarEmailDevDocRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuEnviarEmailRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuEnviarSMSRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuEstadoSolicitudRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuExisteContratoExentoFIngresoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuformarListaValoresPreguntaRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuformarMsgErrorUsrSinDocsRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuFormarTextoObservacionesRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGenerarReporteOracleRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetAgenteRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetAreaMedicaPorIdRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetAsesorRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetCodigoPersonaCORERequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetCodTarifaProdPlanAnyoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetDepartamentoCLRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetDirResidenciaPersonaRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetEstadoBeneficiarioCOREPorNombreRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetEstadoBeneficiarioCORERequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetEstadoCivilRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetEstadoContratoPorNombreRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetEstadoContratoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetEstadoPersonaRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetFechaFinVigenciaUsuarioRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetFInicioPlanVolRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetFuncionarioPorIdRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetFuncionarioPorLDAPRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetGrupoAsocRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetIdDocIdentidadEnGestorRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetListaErroresRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetMunicipioCLRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetNombreAsesorRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetNombreFuncionarioRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetPaisRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetPlanPorCLRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetPlantillaEmailRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetPrioridadRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetProductoPorCLRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetReporteSolicitudRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetRolRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetSolicitudByNumeroRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetSolicitudNovedadByNumRadicadoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuGetTextoAvisoFirmaRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetTipoIdentificacionRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetValorParametroConfRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FugetValorTarifaSegunEdadRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuObtenerDocumentoRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuSoloLetrasRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuSoloNumerosRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuStrONullRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FusubstringTextRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuSustituirParamsEnURLRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuValidarDominioIncompatiblesRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuValidarEmailRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuValidarNombreRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuValidarTelefonoCelularRequest;
import com.keralty.aefirmas.viewmodel.userfunctions.FuValidarTelefonoFijoRequest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * User functions proxy
 */
@Path("/api/UserFunctionsApi")
@RegisterRestClient(configKey="ae-apigateway")
@ClientHeaderParam(name = "Authorization", value = "Bearer {com.keralty.aefirmas.utils.TokenUtilities.getToken4aeapigateway}")
public interface UserFunctionsProxy {

    /** 
     * Method that solves the fuformarListaValoresPregunta user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_formarListaValoresPregunta")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuformarListaValoresPreguntaFun(FuformarListaValoresPreguntaRequest request);

    /** 
     * Method that solves the fuconcatenarConNulos user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_concatenarConNulos")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuconcatenarConNulosFun(FuconcatenarConNulosRequest request);

    /** 
     * Method that solves the fugetTipoDirResidencial user function.
     *
     * @return Return TipoDireccion value of the User Function
     */
    @POST
    @Path(value = "/fu_getTipoDirResidencial")
    public TipoDireccion fugetTipoDirResidencialFun();

    /** 
     * Method that solves the fugetTipoDirCorrespondencia user function.
     *
     * @return Return TipoDireccion value of the User Function
     */
    @POST
    @Path(value = "/fu_getTipoDirCorrespondencia")
    public TipoDireccion fugetTipoDirCorrespondenciaFun();

    /** 
     * Method that solves the fuValidarEmail user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_ValidarEmail")
    public java.lang.Boolean fuValidarEmailFun(FuValidarEmailRequest request);

    /** 
     * Method that solves the fuValidarNombre user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_ValidarNombre")
    public java.lang.Boolean fuValidarNombreFun(FuValidarNombreRequest request);

    /** 
     * Method that solves the fuSoloNumeros user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_SoloNumeros")
    public java.lang.Boolean fuSoloNumerosFun(FuSoloNumerosRequest request);

    /** 
     * Method that solves the fuContieneCharEspecial user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_ContieneCharEspecial")
    public java.lang.Boolean fuContieneCharEspecialFun(FuContieneCharEspecialRequest request);

    /** 
     * Method that solves the fugetTipoIdentificacion user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return TipoIdentificacion value of the User Function
     */
    @POST
    @Path(value = "/fu_getTipoIdentificacion")
    public TipoIdentificacion fugetTipoIdentificacionFun(FugetTipoIdentificacionRequest request);

    /** 
     * Method that solves the fugetEstadoCivil user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return EstadoCivil value of the User Function
     */
    @POST
    @Path(value = "/fu_getEstadoCivil")
    public EstadoCivil fugetEstadoCivilFun(FugetEstadoCivilRequest request);

    /** 
     * Method that solves the fugetEstadoPersona user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return EstadoPersona value of the User Function
     */
    @POST
    @Path(value = "/fu_getEstadoPersona")
    public EstadoPersona fugetEstadoPersonaFun(FugetEstadoPersonaRequest request);

    /** 
     * Method that solves the fugetPais user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Pais value of the User Function
     */
    @POST
    @Path(value = "/fu_getPais")
    public Pais fugetPaisFun(FugetPaisRequest request);

    /** 
     * Method that solves the fuValidarTelefonoFijo user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_ValidarTelefonoFijo")
    public java.lang.Boolean fuValidarTelefonoFijoFun(FuValidarTelefonoFijoRequest request);

    /** 
     * Method that solves the fuValidarTelefonoCelular user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_ValidarTelefonoCelular")
    public java.lang.Boolean fuValidarTelefonoCelularFun(FuValidarTelefonoCelularRequest request);

    /** 
     * Method that solves the fuStrONull user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_StrONull")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuStrONullFun(FuStrONullRequest request);

    /** 
     * Method that solves the fugetEPSSanitas user function.
     *
     * @return Return Eps value of the User Function
     */
    @POST
    @Path(value = "/fu_getEPSSanitas")
    public Eps fugetEPSSanitasFun();

    /** 
     * Method that solves the fugetParentescoTitular user function.
     *
     * @return Return Parentesco value of the User Function
     */
    @POST
    @Path(value = "/fu_getParentescoTitular")
    public Parentesco fugetParentescoTitularFun();

    /** 
     * Method that solves the fugetCuestionarioMedico user function.
     *
     * @return Return CuestionarioMed value of the User Function
     */
    @POST
    @Path(value = "/fu_getCuestionarioMedico")
    public CuestionarioMed fugetCuestionarioMedicoFun();

    /** 
     * Method that solves the fugetValorParametroConf user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_getValorParametroConf")
    @Produces(MediaType.TEXT_PLAIN)
    public String fugetValorParametroConfFun(FugetValorParametroConfRequest request);

    /** 
     * Method that solves the fugetRol user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Rol value of the User Function
     */
    @POST
    @Path(value = "/fu_getRol")
    public Rol fugetRolFun(FugetRolRequest request);

    /** 
     * Method that solves the fusubstringText user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_substringText")
    @Produces(MediaType.TEXT_PLAIN)
    public String fusubstringTextFun(FusubstringTextRequest request);

    /** 
     * Method that solves the fugetValorTarifaSegunEdad user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Double value of the User Function
     */
    @POST
    @Path(value = "/fu_getValorTarifaSegunEdad")
    public java.lang.Double fugetValorTarifaSegunEdadFun(FugetValorTarifaSegunEdadRequest request);

    /** 
     * Method that solves the fugetGrupoAsoc user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return GrupoAsociado value of the User Function
     */
    @POST
    @Path(value = "/fu_getGrupoAsoc")
    public GrupoAsociado fugetGrupoAsocFun(FugetGrupoAsocRequest request);

    /** 
     * Method that solves the fugetEstadoBeneficiarioCORE user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return EstadoBeneficiario value of the User Function
     */
    @POST
    @Path(value = "/fu_getEstadoBeneficiarioCORE")
    public EstadoBeneficiario fugetEstadoBeneficiarioCOREFun(FugetEstadoBeneficiarioCORERequest request);

    /** 
     * Method that solves the fuObtenerDocumento user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return byte[] value of the User Function
     */
    @POST
    @Path(value = "/fu_ObtenerDocumento")
    public byte[] fuObtenerDocumentoFun(FuObtenerDocumentoRequest request);

    /** 
     * Method that solves the fugetPeriodicidadMensual user function.
     *
     * @return Return Periodicidad value of the User Function
     */
    @POST
    @Path(value = "/fu_getPeriodicidadMensual")
    public Periodicidad fugetPeriodicidadMensualFun();

    /** 
     * Method that solves the fugetPlantillaEmail user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return PlantillaEmail value of the User Function
     */
    @POST
    @Path(value = "/fu_getPlantillaEmail")
    public PlantillaEmail fugetPlantillaEmailFun(FugetPlantillaEmailRequest request);

    /** 
     * Method that solves the fuSustituirParamsEnURL user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_SustituirParamsEnURL")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuSustituirParamsEnURLFun(FuSustituirParamsEnURLRequest request);

    /** 
     * Method that solves the fuEnviarEmail user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_EnviarEmail")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuEnviarEmailFun(FuEnviarEmailRequest request);

    /** 
     * Method that solves the fuAnadirTexto user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_AnadirTexto")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuAnadirTextoFun(FuAnadirTextoRequest request);

    /** 
     * Method that solves the fugetEstadoContrato user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return EstadoContrato value of the User Function
     */
    @POST
    @Path(value = "/fu_getEstadoContrato")
    public EstadoContrato fugetEstadoContratoFun(FugetEstadoContratoRequest request);

    /** 
     * Method that solves the fugetDepartamentoCL user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Departamento value of the User Function
     */
    @POST
    @Path(value = "/fu_getDepartamentoCL")
    public Departamento fugetDepartamentoCLFun(FugetDepartamentoCLRequest request);

    /** 
     * Method that solves the fugetSolicitudByNumero user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Solicitud value of the User Function
     */
    @POST
    @Path(value = "/fu_getSolicitudByNumero")
    public Solicitud fugetSolicitudByNumeroFun(FugetSolicitudByNumeroRequest request);

    /** 
     * Method that solves the fugetAgente user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Funcionario value of the User Function
     */
    @POST
    @Path(value = "/fu_getAgente")
    public Funcionario fugetAgenteFun(FugetAgenteRequest request);

    /** 
     * Method that solves the fuBuscarDocGestorNumId user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_BuscarDocGestorNumId")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuBuscarDocGestorNumIdFun(FuBuscarDocGestorNumIdRequest request);

    /** 
     * Method that solves the fuBuscarDocGestorSolicitud user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_BuscarDocGestorSolicitud")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuBuscarDocGestorSolicitudFun(FuBuscarDocGestorSolicitudRequest request);

    /** 
     * Method that solves the fuSoloLetras user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_SoloLetras")
    public java.lang.Boolean fuSoloLetrasFun(FuSoloLetrasRequest request);

    /** 
     * Method that solves the fuGetTextoProteccionDatos user function.
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_GetTextoProteccionDatos")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetTextoProteccionDatosFun();

    /** 
     * Method that solves the fugetAsesor user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Asesor value of the User Function
     */
    @POST
    @Path(value = "/fu_getAsesor")
    public Asesor fugetAsesorFun(FugetAsesorRequest request);

    /** 
     * Method that solves the fugetMunicipioCL user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Municipio value of the User Function
     */
    @POST
    @Path(value = "/fu_getMunicipioCL")
    public Municipio fugetMunicipioCLFun(FugetMunicipioCLRequest request);

    /** 
     * Method that solves the fuGetDirResidenciaPersona user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Direccion value of the User Function
     */
    @POST
    @Path(value = "/fu_GetDirResidenciaPersona")
    public Direccion fuGetDirResidenciaPersonaFun(FuGetDirResidenciaPersonaRequest request);

    /** 
     * Method that solves the fuGetTextoAvisoFirma user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_GetTextoAvisoFirma")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetTextoAvisoFirmaFun(FuGetTextoAvisoFirmaRequest request);

    /** 
     * Method that solves the fugetListaErrores user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_getListaErrores")
    @Produces(MediaType.TEXT_PLAIN)
    public String fugetListaErroresFun(FugetListaErroresRequest request);

    /** 
     * Method that solves the fuGetCodTarifaProdPlanAnyo user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_GetCodTarifaProdPlanAnyo")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetCodTarifaProdPlanAnyoFun(FuGetCodTarifaProdPlanAnyoRequest request);

    /** 
     * Method that solves the fugetProductoPorCL user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Productos value of the User Function
     */
    @POST
    @Path(value = "/fu_getProductoPorCL")
    public Productos fugetProductoPorCLFun(FugetProductoPorCLRequest request);

    /** 
     * Method that solves the fugetPlanPorCL user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Planes value of the User Function
     */
    @POST
    @Path(value = "/fu_getPlanPorCL")
    public Planes fugetPlanPorCLFun(FugetPlanPorCLRequest request);

    /** 
     * Method that solves the fugetFuncionarioPorLDAP user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Funcionario value of the User Function
     */
    @POST
    @Path(value = "/fu_getFuncionarioPorLDAP")
    public Funcionario fugetFuncionarioPorLDAPFun(FugetFuncionarioPorLDAPRequest request);

    /** 
     * Method that solves the fuFormarTextoObservaciones user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fuFormarTextoObservaciones")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuFormarTextoObservacionesFun(FuFormarTextoObservacionesRequest request);

    /** 
     * Method that solves the fuGetCodigoPersonaCORE user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Long value of the User Function
     */
    @POST
    @Path(value = "/fuGetCodigoPersonaCORE")
    public java.lang.Long fuGetCodigoPersonaCOREFun(FuGetCodigoPersonaCORERequest request);

    /** 
     * Method that solves the fuGetFechaFinVigenciaUsuario user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.sql.Date value of the User Function
     */
    @POST
    @Path(value = "/fuGetFechaFinVigenciaUsuario")
    public java.sql.Date fuGetFechaFinVigenciaUsuarioFun(FuGetFechaFinVigenciaUsuarioRequest request);

    /** 
     * Method that solves the fuGetNombreAsesor user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fuGetNombreAsesor")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetNombreAsesorFun(FuGetNombreAsesorRequest request);

    /** 
     * Method that solves the fuGetNombreFuncionario user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fuGetNombreFuncionario")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetNombreFuncionarioFun(FuGetNombreFuncionarioRequest request);

    /** 
     * Method that solves the fuformarMsgErrorUsrSinDocs user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_formarMsgErrorUsrSinDocs")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuformarMsgErrorUsrSinDocsFun(FuformarMsgErrorUsrSinDocsRequest request);

    /** 
     * Method that solves the fuEnviarEmailDevDoc user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_EnviarEmailDevDoc")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuEnviarEmailDevDocFun(FuEnviarEmailDevDocRequest request);

    /** 
     * Method that solves the fuContratoExentoDocSol user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fu_ContratoExentoDocSol")
    public java.lang.Boolean fuContratoExentoDocSolFun(FuContratoExentoDocSolRequest request);

    /** 
     * Method that solves the fuEstadoSolicitud user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_EstadoSolicitud")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuEstadoSolicitudFun(FuEstadoSolicitudRequest request);

    /** 
     * Method that solves the fuGenerarReporteOracle user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return byte[] value of the User Function
     */
    @POST
    @Path(value = "/fuGenerarReporteOracle")
    public byte[] fuGenerarReporteOracleFun(FuGenerarReporteOracleRequest request);

    /** 
     * Method that solves the fuCalcularIMC user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Double value of the User Function
     */
    @POST
    @Path(value = "/fuCalcularIMC")
    public java.lang.Double fuCalcularIMCFun(FuCalcularIMCRequest request);

    /** 
     * Method that solves the fuGetEstadoPersonaError user function.
     *
     * @return Return EstadoBeneficiario value of the User Function
     */
    @POST
    @Path(value = "/fu_GetEstadoPersonaError")
    public EstadoBeneficiario fuGetEstadoPersonaErrorFun();

    /** 
     * Method that solves the fuCalcularIMCUsuario user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Double value of the User Function
     */
    @POST
    @Path(value = "/fuCalcularIMCUsuario")
    public java.lang.Double fuCalcularIMCUsuarioFun(FuCalcularIMCUsuarioRequest request);

    /** 
     * Method that solves the fuEnviarSMS user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fu_EnviarSMS")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuEnviarSMSFun(FuEnviarSMSRequest request);

    /** 
     * Method that solves the fugetFuncionarioPorId user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return Funcionario value of the User Function
     */
    @POST
    @Path(value = "/fu_getFuncionarioPorId")
    public Funcionario fugetFuncionarioPorIdFun(FugetFuncionarioPorIdRequest request);

    /** 
     * Method that solves the fuCallSPgetusugrabador user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Long value of the User Function
     */
    @POST
    @Path(value = "/fuCallSPgetusugrabador")
    public java.lang.Long fuCallSPgetusugrabadorFun(FuCallSPgetusugrabadorRequest request);

    /** 
     * Method that solves the fuCallSPgetusugrabadornov user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Long value of the User Function
     */
    @POST
    @Path(value = "/fuCallSPgetusugrabadornov")
    public java.lang.Long fuCallSPgetusugrabadornovFun(FuCallSPgetusugrabadornovRequest request);

    /** 
     * Method that solves the fuCallSPgetusuareamed user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Long value of the User Function
     */
    @POST
    @Path(value = "/fuCallSPgetusuareamed")
    public java.lang.Long fuCallSPgetusuareamedFun(FuCallSPgetusuareamedRequest request);

    /** 
     * Method that solves the fugetAreaMedicaPorId user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return AreaMedica value of the User Function
     */
    @POST
    @Path(value = "/fu_getAreaMedicaPorId")
    public AreaMedica fugetAreaMedicaPorIdFun(FugetAreaMedicaPorIdRequest request);

    /** 
     * Method that solves the fugetPrioridad user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Long value of the User Function
     */
    @POST
    @Path(value = "/fugetPrioridad")
    public java.lang.Long fugetPrioridadFun(FugetPrioridadRequest request);

    /** 
     * Method that solves the fuCallSPgetsemaforograbador user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Double value of the User Function
     */
    @POST
    @Path(value = "/fuCallSPgetsemaforograbador")
    public java.lang.Double fuCallSPgetsemaforograbadorFun(FuCallSPgetsemaforograbadorRequest request);

    /** 
     * Method that solves the fuCallSPgetsemaforograbadornov user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Double value of the User Function
     */
    @POST
    @Path(value = "/fuCallSPgetsemaforograbadornov")
    public java.lang.Double fuCallSPgetsemaforograbadornovFun(FuCallSPgetsemaforograbadornovRequest request);

    /** 
     * Method that solves the fuCallSPgetsemaforoareamed user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Double value of the User Function
     */
    @POST
    @Path(value = "/fuCallSPgetsemaforoareamed")
    public java.lang.Double fuCallSPgetsemaforoareamedFun(FuCallSPgetsemaforoareamedRequest request);

    /** 
     * Method that solves the fugetSolicitudNovedadByNumRadicado user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return SolicitudNovedad value of the User Function
     */
    @POST
    @Path(value = "/fu_getSolicitudNovedadByNumRadicado")
    public SolicitudNovedad fugetSolicitudNovedadByNumRadicadoFun(FugetSolicitudNovedadByNumRadicadoRequest request);

    /** 
     * Method that solves the fuGetFechaDesdeVarFiltro user function.
     *
     * @return Return java.sql.Date value of the User Function
     */
    @POST
    @Path(value = "/fuGetFechaDesdeVarFiltro")
    public java.sql.Date fuGetFechaDesdeVarFiltroFun();

    /** 
     * Method that solves the fuCheckRegimenSubsidiado user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Long value of the User Function
     */
    @POST
    @Path(value = "/fuCheckRegimenSubsidiado")
    public java.lang.Long fuCheckRegimenSubsidiadoFun(FuCheckRegimenSubsidiadoRequest request);

    /** 
     * Method that solves the fuGetFInicioPlanVol user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.sql.Date value of the User Function
     */
    @POST
    @Path(value = "/fuGetFInicioPlanVol")
    public java.sql.Date fuGetFInicioPlanVolFun(FuGetFInicioPlanVolRequest request);

    /** 
     * Method that solves the fuValidarDominioIncompatibles user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fuValidarDominioIncompatibles")
    public java.lang.Boolean fuValidarDominioIncompatiblesFun(FuValidarDominioIncompatiblesRequest request);

    /** 
     * Method that solves the fuExisteContratoExentoFIngreso user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return java.lang.Boolean value of the User Function
     */
    @POST
    @Path(value = "/fuExisteContratoExentoFIngreso")
    public java.lang.Boolean fuExisteContratoExentoFIngresoFun(FuExisteContratoExentoFIngresoRequest request);

    /** 
     * Method that solves the fuGetFechaLimiteDescargaDoc user function.
     *
     * @return Return java.sql.Date value of the User Function
     */
    @POST
    @Path(value = "/fuGetFechaLimiteDescargaDoc")
    public java.sql.Date fuGetFechaLimiteDescargaDocFun();

    /** 
     * Method that solves the fuGetTkn user function.
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fuGetTkn")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetTknFun();

    /** 
     * Method that solves the fuGetReporteSolicitud user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return byte[] value of the User Function
     */
    @POST
    @Path(value = "/fuGetReporteSolicitud")
    public byte[] fuGetReporteSolicitudFun(FuGetReporteSolicitudRequest request);

    /** 
     * Method that solves the fugetEstadoBeneficiarioCOREPorNombre user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return EstadoBeneficiario value of the User Function
     */
    @POST
    @Path(value = "/fu_getEstadoBeneficiarioCOREPorNombre")
    public EstadoBeneficiario fugetEstadoBeneficiarioCOREPorNombreFun(FugetEstadoBeneficiarioCOREPorNombreRequest request);

    /** 
     * Method that solves the fugetEstadoContratoPorNombre user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return EstadoContrato value of the User Function
     */
    @POST
    @Path(value = "/fu_getEstadoContratoPorNombre")
    public EstadoContrato fugetEstadoContratoPorNombreFun(FugetEstadoContratoPorNombreRequest request);

    /** 
     * Method that solves the fuGetIdDocIdentidadEnGestor user function.
     *
     * @param request Payload to invoke the REST API of the User Function
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fuGetIdDocIdentidadEnGestor")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetIdDocIdentidadEnGestorFun(FuGetIdDocIdentidadEnGestorRequest request);

    /** 
     * Method that solves the fuGetIURLDescargaATOMIC user function.
     *
     * @return Return String value of the User Function
     */
    @POST
    @Path(value = "/fuGetIURLDescargaATOMIC")
    @Produces(MediaType.TEXT_PLAIN)
    public String fuGetIURLDescargaATOMICFun();
}
