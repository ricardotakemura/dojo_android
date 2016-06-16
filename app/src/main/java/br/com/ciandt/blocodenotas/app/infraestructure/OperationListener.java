package br.com.ciandt.blocodenotas.app.infraestructure;

import java.util.List;

/**
 * Created by wbatista on 6/1/16.
 */
public abstract class OperationListener<T> {

    public abstract void onOperationSuccess(T result);

    public void onOperationError(T result, List<OperationError> errors) {}

    public void onOperationCancelled() {}
}