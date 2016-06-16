package br.com.ciandt.blocodenotas.app.integrator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;

import javax.net.ssl.HttpsURLConnection;

import br.com.ciandt.blocodenotas.app.entity.NetworkOperation;
import br.com.ciandt.blocodenotas.app.infraestructure.NetworkOperationResult;
import br.com.ciandt.blocodenotas.app.util.Constants;

/**
 * Created by wbatista on 6/1/16.
 */
public class BlocoDeNotasIntegrator {

    public NetworkOperationResult fetchData(final String url, final String contentType) {
        final URL endPoint;
        final HttpsURLConnection connection;
        NetworkOperationResult networkOperationResult = null;
        try {
            endPoint = new URL(url);
            connection = (HttpsURLConnection) endPoint.openConnection();
            // 16 segundos
            connection.setReadTimeout(Constants.Network.READ_TIMEOUT);
            // 25 seconds
            connection.setConnectTimeout(Constants.Network.CONNECT_TIMEOUT);
            connection.setRequestMethod(Constants.Network.METHOD_GET);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("X-Api-Token", Constants.Network.X_API_TOKEN);
            connection.connect();
            networkOperationResult = new NetworkOperationResult(connection.getResponseCode(),
                    connection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return networkOperationResult;
    }

    public NetworkOperationResult networkDataTransfer(final String url, final Object data, final String contentType, final NetworkOperation networkTaskOperation) {
        final URL endPoint;
        final HttpsURLConnection connection;
        NetworkOperationResult networkOperationResult = null;
        try {
            endPoint = new URL(url);
            connection = (HttpsURLConnection) endPoint.openConnection();
            // 16 segundos
            connection.setReadTimeout(10000);
            // 25 seconds
            connection.setConnectTimeout(15000);

            switch (networkTaskOperation) {
                case POST: {
                    // the header parameter must be in this order, other wise it will not work
                    connection.setRequestMethod("POST");
                } break;
                case PUT: {
                    // the header parameter must be in this order, other wise it will not work
                    connection.setRequestMethod("PUT");
                } break;
                default: {
                    throw new InvalidParameterException("The operation " + networkOperationResult + " is not available");
                }
            }

            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("X-Api-Token", Constants.Network.X_API_TOKEN);

            // send post. If this line was in another place it will not work
            connection.setDoOutput(true);

            if(!contentType.contains("json")) {
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write((byte[]) data);

                dataOutputStream.flush();
                dataOutputStream.close();
            } else {
                final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWriter.write(String.valueOf(data));
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }

            networkOperationResult = new NetworkOperationResult(connection.getResponseCode(),
                    connection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return networkOperationResult;
    }
}