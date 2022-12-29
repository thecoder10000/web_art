package dduwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DeveloperInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developerinfo);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                break;
        }
        finish();
    }
}