package tn.esprit.autoloc.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String entityName, Object id) {
        super(entityName + " introuvable : " + id);
    }
}
