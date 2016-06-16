package br.com.ciandt.blocodenotas.app.util;

/**
 * Created by wbatista on 6/1/16.
 */
public final class Constants {

    private Constants() {
        // This is an util class and must never be instantiated
    }

    public final class Network {

        public static final String CONTENT_TYPE_JSON = "application/json";

        public static final String ENDPOINT = "https://www.obackend.com/api/1.0/";
        public static final String X_API_TOKEN = "d629a086ecd841fd9eab82500ea3a194";
        public static final int READ_TIMEOUT = 10000;
        public static final int CONNECT_TIMEOUT = 15000;

        public static final String METHOD_GET = "GET";
        public static final String METHOD_POST = "POST";
        public static final String METHOD_PUT = "PUT";
    }

    public final class Service {
        public final class Note {
            public static final String BASE_NOTE_SERVICE_PATH = "note";
        }
    }
}