package com.intelligence.raiffeisentest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.intelligence.raiffeisentest.R;


public class SendEmailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_activity);

        final EditText emailAddress = (EditText) findViewById(R.id.email);
        final EditText emailSubject = (EditText) findViewById(R.id.subject);
        final EditText emailMessage = (EditText) findViewById(R.id.message);
        Button sendEmailButton = (Button) findViewById(R.id.send_button);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toAddress = emailAddress.getText().toString();
                String msubject = emailSubject.getText().toString();
                String mmessage = emailMessage.getText().toString();

                Intent sendEmail = new Intent(Intent.ACTION_SEND);
                sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{toAddress});
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, msubject);
                sendEmail.putExtra(Intent.EXTRA_TEXT, mmessage);
                sendEmail.setType("message/rfc822");
                startActivity(Intent.createChooser(sendEmail, "Send Email Via"));

            }
        });
    }
}
