package app.menu.menutechnologiestestapp.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Optional;

import retrofit2.Response;

public class ErrorObject {

    private final String body;

    public ErrorObject(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public static String getErrorMessage(Response<?> response) {
        String string;
        ErrorObject errorObject = null;
        try {
            if (response.errorBody() == null) throw new AssertionError();
            string = Optional.of(response.errorBody().string()).get();
            if (string.equals(""))
                return "error";
            JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
            JsonObject errorMessage = jsonObject.get("data").getAsJsonObject().get("info_message").getAsJsonObject();
            Gson gson = new Gson();
            errorObject = gson.fromJson(errorMessage, ErrorObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorObject != null ? errorObject.getBody() : "error";
    }
}
