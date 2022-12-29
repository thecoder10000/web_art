package dduwcom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Query_author extends AppCompatActivity {

    BookDBManager bookDBManager;
    ListView listView;
    ArrayAdapter<Book> adapter;
    ArrayList<Book> bookList = null;
    EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_author);

        etTitle = (EditText) findViewById(R.id.etTitle_author);
        listView = (ListView) findViewById(R.id.listView_author);
        bookList = new ArrayList<>();
        adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);
        bookDBManager = new BookDBManager(this);
    }

    public void onClick(View view){
        switch(view.getId()) {
            case R.id.button_author:
                String title = etTitle.getText().toString();
                bookList.clear();
                bookList.addAll(bookDBManager.getBookByAuthor(title));
                adapter.notifyDataSetChanged();
                break;
            case R.id.button_author2:
                finish();
                break;
        }
    }
}
