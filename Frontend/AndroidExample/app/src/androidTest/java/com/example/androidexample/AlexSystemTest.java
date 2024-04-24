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
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;

import android.content.Context;
import android.os.Looper;
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

    @Test
    public void GetFlashcard() throws JSONException {
        Looper.prepare();
        onView(withId(R.id.btnFlashCard)).perform(click());
        try {
         Thread.sleep(1000);
        }catch (InterruptedException e){

         }
        onView(withId(R.id.Question)).check(matches(withText("What is the capital of France?")));

    }
    @Test
    public void SaveAndDeleteFlashcard(){
    }
}

