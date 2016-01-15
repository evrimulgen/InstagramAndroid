package com.example.instagramclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.emreeran.instagramclient.Instagram;
import com.emreeran.instagramclient.objects.User;

public class MainActivity extends AppCompatActivity {

    TextView mResponseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResponseTextView = (TextView) findViewById(R.id.response_text_view);
    }

    public void onClick(View view) {
        Instagram instagram = new Instagram(this);

        switch (view.getId()) {
            case R.id.button_login:
                instagram.login();
                break;
            case R.id.button_self:
                instagram.getSelf(new Instagram.UserCallback() {
                    @Override
                    public void done(User user) {
                        String text = "id: " + user.getId() + " name: " + user.getFullName() + " username: " + user.getUsername();
                        mResponseTextView.setText(text);
                    }
                });
                break;
            case R.id.button_search:
                EditText editText = (EditText) findViewById(R.id.edit_text);
                String phrase = editText.getText().toString();
                instagram.searchUser(phrase);
                break;
        }

    }
}
