package com.xtremebd.ksl.activities;

import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.FirebaseApp;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Message;
import com.xtremebd.ksl.utils.DBHelper;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DiscussionActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseListAdapter<Message> adapter;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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
                        .setValue(new Message(input.getText().toString(), DBHelper.getMasterAccount(DiscussionActivity.this).getName())
                        );

                // Clear the input
                input.setText("");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void displayMessages() {
        ListView listOfMessages = findViewById(R.id.list_of_messages);

        Query query = FirebaseDatabase.getInstance()
                .getReference("messages");

        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
                .setLayout(R.layout.message)//Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                .setQuery(query, Message.class)
                .build();


        adapter = new FirebaseListAdapter<Message>(options) {
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


        /***
         adapter = new FirebaseListAdapter<Message>(this, Message.class,
         R.layout.message, FirebaseDatabase.getInstance().getReference()) {
        @Override protected void populateView(View v, Message model, int position) {
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
         ***/


        listOfMessages.setAdapter(adapter);
    }
}
