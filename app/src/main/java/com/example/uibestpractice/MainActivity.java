package com.example.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.uibestpractice.apapter.MsgAdapter;
import com.example.uibestpractice.entity.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button sendBtn;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler_view);
        inputText = (EditText)findViewById(R.id.input_text);
        sendBtn = (Button)findViewById(R.id.send);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(manager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg newMsg = new Msg();
                    newMsg.setContent(content);
                    newMsg.setType(Msg.TYPE_SEND);
                    msgList.add(newMsg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);

                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs(){
        Msg msg1 = new Msg();
        msg1.setContent("Hello wangliyue...");
        msg1.setType(Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg();
        msg2.setContent("Hello,who are you?");
        msg2.setType(Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg();
        msg3.setContent("I am you!");
        msg3.setType(Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }
}
