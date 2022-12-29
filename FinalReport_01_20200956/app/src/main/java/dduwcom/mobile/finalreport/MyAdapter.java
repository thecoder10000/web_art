package dduwcom.mobile.finalreport;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Book> bookList; //원본 데이터
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<Book> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return bookList.get(i).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {

        final int position = pos; //실행시간과 다를 수 있으므로 상수로, 멤버변수는 어려우므로-여러개니까-상수로 만듦
        ViewHolder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.tvId = (TextView)convertView.findViewById(R.id.tvId);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            holder.tvAuthor = (TextView)convertView.findViewById(R.id.tvAuthor);
            holder.tvPublisher = (TextView)convertView.findViewById(R.id.tvPublisher);
            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.btnCheck = (Button)convertView.findViewById(R.id.btnCheck);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvId.setText(String.valueOf(bookList.get(pos).get_id()));
        holder.tvTitle.setText(bookList.get(pos).getTitle());
        holder.tvAuthor.setText(bookList.get(pos).getAuthor());
        holder.tvPublisher.setText(bookList.get(pos).getPublisher());
        /*if(bookList.get(pos).getImage() != null) {
            holder.image.setImageResource(Integer.parseInt(bookList.get(pos).getImage()));
        }else{
            holder.image.setImageResource(R.mipmap.nopic);
        }*/
        holder.image.setImageResource(Integer.parseInt(bookList.get(pos).getImage()));
        holder.btnCheck.setFocusable(false);
        holder.btnCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Summary");
                builder.setMessage(bookList.get(pos).getSummary());
                builder.setCancelable(false);
                builder.setPositiveButton("확인", null);
                AlertDialog alertDialog = builder.show();
                TextView messageText = (TextView)alertDialog.findViewById(android.R.id.message);
                messageText.setTextSize(15);
            }
        });

        holder.tvId.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(context, String.valueOf(bookList.get(pos).get_id()), Toast.LENGTH_SHORT).show();
                holder.tvId.setClickable(true);
                return false;
            }
        });

        holder.tvTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(context, "책 제목 : " + bookList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
                holder.tvTitle.setClickable(true);
                return false;
            }
        });

        holder.tvAuthor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(context, "저자 : " + bookList.get(pos).getAuthor(), Toast.LENGTH_SHORT).show();
                holder.tvAuthor.setClickable(true);
                return false;
            }
        });

        holder.tvPublisher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(context, "출판사 : " + bookList.get(pos).getPublisher(), Toast.LENGTH_SHORT).show();
                holder.tvPublisher.setClickable(true);
                return false;
            }
        });

        return convertView;
    }

    static class ViewHolder{ //정적 클래스
        TextView tvId;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvPublisher;
        ImageView image;
        Button btnCheck;
    }
}
