package dduwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    BookDBManager bookDBManager;
    EditText etTitle;
    EditText etAuthor;
    EditText etPublisher;
    EditText etSummary;
    EditText etPrice;

    String title;
    String author;
    String publisher;
    String summary;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bookDBManager = new BookDBManager(this);

        etTitle = findViewById(R.id.etTitle_add);
        etAuthor = findViewById(R.id.etAuthor_add);
        etPublisher = findViewById(R.id.etPublisher_add);
        etSummary = findViewById(R.id.etSummary_add);
        etPrice = findViewById(R.id.etPrice_add);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.confirm_add:
                title = etTitle.getText().toString();
                author = etAuthor.getText().toString();
                publisher = etPublisher.getText().toString();
                summary = etSummary.getText().toString();
                price = etPrice.getText().toString();

                if(title.equals("") || author.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    builder.setTitle("안내사항")
                            .setMessage("책 제목과 저자는 필수 항목입니다.")
                            .setCancelable(false)
                            .setPositiveButton("확인", null)
                            .show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("title_add", title);

                    boolean result = bookDBManager.addNewBook(new Book(title, author, publisher, summary, price));
                    if (result)
                        setResult(RESULT_OK, intent);
                    else
                        setResult(RESULT_CANCELED);
                    finish();
                }
                break;
            case R.id.cancel_add:
                finish();
                break;
        }
    }
}