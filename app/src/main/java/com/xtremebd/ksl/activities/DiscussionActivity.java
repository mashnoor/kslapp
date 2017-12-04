package com.xtremebd.ksl.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;

import com.google.firebase.FirebaseApp;

import com.google.firebase.database.FirebaseDatabase;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Message;
import com.xtremebd.ksl.utils.DBHelper;

public class DiscussionActivity extends AppCompatActivity {

    private FirebaseListAdapter<Message> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        FirebaseApp.initializeApp(this);
        displayMessages();
        FloatingActionButton fab =
                findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new Message(input.getText().toString(), DBHelper.getMasterAccount(DiscussionActivity.this).getMasterId())
                        );

                // Clear the input
                input.setText("");
            }
        });
    }

    private void displayMessages()
    {
        ListView listOfMessages =findViewById(R.id.list_of_messages);



        adapter = new FirebaseListAdapter<Message>(this, Message.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {
                // Get references to the views of message.xml
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };


        listOfMessages.setAdapter(adapter);
    }
}
