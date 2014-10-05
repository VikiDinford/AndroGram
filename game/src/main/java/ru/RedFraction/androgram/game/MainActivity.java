package ru.RedFraction.androgram.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private static Random   rnd = new Random();
    private static int      select;
    private static String   ready = new String();
    private static int      points = 0;
    public static String[]  wordlist = WordLists.RockGroups;

    SharedPreferences       sPref;                                                                  // Сохранение счета

    private EditText et_textEnter;                                                                  //Элементы управления
    private TextView tv_word;
    private Button   bt_enter;
    private TextView tv_points;
    private TextView tv_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputFilter customFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence arg0, int arg1, int arg2, Spanned arg3, int arg4, int arg5){                        //Блокировка Enter
            for (int i = arg1; i < arg2; i++) {
                if(arg0.charAt(i) == '\n') {
                    return "";
                }
            }
                return null;}
        };
        et_textEnter.setFilters(new InputFilter[]{ customFilter});

        et_textEnter.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
            if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                Enter();
                return true;
            }
            return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {                                                                 // Handle item selection
            case R.id.menu_wordlist:
                Intent int_wordlist = new Intent(this, SetWordList.class);
                startActivity(int_wordlist);
                return true;
            case R.id.menu_help:
                Intent int_help = new Intent(this, Help.class);
                startActivity(int_help);
                return true;
            case R.id.menu_settings:

                return true;
            case R.id.menu_about:
                Intent int_about = new Intent(this, AboutActivity.class);
                startActivity(int_about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    // Начало метода с входным значением одномерного массива
    private static String anagram(String[] allWords){
        select = rnd.nextInt(allWords.length); // Получаем рандом
        int lenght = allWords[select].length();
        boolean[] isChecked = new boolean[lenght];

        for(int i = 0; i < lenght; i++){
            int rndChar = rnd.nextInt(allWords[select].length());

            if(isChecked[rndChar] == false){
                ready += allWords[select].charAt(rndChar);
                isChecked[rndChar] = true;
            }
            else{
                lenght++;
            }
        }
        return ready.toLowerCase();
    }

    private void Enter(){
        if(wordlist[select].toLowerCase().equals(et_textEnter.getText().toString().toLowerCase())){
            Toast.makeText(getApplicationContext(), "Отлично +1 очко", Toast.LENGTH_SHORT).show();
            tv_word.setText(anagram(wordlist).toLowerCase());
            points++;
            tv_points.setText(String.valueOf(points));
            ready = "";
            et_textEnter.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Неудача -1 очко", Toast.LENGTH_SHORT).show();
            tv_word.setText(anagram(wordlist).toLowerCase());
            ready = "";
            points--;
            tv_points.setText(String.valueOf(points));
            et_textEnter.setText("");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Prefs.saveScore(getApplicationContext(), points);
    } //Сохранение

    @Override
    protected void onResume() {
        super.onResume();

        points = Prefs.loadScore(getApplicationContext());
        tv_points.setText(points + "");
    } //Загрузка

}
