package app.menu.menutechnologiestestapp.data;

public class ApiResponseObject {

    private final Status status;
    private final Object object;
    private final String error;

    private ApiResponseObject(Status status, Object object, String error) {
        this.status = status;
        this.object = object;
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }

    public String getError() {
        return error;
    }

    public static ApiResponseObject loading() {
        return new ApiResponseObject(Status.LOADING, null, null);
    }

    public static ApiResponseObject success(Object object) {
        return new ApiResponseObject(Status.SUCCESS, object, null);
    }

    public static ApiResponseObject error(String error) {
        return new ApiResponseObject(Status.ERROR, null, error);
    }
}
