package id.co.hananda.atry;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.Random;

import id.co.hananda.atry.BroadcastReceiver.AlarmReceiver;
import id.co.hananda.atry.Model.User;

public class    MainActivity extends AppCompatActivity {
//    MaterialEditText edtNewUser, edtNewPassword; //for sign up
    MaterialEditText edtUser, edtPassword; //for sign in

    Button btnSignUp, btnSignIn;

    BroadcastReceiver mRegistrationBroadcastReceiver;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerAlarm();
        
        registrationNotification();

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUser = (MaterialEditText) findViewById(R.id.edtUser);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
//        btnSignUp = (Button) findViewById(R.id.btn_sign_up);

////        btnSignUp.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                showSignUpDialog();
////            }
//        });
        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signIn(edtUser.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void registrationNotification() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(MainActivity.STR_PUSH))
                {
                    String message = intent.getStringExtra("message");
                    showNotification("23Dev", message);
                }
            }

            private void showNotification(String title, String message) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
                builder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentIntent(contentIntent);

                NotificationManager notificationManager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(new Random().nextInt(),builder.build());
            }
        };
    }

    private void registerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set (Calendar.HOUR_OF_DAY,16); // 9 hour
        calendar.set (Calendar.MINUTE,52); //40 minute
        calendar.set (Calendar.SECOND,0); // 0 second

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    private void signIn(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).exists()) {
                    if (!user.isEmpty()) {
                        User login = dataSnapshot.child(user).getValue(User.class);
                        if (login.getPassword().equals(pwd)) {
                            Intent homeActivity = new Intent(MainActivity.this,FourthActivity.class);
                            startActivity(homeActivity);
                            finish();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Password? Ada di Origami", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Whoops! Cari dulu yang bener Usernamenya ", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "ID siapa tuh?", Toast.LENGTH_SHORT).show();
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    private void showSignUpDialog() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//        alertDialog.setTitle("Cus Daftar biar bisa login :D");
//        alertDialog.setMessage("Di isi ya, ngasal aja~");
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout, null);
//
//        edtNewUser = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewUserName);
//        edtNewPassword = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewPassword);
//
//        alertDialog.setView(sign_up_layout);
//        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);
//
//        alertDialog.setNegativeButton("Balik lah", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        alertDialog.setPositiveButton("Daftar bang!", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                final User user = new User(edtNewUser.getText().toString(),
//                        edtNewPassword.getText().toString());
//
//                users.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.child(user.getUserName()).exists())
//                            Toast.makeText(MainActivity.this, ".", Toast.LENGTH_SHORT).show();
//                        else {
//                            users.child(user.getUserName())
//                                    .setValue(user);
//                            Toast.makeText(MainActivity.this, "Pendaftaran Sukses :D", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//                dialogInterface.dismiss();
//            }
//        });
//
//        alertDialog.show();
//    }

    public static final String STR_PUSH = "pushNotification";
}
