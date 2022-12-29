package dduwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    BookDBManager bookDBManager;
    Book sentBook;

    ImageView imageView;
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
        setContentView(R.layout.activity_update);
        bookDBManager = new BookDBManager(this);

        Intent getIntent = getIntent();
        sentBook = (Book)getIntent.getSerializableExtra("book");

        imageView = findViewById(R.id.imageView_update);
        imageView.setImageResource(Integer.parseInt(sentBook.getImage()));
       /* if(){

        }else{
            imageView.setImageResource();
        }*/

        etTitle = findViewById(R.id.etTitle_update);
        etTitle.setText(sentBook.getTitle());

        etAuthor = findViewById(R.id.etAuthor_update);
        etAuthor.setText(sentBook.getAuthor());

        etPublisher = findViewById(R.id.etPublisher_update);
        etPublisher.setText(sentBook.getPublisher());

        etSummary = findViewById(R.id.etSummary_update);
        etSummary.setText(sentBook.getSummary());

        etPrice = findViewById(R.id.etPrice_update);
        etPrice.setText(sentBook.getPrice());
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.confirm_update:
                title = etTitle.getText().toString();
                author = etAuthor.getText().toString();
                publisher = etPublisher.getText().toString();
                summary = etSummary.getText().toString();
                price = etPrice.getText().toString();

                //Toast.makeText(this, title + ", " + author, Toast.LENGTH_SHORT).show();
                if(title.equals("") || author.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    builder.setTitle("안내사항")
                            .setMessage("책 제목과 저자는 필수 항목입니다.")
                            .setCancelable(false)
                            .setPositiveButton("확인", null)
                            .show();
                }else {
                    sentBook.setTitle(title);
                    sentBook.setAuthor(author);
                    sentBook.setPublisher(publisher);
                    sentBook.setSummary(summary);
                    sentBook.setPrice(price);

                    Intent intent = new Intent();
                    intent.putExtra("title_update", title);

                    boolean result = bookDBManager.updateBook(sentBook);
                    if (result)
                        setResult(RESULT_OK, intent);
                    else
                        setResult(RESULT_CANCELED);
                    finish();
                }
                break;
            case R.id.cancel_update:
                finish();
                break;
        }
    }
}