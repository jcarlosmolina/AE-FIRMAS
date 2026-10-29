package com.keralty.aefirmas.exception;

public class InstanceExistsException extends ModelException {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;
    
    public InstanceExistsException(String classAlias) {
    	super(ErrorInfo.EXISTS_INSTANCE, "Instancia de " + classAlias + " existente");
    }
}
