package ru.RedFraction.androgram.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    public static String[] wordlist = WordLists.RockGroups;
    private static String word;
    private static int select;
    private static int points = 0;
    private static Random rnd;
    //Элементы управления
    private EditText et_textEnter;
    private TextView tv_word;
    private Button   bt_enter;
    private TextView tv_points;
    private TextView tv_words;

    // Начало метода с входным значением одномерного массива
    private static String anagram(String[] allWords) {
        select = rnd.nextInt(allWords.length); // Получаем рандом
        int lenght = allWords[select].length();
        boolean[] isChecked = new boolean[lenght];

        for (int i = 0; i < lenght; i++) {
            int rndChar = rnd.nextInt(allWords[select].length());

            if (isChecked[rndChar] == false) {
                word += allWords[select].charAt(rndChar);
                isChecked[rndChar] = true;
            } else {
                lenght++;
            }
        }
        return word.toLowerCase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cлушатель кнопки Enter
        et_textEnter.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Enter();
                    return true;
                }
                return false;
            }
        });

        View.OnClickListener radioListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton rb = (RadioButton) v;
                switch (rb.getId()) {
                    case R.id.rb_1:
                        wordlist = WordLists.Animes;
                        Toast.makeText(getApplicationContext(), "Anime", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_2:
                        wordlist = WordLists.Films;
                        Toast.makeText(getApplicationContext(), "Films", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_3:
                        wordlist = WordLists.Games;
                        Toast.makeText(getApplicationContext(), "Games", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_4:
                        wordlist = WordLists.RockGroups;
                        Toast.makeText(getApplicationContext(), "Rock Groups", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_5:
                        wordlist = WordLists.Countrys;
                        Toast.makeText(getApplicationContext(), "Country", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {                                                                 // Handle item selection

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

    private void Enter(){
        if(wordlist[select].toLowerCase().equals(et_textEnter.getText().toString().toLowerCase())){
            Toast.makeText(getApplicationContext(), "Отлично +1 очко", Toast.LENGTH_SHORT).show();
            tv_word.setText(anagram(wordlist).toLowerCase());
            points++;
            tv_points.setText(String.valueOf(points));
            word = "";
            et_textEnter.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Неудача -1 очко", Toast.LENGTH_SHORT).show();
            tv_word.setText(anagram(wordlist).toLowerCase());
            word = "";
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
        //wordlist = Prefs.loadWordlist(getApplicationContext());
        points = Prefs.loadScore(getApplicationContext());
        tv_points.setText(points + "");
    } //Загрузка

}
