package com.example.androidexample;


import com.example.androidexample.BossCreatorActivity;

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
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;

/**
 * This testing file uses ActivityScenarioRule instead of ActivityTestRule
 * to demonstrate system testings cases
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class NoelSystemTest {

    private static final int SIMULATED_DELAY_MS = 500;

    @Rule
    public ActivityScenarioRule<BossCreatorActivity> activityScenarioRule = new ActivityScenarioRule<>(BossCreatorActivity.class);

    @Test
    public void sendBossInfo() {
        onView(withId(R.id.bossNameInput)).perform(typeText("BossName"), closeSoftKeyboard());
        onView(withId(R.id.healthInput)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.dmgInput)).perform(typeText("50"), closeSoftKeyboard());
        onView(withId(R.id.defenseInput)).perform(typeText("20"), closeSoftKeyboard());
        onView(withId(R.id.sendBtn)).perform(click());

        onView(withId(R.id.errorText)).check(matches(withText(containsString("name"))));
}
    @Test
    public void deleteBossInfo() {
        onView(withId(R.id.idInput)).perform(typeText("26"), closeSoftKeyboard()); // Assuming you provide an ID
        onView(withId(R.id.deleteBtn)).perform(click());

        onView(withId(R.id.errorText)).check(matches(withText(containsString("failure"))));
    }










}

