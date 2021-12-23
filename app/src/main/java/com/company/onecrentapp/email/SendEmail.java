package com.company.onecrentapp.email;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.company.onecrentapp.R;
import com.company.onecrentapp.models.User;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SendEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
    }

    private String getUserLastNameNameMiddleName()
    {
        User user = User.getInstance();
        return user.lastName + " " + user.firstName + " " + user.middleName;
    }

    private String getUserEmail()
    {
        return "KO.T.E@yandex.ru";
    }

    public void sendMessage(View v)
    {
        TextInputEditText labelText = findViewById(R.id.labelEditText);
        TextInputEditText textEdit = findViewById(R.id.editText);
        String label = labelText.getText().toString();
        String text = textEdit.getText().toString();
        String userLastNameNameMiddleName = getUserLastNameNameMiddleName();
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.forLanguageTag("ru")).format(new Date());
        String userEmail = getUserEmail();
        try {
            GMailSender sender = new GMailSender("ivanbychkovsky3@gmail.com",
                        "helloworld228");
            sender.sendMail("Письмо от пользователя " + userLastNameNameMiddleName, date + " Поступило письмо от пользователя " + userLastNameNameMiddleName + "\n" + "На тему “" + label + "”\n" + "Письмо: " + text, "ivanbychkovsky3@gmail.com", userEmail);
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
        finish();
    }
}