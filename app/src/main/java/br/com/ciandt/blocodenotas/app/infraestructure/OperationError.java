package br.com.ciandt.blocodenotas.app.infraestructure;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wbatista on 6/1/16.
 */
public class OperationError implements Parcelable {
    /**
     * Error code
     */
    private String errorCode;

    /**
     * Error message
     */
    private String errorMessage;

    /**
     * Extra error data
     */
    private String errorData;

    /**
     * Default constructor
     */
    public OperationError() {
        // Nothing to do at this moment
    }

    public OperationError(String errorCode, String errorMessage, String errorData) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorData = errorData;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.errorCode);
        out.writeString(this.errorMessage);
        out.writeString(this.errorData);
    }

    public static final Parcelable.Creator<OperationError> CREATOR = new Parcelable.Creator<OperationError>() {
        public OperationError createFromParcel(Parcel in) {

            OperationError operationError = new OperationError();
            operationError.errorCode = in.readString();
            operationError.errorMessage = in.readString();
            operationError.errorData = in.readString();

            return operationError;
        }

        public OperationError[] newArray(int size) {
            return new OperationError[size];
        }
    };
}