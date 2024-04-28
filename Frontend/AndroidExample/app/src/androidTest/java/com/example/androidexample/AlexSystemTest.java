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
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.Button;


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
    public void Login() throws JSONException {
        onView(withId(R.id.btnSIgnOut)).perform(click());
        onView(withId(R.id.User_Name)).perform(typeText("example"));
        onView(withId(R.id.Password)).perform(typeText("example"));
        onView(withId(R.id.btnLogin)).perform(click());
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){

        }
        assertEquals(LoginActivity.getUserInfo().getString("name"),"example");
    }
    @Test
    public void GenerateMap(){
        onView(withId(R.id.btnSIgnOut)).perform(click());
        onView(withId(R.id.User_Name)).perform(typeText("example"));
        onView(withId(R.id.Password)).perform(typeText("example"));
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.btnMapTest)).perform(click());
        onView(withId(R.id.MapNew)).perform((click()));
        onView(withId(R.id.Heath_val)).check(matches(withText("100")));
    }
    @Test
    public void FlashcardEdit(){
        // this looks like just editing text but its checking if the text is saved on the server
        onView(withId(R.id.btnFlashCard)).perform(click());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        onView(withId(R.id.Correct)).perform(replaceText("Edited Text"));
        onView(withId(R.id.Save)).perform(click());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        onView(withId(R.id.Next_Card)).perform(click());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        onView(withId(R.id.Prev_Card)).perform(click());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        // this causes the app to pull the flashcard from the server
        onView(withId(R.id.Correct)).check(matches(withText("Edited Text")));
        //Sets text back to normal
        onView(withId(R.id.Correct)).perform(replaceText("Paris"));
        onView(withId(R.id.Save)).perform(click());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
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
}

