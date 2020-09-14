package com.example.semana5_bd.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.semana5_bd.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {
    private TextInputEditText tietName;
    private TextInputEditText tietEmail;
    private TextInputEditText tietMessage;
    String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        tietName = findViewById(R.id.tietName);
        tietEmail = findViewById(R.id.tietEmail);
        tietMessage = findViewById(R.id.tietMessage);
    }

    public void sendMessage(View view) {
        sEmail = "";//TODO insertar correo
        sPassword = "";//TODO insertar contraseña
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        //Initialize session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmail, sPassword);
            }
        });

        try {
            //Initialize email content
            Message message = new MimeMessage(session);
            //Sender Email
            message.setFrom(new InternetAddress(sEmail));
            //Recipent email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Objects.requireNonNull(tietEmail.getText()).toString().trim()));
            //Email subject
            message.setSubject(Objects.requireNonNull(tietName.getText()).toString().trim());

            //Email Message
            message.setText(Objects.requireNonNull(tietMessage.getText()).toString().trim());

            //Send email
            new SendMail().execute(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SendMail extends AsyncTask<Message, String, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(Contacto.this, "Espere un momento",
                    "Enviando mensaje . . .", true);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss progress dialog
            progressDialog.dismiss();
            if (s.equals("Success")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Contacto.this);
                builder.setCancelable(false);
                builder.setTitle("Success");
                builder.setMessage("Mensaje enviado satisfactoriamente.");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tietMessage.setText("");
                    }
                });
                builder.show();
            } else {
                Toast.makeText(Contacto.this, "Ha pasado un error.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}