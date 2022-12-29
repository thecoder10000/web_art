package dduwcom.mobile.finalreport;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class BookDBManager {

    BookDBHelper bookDBHelper = null;
    Cursor cursor = null;

    public BookDBManager(Context context) {
        bookDBHelper = new BookDBHelper(context);
    }

    //db에 있는 모든 정보를 arrayList에 옮기기 위한 메소드
    public ArrayList<Book> getAllBook(){
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BookDBHelper.TABLE_NAME, null);
        while(cursor.moveToNext()) {
            long id = cursor.getLong( cursor.getColumnIndex(BookDBHelper.COL_ID) );
            String book = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PUBLISHER));
            String summary = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_SUMMARY));
            String price = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_IMAGE));
            //아래 addNewBook에 조건문으로 처리했기 때문에 요건 필요X
            /*if(image == null)
                image = Integer.toString(R.drawable.nopic);*/
            bookList.add ( new Book (id, book, author, publisher, summary, price, image) );
        }

        cursor.close();
        bookDBHelper.close();
        return bookList;
    }

    //추가
    public boolean addNewBook(Book newBook){
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(BookDBHelper.COL_TITLE, newBook.getTitle());
        row.put(BookDBHelper.COL_AUTHOR, newBook.getAuthor());
        row.put(BookDBHelper.COL_PUBLISHER, newBook.getPublisher());
        row.put(BookDBHelper.COL_SUMMARY, newBook.getSummary());
        row.put(BookDBHelper.COL_PRICE, newBook.getPrice());
        //사진 없으면 nopic을 저장함
        if(newBook.getImage() == null)
            row.put(BookDBHelper.COL_IMAGE, R.mipmap.nopic);
        else
            row.put(BookDBHelper.COL_IMAGE, newBook.getImage());

        int result = (int) db.insert(BookDBHelper.TABLE_NAME, null, row);
        bookDBHelper.close();

        if(result > 0) return true;
        return false;
    }

    //수정
    public boolean updateBook(Book book){
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(BookDBHelper.COL_TITLE, book.getTitle());
        row.put(BookDBHelper.COL_AUTHOR, book.getAuthor());
        row.put(BookDBHelper.COL_PUBLISHER, book.getPublisher());
        row.put(BookDBHelper.COL_SUMMARY, book.getSummary());
        row.put(BookDBHelper.COL_PRICE, book.getPrice());

        String whereClause = BookDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(book.get_id())};

        int result = db.update(BookDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        bookDBHelper.close();

        if(result > 0) return true;
        return false;
    }

    //삭제
    public boolean deleteBook(Book book){
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        String whereClause = BookDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(book.get_id())};

        int result = db.delete(BookDBHelper.TABLE_NAME, whereClause, whereArgs);
        bookDBHelper.close();

        if(result > 0) return true;
        return false;
    }

    //1.책 제목으로 DB 검색 후 객체 반환
    public ArrayList<Book> getBookByTitle(String title) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        SQLiteDatabase db = bookDBHelper.getReadableDatabase(); //테이블 생성 - 읽기용

        //Cursor cursor = db.rawQuery("SELECT * FROM " + BookDBHelper.TABLE_NAME + " WHERE " + bookDBHelper.COL_TITLE + " LIKE \"%" + title + "%\", null);

        String[] columns = {BookDBHelper.COL_ID, BookDBHelper.COL_TITLE, BookDBHelper.COL_AUTHOR, BookDBHelper.COL_PUBLISHER, BookDBHelper.COL_SUMMARY, BookDBHelper.COL_PRICE, BookDBHelper.COL_IMAGE};
        String selection = bookDBHelper.COL_TITLE + "=?";
        String[] selectArgs = new String[]{title};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(BookDBHelper.COL_ID));
            String author = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PUBLISHER));
            String summary = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_SUMMARY));
            String price = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_IMAGE));
            bookList.add(new Book(id, title, author, publisher, summary, price, image));
        }

        cursor.close();
        bookDBHelper.close();
        return bookList;
    }

    //2.저서로 DB 검색 후 해당 객체 반환
    public ArrayList<Book> getBookByAuthor(String author) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        SQLiteDatabase db = bookDBHelper.getReadableDatabase(); //테이블 생성 - 읽기용

        String[] columns = {BookDBHelper.COL_ID, BookDBHelper.COL_TITLE, BookDBHelper.COL_AUTHOR, BookDBHelper.COL_PUBLISHER, BookDBHelper.COL_SUMMARY, BookDBHelper.COL_PRICE, BookDBHelper.COL_IMAGE};
        String selection = bookDBHelper.COL_AUTHOR + "=?";
        String[] selectArgs = new String[]{author};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(BookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_TITLE));
            String publisher = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PUBLISHER));
            String summary = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_SUMMARY));
            String price = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_IMAGE));
            bookList.add(new Book(id, title, author, publisher, summary, price, image));
        }

        cursor.close();
        bookDBHelper.close();
        return bookList;
    }

    //3.출판사로 DB 검색 후 객체 반환
    public ArrayList<Book> getBookByPublisher(String publisher) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        SQLiteDatabase db = bookDBHelper.getReadableDatabase(); //테이블 생성 - 읽기용

        String[] columns = {BookDBHelper.COL_ID, BookDBHelper.COL_TITLE, BookDBHelper.COL_AUTHOR, BookDBHelper.COL_PUBLISHER, BookDBHelper.COL_SUMMARY, BookDBHelper.COL_PRICE, BookDBHelper.COL_IMAGE};
        String selection = bookDBHelper.COL_PUBLISHER + "=?";
        String[] selectArgs = new String[]{publisher};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(BookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_AUTHOR));
            String summary = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_SUMMARY));
            String price = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_IMAGE));
            bookList.add(new Book(id, title, author, publisher, summary, price, image));
        }

        cursor.close();
        bookDBHelper.close();
        return bookList;
    }

    // 4.id로 DB 검색 후 객체 반환
    public ArrayList<Book> getBoodById(long id) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        SQLiteDatabase db = bookDBHelper.getReadableDatabase(); //테이블 생성 - 읽기용

        String[] columns = {BookDBHelper.COL_ID, BookDBHelper.COL_TITLE, BookDBHelper.COL_AUTHOR, BookDBHelper.COL_PUBLISHER, BookDBHelper.COL_SUMMARY, BookDBHelper.COL_PRICE, BookDBHelper.COL_IMAGE};
        String selection = bookDBHelper.COL_ID + "=?";
        String[] selectArgs = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);
        while(cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PUBLISHER));
            String summary = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_SUMMARY));
            String price = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(BookDBHelper.COL_IMAGE));
            bookList.add(new Book(id, title, author, publisher, summary, price, image));
        }

        cursor.close();
        bookDBHelper.close();
        return bookList;
    }

    public void close() {
        if (bookDBHelper != null) bookDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
