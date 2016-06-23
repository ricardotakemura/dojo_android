package br.com.ciandt.blocodenotas.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ciandt.blocodenotas.app.R;
import br.com.ciandt.blocodenotas.app.entity.Note;
import br.com.ciandt.blocodenotas.app.infraestructure.OperationListener;
import br.com.ciandt.blocodenotas.app.manager.BlocoDeNotasManager;

public class BlocoDeNotasActivity extends AppCompatActivity {

    private BlocoDeNotasManager mBlocoDeNotasManager;

    private Button mAddNoteButton;

    private EditText mEditTextNote;

    private ListView mListViewNotes;

    private ArrayAdapter<Note> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mBlocoDeNotasManager = new BlocoDeNotasManager();
        this.mAddNoteButton = (Button) findViewById(R.id.button_add_note);
        this.mEditTextNote = (EditText) findViewById(R.id.edit_text_note);
        this.mListViewNotes = (ListView) findViewById(R.id.list_view_notes);
        mAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<Note>());
        mListViewNotes.setAdapter(mAdapter);
        setListeners();
    }

    private void setListeners() {
        this.mAddNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Note note = new Note();
                note.setNote(mEditTextNote.getText().toString());
                mBlocoDeNotasManager.saveNote(note, new OperationListener<Note>() {
                    @Override
                    public void onOperationSuccess(Note result) {
                        if (result != null) {
                            mAdapter.add(result);
                            mEditTextNote.setText("");
                            Toast.makeText(BlocoDeNotasActivity.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BlocoDeNotasActivity.this, "It was not possible to save your note", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        this.mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(BlocoDeNotasActivity.this, "Note : [" + mAdapter.getItem(position).getNote() + "] clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BlocoDeNotasActivity.this, NoteDetailActivity.class);
                intent.putExtra("note", mAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    private void loadNotes() {
        this.mBlocoDeNotasManager.getAllNotes(new OperationListener<List<Note>>() {
            @Override
            public void onOperationSuccess(List<Note> result) {
                mAdapter.clear();
                mAdapter.addAll(result);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sync: {
                loadNotes();
            }
            break;
            default: {
                throw new UnsupportedOperationException("Menu not available");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.mAdapter != null) {
            this.mAdapter.clear();
            this.mAdapter = null;
        }

        this.mListViewNotes = null;
        this.mAddNoteButton = null;
        this.mEditTextNote = null;
        this.mBlocoDeNotasManager = null;
    }
}