package in.aniruddhag.forcefest_appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Report extends AppCompatActivity {

    Button btn_send;
    EditText ET_Message, ET_Subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        btn_send = findViewById(R.id.btn_send);
        ET_Message = findViewById(R.id.ET_Message);
        ET_Subject = findViewById(R.id.ET_Subject);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String sSubject = ET_Subject.getText().toString();
        String sMessage = ET_Message.getText().toString();
        if (sSubject.isEmpty() || sMessage.isEmpty()){
            Toast.makeText(this, "Make sure no fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SENDTO);

            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, "war@gov.in");
            intent.putExtra(Intent.EXTRA_SUBJECT, sSubject);
            intent.putExtra(Intent.EXTRA_TEXT, sMessage);

            intent.setDataAndType(Uri.parse("mailto:"), "message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an Email Client"));
        }
    }
}