package br.com.ciandt.blocodenotas.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ciandt.blocodenotas.app.R;
import br.com.ciandt.blocodenotas.app.entity.Note;
import br.com.ciandt.blocodenotas.app.infraestructure.OperationListener;
import br.com.ciandt.blocodenotas.app.manager.BlocoDeNotasManager;

public class NoteDetailActivity extends AppCompatActivity {

    private BlocoDeNotasManager mBlocoDeNotasManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBlocoDeNotasManager = new BlocoDeNotasManager();
        setContentView(R.layout.activity_note_detail);
        final Note note = (Note) getIntent().getSerializableExtra("note");

        final EditText noteText = (EditText) findViewById(R.id.noteText);
        noteText.setText(note.getNote());

        Button saveButton = (Button) findViewById(R.id.saveNote);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setNote(noteText.getText().toString());
                mBlocoDeNotasManager.updateNote(note, new OperationListener<Boolean>() {
                    @Override
                    public void onOperationSuccess(Boolean result) {
                        Toast.makeText(NoteDetailActivity.this, "Nota salva com sucesso!", Toast.LENGTH_LONG);
                        //NoteDetailActivity.this.finish();
                    }
                });
            }
        });
    }
}