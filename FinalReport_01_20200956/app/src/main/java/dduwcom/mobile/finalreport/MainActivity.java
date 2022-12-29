// 과제명: 도서 관리 앱 – 책 정보를 기록
// 분반: 01 분반
// 학번: 20200956 성명 : 김주연
// 제출일: 2022년 06월 19일
package dduwcom.mobile.finalreport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int ADD = 100;
    final int UPDATE = 200;
    final int DEVELOPER = 300;
    final int QUERY_TITLE = 400;

    private ListView listView;
    private MyAdapter adapter;
    private BookDBHelper bookDBHelper;
    private BookDBManager bookDBManager;
    private ArrayList<Book> bookList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = new ArrayList<Book>();
        bookDBManager = new BookDBManager(this);
        bookDBHelper = new BookDBHelper(this);

        listView = findViewById(R.id.listView);
        listView.setItemsCanFocus(true);
        adapter = new MyAdapter(this, R.layout.custom_adapter_view, bookList);
        listView.setAdapter(adapter);

        //삭제
        listView.setOnItemLongClickListener(onItemLongClickListener);
        //수정
        listView.setOnItemClickListener(onItemClickListener);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //만들어놓은 xml메뉴가 뜬다
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            //추가
            case R.id.addBook:
                Intent intent_addBook = new Intent(this, AddActivity.class);
                startActivityForResult(intent_addBook, ADD);
                break;
            //개발자
            case R.id.viewDeveloperInfo:
                Intent intent_developer = new Intent(this, DeveloperInfo.class);
                startActivityForResult(intent_developer, DEVELOPER);
                break;
           //종료
            case R.id.quit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("앱 종료");
                builder.setMessage("앱을 종료하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                break;
                //책 제목으로 검색
            case R.id.query_title:
                Intent intent_query_title = new Intent(this, Query_title.class);
                //startActivityForResult(intent_query_title, QUERY_TITLE);
                startActivity(intent_query_title);
                break;
            case R.id.query_author:
                Intent intent_query_author = new Intent(this, Query_author.class);
                startActivity(intent_query_author);
                break;
            case R.id.query_publisher:
                Intent intent_query_publisher = new Intent(this, Query_publisher.class);
                startActivity(intent_query_publisher);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            //추가확인
            case ADD:
                if(resultCode == RESULT_OK){
                    String title_add = data.getStringExtra("title_add");
                    Toast.makeText(this, title_add + " 책 추가 완료", Toast.LENGTH_SHORT).show();
                }
                break;
            //수정확인
            case UPDATE:
                if(resultCode == RESULT_OK){
                    String title_update = data.getStringExtra("title_update");
                    Toast.makeText(this, title_update + " 책 수정 완료", Toast.LENGTH_SHORT).show();
                }
                break;
            //개발자정보 확인
            case DEVELOPER:
                if(resultCode == RESULT_OK){
                    Toast.makeText(this, "확인 완료", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    //삭제
    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("책 정보 삭제");
            builder.setMessage("책 '" + bookList.get(i).getTitle() + "' 정보를 삭제하시겠습니까?");
            builder.setCancelable(false);
            builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean result = bookDBManager.deleteBook(bookList.get(i));
                    if(result) {
                        bookList.clear();
                        bookList.addAll(bookDBManager.getAllBook());
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            builder.setNegativeButton("취소", null);
            builder.show();

            return true;
        }
    };
    //수정
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent_update = new Intent(MainActivity.this, UpdateActivity.class);
            intent_update.putExtra("book", bookList.get(i));
            startActivityForResult(intent_update, UPDATE);
        }
    };

    public void onClick(View view){

    }

    protected void onResume(){
        super.onResume();
        bookList.clear();
        bookList.addAll(bookDBManager.getAllBook());
        adapter.notifyDataSetChanged();
    }
}