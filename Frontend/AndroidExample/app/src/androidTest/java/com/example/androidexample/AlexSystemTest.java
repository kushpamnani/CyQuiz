package com.example.androidexample;


import com.example.androidexample.MainActivity;
import com.example.androidexample.Map.MapActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;

import android.view.View;


// Mock the RequestServerForService class
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class AlexSystemTest {
    private static final int SIMULATED_DELAY_MS = 5000;
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    /**
     * Start the server and run this test
     */
    @Test
    public void GenerateMap(){
        //try {
           // Thread.sleep(1000);
        //}catch (InterruptedException e){

       // }
       // onView(withId(R.id.btnMapTest)).perform(click());
       // try {
         //   Thread.sleep(SIMULATED_DELAY_MS);
       // }catch (InterruptedException e){

       // }
    }
    @Test
    public void SetHp(){
    }
    @Rule
    public ActivityScenarioRule<FlashCardActivity> flashcardRule = new ActivityScenarioRule<>(FlashCardActivity.class);
    @Test
    public void GetFlashcard() throws JSONException {
        JSONObject flashcard1= new JSONObject();
        flashcard1.put("id",4);
        flashcard1.put("question","correct question");
        flashcard1.put("answer","Correct answer");
        flashcard1.put("option1","Correct option1");
        flashcard1.put("option2","Correct option2");
        flashcard1.put("option3","Correct option3");
        JSONObject flashcard2= new JSONObject();
        flashcard2.put("id",4);
        flashcard2.put("question","correct question");
        flashcard2.put("answer","Correct answer");
        flashcard2.put("option1","Correct option1");
        flashcard2.put("option2","Correct option2");
        flashcard2.put("option3","Correct option3");
        JSONArray flashcardArray = new JSONArray();
        flashcardArray.put(flashcard1);
        flashcardArray.put(flashcard2);
        try {
        Thread.sleep(1000);
        }catch (InterruptedException e){

         }
    }
    @Test
    public void SaveAndDeleteFlashcard(){
    }
}

