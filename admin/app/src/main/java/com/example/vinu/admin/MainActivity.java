package com.example.vinu.admin;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bus;
    Button route;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus=(Button)findViewById(R.id.businsert);
        route=(Button)findViewById(R.id.r_ins);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
route.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,Main3Activity.class);
        startActivity(intent);
    }
});



}
}
