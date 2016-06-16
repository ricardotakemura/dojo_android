package br.com.ciandt.blocodenotas.app.infraestructure;

import java.io.InputStream;

/**
 * Created by wbatista on 6/1/16.
 */
public class NetworkOperationResult {

    private int mReponseCode;

    private InputStream mStream;

    public NetworkOperationResult(final int responseCode, final InputStream stream) {
        this.mReponseCode = responseCode;
        this.mStream = stream;
    }

    public int getResponseCode() {
        return this.mReponseCode;
    }

    public void setResponseCode(int responseCode) {
        this.mReponseCode = responseCode;
    }

    public InputStream getStream() {
        return this.mStream;
    }

    public void setStream(InputStream mStream) {
        this.mStream = mStream;
    }
}