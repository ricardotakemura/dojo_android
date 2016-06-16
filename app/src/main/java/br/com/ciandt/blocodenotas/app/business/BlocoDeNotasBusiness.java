package br.com.ciandt.blocodenotas.app.business;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.ciandt.blocodenotas.app.entity.NetworkOperation;
import br.com.ciandt.blocodenotas.app.entity.Note;
import br.com.ciandt.blocodenotas.app.infraestructure.NetworkOperationResult;
import br.com.ciandt.blocodenotas.app.infraestructure.OperationResult;
import br.com.ciandt.blocodenotas.app.integrator.BlocoDeNotasIntegrator;
import br.com.ciandt.blocodenotas.app.util.BlocoDeNotasUtil;
import br.com.ciandt.blocodenotas.app.util.Constants;

/**
 * Created by wbatista on 6/1/16.
 */
public class BlocoDeNotasBusiness {

    private BlocoDeNotasIntegrator mBlocoDeNotasIntegrator;

    public BlocoDeNotasBusiness() {
        this.mBlocoDeNotasIntegrator = new BlocoDeNotasIntegrator();
    }

    public OperationResult<Note> save(final Note newNote) {
        final OperationResult<Note> result = new OperationResult<>();

        final Gson gson = new Gson();
        final NetworkOperationResult networkOperationResult =
                this.mBlocoDeNotasIntegrator.networkDataTransfer(
                        Constants.Network.ENDPOINT + Constants.Service.Note.BASE_NOTE_SERVICE_PATH,
                        gson.toJson(newNote), Constants.Network.CONTENT_TYPE_JSON, NetworkOperation.POST);

        try {
            final JSONObject jsonObject = new JSONObject(BlocoDeNotasUtil.convertStreamToString(networkOperationResult.getStream()));
            final JSONArray jsonArray = jsonObject.getJSONArray("data");

            final Note note =
                    gson.fromJson(jsonArray.getJSONObject(0).toString(), Note.class);
            result.setOperationCompletedSuccessfully(true);
            result.setResult(note);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public OperationResult<Boolean> update(final Note note) {
        return null;
    }

    public OperationResult<List<Note>> getAllNotes() {
        final OperationResult<List<Note>> result = new OperationResult<>();

        final NetworkOperationResult networkOperationResult =
                this.mBlocoDeNotasIntegrator.fetchData(
                        Constants.Network.ENDPOINT + Constants.Service.Note.BASE_NOTE_SERVICE_PATH,
                        Constants.Network.CONTENT_TYPE_JSON);

        final Gson gson = new Gson();
        final List<Note> notes = new ArrayList<>();
        try {
            final JSONObject jsonObject = new JSONObject(BlocoDeNotasUtil.convertStreamToString(networkOperationResult.getStream()));
            final JSONArray jsonArray = jsonObject.getJSONArray("data");
            final int length = jsonArray.length();

            for (int i = 0; i < length; i++) {
                final Note note =
                        gson.fromJson(jsonArray.getJSONObject(i).toString(), Note.class);
                notes.add(note);
            }

            result.setOperationCompletedSuccessfully(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        result.setResult(notes);
        return result;
    }
}