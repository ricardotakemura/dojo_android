package br.com.ciandt.blocodenotas.app.manager;

import android.os.AsyncTask;

import java.util.List;

import br.com.ciandt.blocodenotas.app.business.BlocoDeNotasBusiness;
import br.com.ciandt.blocodenotas.app.entity.Note;
import br.com.ciandt.blocodenotas.app.infraestructure.OperationListener;
import br.com.ciandt.blocodenotas.app.infraestructure.OperationResult;

/**
 * Created by wbatista on 6/1/16.
 */
public class BlocoDeNotasManager {

    private BlocoDeNotasBusiness mBlocoDeNotasBusiness;

    public BlocoDeNotasManager() {
        this.mBlocoDeNotasBusiness = new BlocoDeNotasBusiness();
    }

    public void saveNote(final Note note, final OperationListener<Note> operationListener) {
        final AsyncTask<Void, Void, OperationResult<Note>> task = new AsyncTask<Void, Void, OperationResult<Note>>() {
            @Override
            protected OperationResult<Note> doInBackground(Void... params) {

                final Note result = mBlocoDeNotasBusiness.save(note).getResult();
                return mBlocoDeNotasBusiness.save(note);
            }

            @Override
            protected void onPostExecute(OperationResult<Note> listOperationResult) {
                super.onPostExecute(listOperationResult);
                operationListener.onOperationSuccess(listOperationResult.getResult());
            }
        };
        task.execute();
    }

    public void updateNote(final Note note, final OperationListener<Boolean> operationListener) {
        final AsyncTask<Void, Void, OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... params) {
                return mBlocoDeNotasBusiness.update(note);
            }

            @Override
            protected void onPostExecute(OperationResult<Boolean> listOperationResult) {
                super.onPostExecute(listOperationResult);
                operationListener.onOperationSuccess(listOperationResult.getResult());
            }
        };
        task.execute();
    }

    public void getAllNotes(final OperationListener<List<Note>> operationListener) {
        final AsyncTask<Void, Void, OperationResult<List<Note>>> task = new AsyncTask<Void, Void, OperationResult<List<Note>>>() {
            @Override
            protected OperationResult<List<Note>> doInBackground(Void... params) {
                return mBlocoDeNotasBusiness.getAllNotes();
            }

            @Override
            protected void onPostExecute(OperationResult<List<Note>> listOperationResult) {
                super.onPostExecute(listOperationResult);
                operationListener.onOperationSuccess(listOperationResult.getResult());
            }
        };
        task.execute();
    }
}