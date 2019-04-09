package xyz.loadnl.payrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;

import xyz.loadnl.payrecord.data.LogItem;
import xyz.loadnl.payrecord.util.DBManager;

public class LogActivity extends AppCompatActivity {
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        container = findViewById(R.id.log_container);
    }

    @Override
    protected void onResume() {
        super.onResume();
        container.removeAllViews();
        AddLogItem();
    }

    private void AddLogItem(){
        DBManager db = new DBManager(this);
        ArrayList<LogItem> list = db.getLogList(0,0);
        for (LogItem item:list) {
            View view = View.inflate(this, R.layout.sample_log_item_comp, null);
            updateText(item,view);
            container.addView(view);
        }
    }

    private void updateText(LogItem data, View view){
        TextView textView = view.findViewById(R.id.text_id);
        textView.setText(""+data.id);
        textView = view.findViewById(R.id.text_create_dt);
        textView.setText(data.create_dt);
        textView = view.findViewById(R.id.text_type);
        textView.setText(""+data.log_type);
        textView = view.findViewById(R.id.text_value);
        textView.setText(data.log_value);
    }
}
