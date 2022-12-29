package dduwcom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Query_publisher extends AppCompatActivity {

    BookDBManager bookDBManager;
    ListView listView;
    ArrayAdapter<Book> adapter;
    ArrayList<Book> bookList = null;
    EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_publisher);

        etTitle = (EditText) findViewById(R.id.etTitle_publisher);
        listView = (ListView) findViewById(R.id.listView_publisher);
        bookList = new ArrayList<>();
        adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);
        bookDBManager = new BookDBManager(this);
    }

    public void onClick(View view){
        switch(view.getId()) {
            case R.id.button_publisher:
                String title = etTitle.getText().toString();
                bookList.clear();
                bookList.addAll(bookDBManager.getBookByPublisher(title));
                adapter.notifyDataSetChanged();
                break;
            case R.id.button_publisher2:
                finish();
                break;
        }
    }
}
