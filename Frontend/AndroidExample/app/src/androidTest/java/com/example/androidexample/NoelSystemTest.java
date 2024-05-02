package com.example.androidexample;


import com.example.androidexample.BossCreatorActivity;
import com.example.androidexample.JsonObjReqActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;


@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class NoelSystemTest {

    private static final int SIMULATED_DELAY_MS = 500;

    @Rule
    public ActivityScenarioRule<MainActivity> Rule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void sendBossInfo() throws InterruptedException {
        onView(withId(R.id.btnEnemyCreate)).perform(click());
        onView(withId(R.id.teacherCheck)).perform(click());
        onView(withId(R.id.bossCreateButton)).perform(click());
        Thread.sleep(500);



        onView(withId(R.id.bossNameInput)).perform(replaceText("BossName"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.healthInput)).perform(replaceText("100"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.dmgInput)).perform(replaceText("50"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.defenseInput)).perform(replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.sendBtn)).perform(click());
        Thread.sleep(500);

        onView(withId(R.id.errorText)).check(matches(withText(containsString("health\":100"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("BossName"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("attack\":50"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("defense\":20"))));




    }

    @Test
    public void updateBossFlashcardInfo() throws InterruptedException {

        onView(withId(R.id.btnEnemyCreate)).perform(click());
        Thread.sleep(150);

        onView(withId(R.id.teacherCheck)).perform(click());
        Thread.sleep(150);

        onView(withId(R.id.bossCreateButton)).perform(click());
        Thread.sleep(500);



        onView(withId(R.id.idInput)).perform(typeText("29"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.cardInput)).perform(typeText("5"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.addCard)).perform(click());
        Thread.sleep(500);


        onView(withId(R.id.errorText)).check(matches(withText(containsString("success"))));

    }
    @Test
    public void GetBossInfo() throws InterruptedException {
        onView(withId(R.id.btnEnemyCreate)).perform(click());
        Thread.sleep(150);
        onView(withId(R.id.teacherCheck)).perform(click());
        Thread.sleep(150);
        onView(withId(R.id.bossCreateButton)).perform(click());
        Thread.sleep(500);




        onView(withId(R.id.idInput)).perform(typeText("28"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.getButton)).perform(click());
        Thread.sleep(500);


        onView(withId(R.id.errorText)).check(matches(withText(containsString("health\":100"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("BossName"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("attack\":50"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("defense\":20"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("id\":5"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("option1\":\"Berlin\""))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("option2\":\"Madrid\""))));




    }

    @Test
    public void getUserInfo() throws InterruptedException {
        onView(withId(R.id.btnStringRequest)).perform(click());
        Thread.sleep(150);
        onView(withId(R.id.IDGet)).perform(typeText("9"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.btnStringReq)).perform(click());
        Thread.sleep(500);




        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("name\":\"NewNameTester\""))));
        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("password\":\"Blankpass\""))));


    }
    @Test
    public void getTeacherInfo() throws InterruptedException {
        onView(withId(R.id.btnStringRequest)).perform(click());
        Thread.sleep(150);
        onView(withId(R.id.IDGet)).perform(typeText("22"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.teacherChip)).perform(click());
        Thread.sleep(150);
        onView(withId(R.id.btnStringReq)).perform(click());
        Thread.sleep(500);




        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("name\":\"TestingTeach\""))));
        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("password\":\"TestPass123\""))));


    }
    @Test
    public void checkBattleInfo() throws InterruptedException {
        onView(withId(R.id.battleScreen)).perform(click());
        Thread.sleep(150);
        onView(withId(R.id.battleIDInput)).perform(typeText("49"), closeSoftKeyboard());
        Thread.sleep(150);
        onView(withId(R.id.startBattleBtn)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.enemyLeft)).check(matches(withText(containsString("small enemies left: 2"))));

    }
    @Test
    public void checkHealthInfo() throws InterruptedException {
        onView(withId(R.id.battleScreen)).perform(click());
        Thread.sleep(50);
        onView(withId(R.id.battleIDInput)).perform(typeText("49"), closeSoftKeyboard());
        Thread.sleep(50);
        onView(withId(R.id.startBattleBtn)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.healthNum)).check(matches(withText(containsString("0"))));
    }












}

