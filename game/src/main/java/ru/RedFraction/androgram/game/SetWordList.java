package ru.RedFraction.androgram.game;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SetWordList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setwordlist);

        final Intent int_main = new Intent(this, MainActivity.class);

        View.OnClickListener radioListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton rb = (RadioButton)v;
                switch (rb.getId()) {
                case R.id.rb_1:

                Toast.makeText(getApplicationContext(), "Anime", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rb_2:

                Toast.makeText(getApplicationContext(), "Films", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rb_3:

                Toast.makeText(getApplicationContext(), "Games", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rb_4:

                Toast.makeText(getApplicationContext(), "Rock Groups", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rb_5:

                Toast.makeText(getApplicationContext(), "Country", Toast.LENGTH_SHORT).show();
                break;

                default:
                break;
                }
            }
        };
    }
}
