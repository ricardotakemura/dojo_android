package br.com.ciandt.blocodenotas.app.infraestructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wbatista on 1/14/16.
 */
public class OperationResult<TResult> {

    /**
     * Indice de sucesso da operacao.
     */
    private boolean operationCompletedSuccessfully;
    /**
     * Lista de erros ocorridos na operacao.
     */
    private List<OperationError> operationErrors;
    /**
     * resultado obtido na operacao.
     */
    private TResult result;

    /**
     * Construtor padrao da entidade Operation Result.
     */
    public OperationResult() {
        this.operationErrors = new ArrayList<>();
    }

    public boolean isOperationCompletedSuccessfully() {
        return operationCompletedSuccessfully;
    }
    public void setOperationCompletedSuccessfully(boolean operationCompletedSuccessfully) {
        this.operationCompletedSuccessfully = operationCompletedSuccessfully;
    }
    public List<OperationError> getOperationErrors() {
        return operationErrors;
    }
    public void setOperationErrors(List<OperationError> operationErrors) {
        this.operationErrors = operationErrors;
    }
    public TResult getResult() {
        return result;
    }
    public void setResult(TResult result) {
        this.result = result;
    }
}