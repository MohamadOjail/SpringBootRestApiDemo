package online.ojail.restdemo.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String message;

    public ResourceNotFoundException(Class resource) {
        String name = resource.getSimpleName();
        this.message = String.format("Resource: {%s} Not Found", name);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
