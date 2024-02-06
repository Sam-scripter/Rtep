package com.example.rtep2.Sign_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rtep2.APIConstants;

import com.example.rtep2.Overview;
import com.example.rtep2.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class login extends AppCompatActivity {

    static String base_url = APIConstants.BASE_URL;

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b = findViewById(R.id.BtnLogin);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginPSN = findViewById(R.id.login_public_service_no);
                EditText loginPassword = findViewById(R.id.login_password);

                String publicServiceNumber = loginPSN.getText().toString();
                String password = loginPassword.getText().toString();

                // Execute network request in AsyncTask
                new LoginAsyncTask().execute(publicServiceNumber, password);
            }
        });
    }

    private void redirectToOverview(String accessToken, String role, boolean isSuperUser, String designation) {
        Intent intent = new Intent(login.this, Overview.class);
        intent.putExtra("access_token", accessToken);
        intent.putExtra("role", role);
        intent.putExtra("is_superuser", isSuperUser);
        intent.putExtra("designation", designation);
        startActivity(intent);
        finish(); // Optional: Finish the login activity
    }

    class LoginAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String publicServiceNumber = params[0];
            String password = params[1];

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("public_service_no", publicServiceNumber)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(base_url + "login/")
                    .post(requestBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JsonObject jsonObject = extractData(responseBody);
                    Log.d("response:", responseBody);
                    if (jsonObject != null && jsonObject.has("access")) {
                        JsonElement accessTokenElement = jsonObject.get("access");
                        return accessTokenElement.getAsString();
                    }
                } else {
                    Log.d("response:", "Failed to log in " + response.code() + " " + response.message());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String accessToken) {
            if (accessToken != null) {
                // Fetch additional details using the access token
                new FetchEmployeeDetailsAsyncTask().execute(accessToken);
            }
        }

        private JsonObject extractData(String responseBody) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(responseBody);

            if (jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject();
            }

            Log.d("Failure1", "Failed to extract JSON object from response");
            return null; // Return null if the data is not found or JSON parsing fails
        }


    }

    class FetchEmployeeDetailsAsyncTask extends AsyncTask<String, Void, JsonObject> {

        @Override
        protected JsonObject doInBackground(String... params) {
            String accessToken = params[0];

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(base_url + "employee/")
                    .header("Authorization", "Bearer " + accessToken)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JsonObject jsonObject = extractData(responseBody);
                    jsonObject.addProperty("access_token", accessToken);
                    Log.d("employee_response:", responseBody);
                    return jsonObject;

                } else {
                    Log.d("employee_response:", "Failed to fetch employee details " + response.code() + " " + response.message());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JsonObject jsonObject) {
            if (jsonObject != null) {
                String role = null;
                String designation = null;
                boolean isSuperUser = false;
                String accessToken = null;

                if (jsonObject.has("role") && !jsonObject.get("role").isJsonNull()) {
                    role = jsonObject.get("role").getAsString();
                    Log.d("role", role);
                }

                if (jsonObject.has("designation") && !jsonObject.get("designation").isJsonNull()) {
                    designation = jsonObject.get("designation").getAsString();
                    Log.d("designation", designation);
                }

                if (jsonObject.has("is_superuser") && !jsonObject.get("is_superuser").isJsonNull()) {
                    isSuperUser = jsonObject.get("is_superuser").getAsBoolean();
                    Log.d("Superuser", String.valueOf(isSuperUser));
                }

                if (jsonObject.has("access_token") && !jsonObject.get("access_token").isJsonNull()) {
                    accessToken = jsonObject.get("access_token").getAsString();
                    Log.d("accessToken", accessToken);
                }

                redirectToOverview(accessToken, role, isSuperUser, designation);
            }
        }

        private JsonObject extractData(String responseBody) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(responseBody);

            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                if (jsonArray.size() > 0 && jsonArray.get(0).isJsonObject()) {
                    return jsonArray.get(0).getAsJsonObject();
                }
            }

            Log.d("Failure2", "Failed to extract JSON object from response");
            return null; // Return null if the data is not found or JSON parsing fails
        }
    }
}

