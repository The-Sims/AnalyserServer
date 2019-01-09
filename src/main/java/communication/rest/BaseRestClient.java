package communication.rest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import logger.LogLevel;
import logger.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class BaseRestClient {

    private final Gson gson = new Gson();

    public abstract String getBaseUr();

    public  <T> T executeQueryGet(String queryGet, Class<T> clazz) {

        // Build the query for the REST service
        final String query = getBaseUr() + queryGet;
        // Perform the query
        HttpGet httpGet = new HttpGet(query);

        return executeRequest(httpGet, clazz);

    }

    private <T> T executeRequest(HttpUriRequest request, Class<T> clazz)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request);) {

            HttpEntity entity = response.getEntity();
            String entityString = EntityUtils.toString(entity);
            entityString = entityString.replaceAll("\\\\", "").substring(1);
            entityString = entityString.substring(0, entityString.length()-1);
            Logger.getInstance().log(entityString, LogLevel.RECEIVEDMESSAGE);
            return gson.fromJson(entityString, clazz);
        } catch (IOException | JsonSyntaxException e) {
            Logger.getInstance().log(e);
            return null;
        }
    }

    public <T> T executeQueryPost(T request, String queryPost , Class<T> clazz) {

        // Build the query for the REST service
        final String query = getBaseUr() + queryPost;

        // Perform the query
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");

        StringEntity params;
        try {
            String json = gson.toJson(request);
            Logger.getInstance().log(json, LogLevel.DEBUG);
            params = new StringEntity(json);
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            Logger.getInstance().log(ex);
        }
        return executeRequest(httpPost, clazz);
    }

    public <T> T  executeQueryPut(T putRequest, String queryPut , Class<T> clazz) {

        // Build the query for the REST service
        final String query = getBaseUr() + queryPut;

        // Perform the query
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(putRequest));
            httpPut.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            Logger.getInstance().log(ex);
        }

        return executeRequest(httpPut, clazz);
    }

    public <T> T executeQueryDelete(String queryDelete, Class<T> clazz) {

        // Build the query for the REST service
        final String query = getBaseUr() + queryDelete;

        // Perform the query
        HttpDelete httpDelete = new HttpDelete(query);

        return executeRequest(httpDelete, clazz);
    }
}
