package br.edu.materdei.tas.core.exception;

/**
 *
 * @author brunofontana
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Integer resourceId) {
        super(resourceId != null ? resourceId.toString() : null);
    }
    
}
