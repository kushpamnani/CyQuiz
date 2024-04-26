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
        onView(withId(R.id.healthInput)).perform(replaceText("100"), closeSoftKeyboard());
        onView(withId(R.id.dmgInput)).perform(replaceText("50"), closeSoftKeyboard());
        onView(withId(R.id.defenseInput)).perform(replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.sendBtn)).perform(click());
        Thread.sleep(500);

        onView(withId(R.id.errorText)).check(matches(withText(containsString("health\":100"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("BossName"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("attack\":50"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("defense\":20"))));




    }
    @Test
    public void updateBossInfo() throws InterruptedException {
        onView(withId(R.id.btnEnemyCreate)).perform(click());
        onView(withId(R.id.teacherCheck)).perform(click());
        onView(withId(R.id.bossCreateButton)).perform(click());
        Thread.sleep(500);



        onView(withId(R.id.idInput)).perform(typeText("29"), closeSoftKeyboard());
        onView(withId(R.id.bossNameInput)).perform(typeText("BossNameNew"), closeSoftKeyboard());
        onView(withId(R.id.healthInput)).perform(typeText("110"), closeSoftKeyboard());
        onView(withId(R.id.dmgInput)).perform(typeText("25"), closeSoftKeyboard());
        onView(withId(R.id.defenseInput)).perform(typeText("25"), closeSoftKeyboard());
        onView(withId(R.id.updateBtn)).perform(click());
        Thread.sleep(500);


        onView(withId(R.id.errorText)).check(matches(withText(containsString("health\":110"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("BossNameNew"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("attack\":25"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("defense\":25"))));

        onView(withId(R.id.idInput)).perform(replaceText("29"), closeSoftKeyboard());
        onView(withId(R.id.bossNameInput)).perform(replaceText("BossNameChecker"), closeSoftKeyboard());
        onView(withId(R.id.healthInput)).perform(replaceText("200"), closeSoftKeyboard());
        onView(withId(R.id.dmgInput)).perform(replaceText("250"), closeSoftKeyboard());
        onView(withId(R.id.defenseInput)).perform(replaceText("22"), closeSoftKeyboard());
        onView(withId(R.id.updateBtn)).perform(click());
        Thread.sleep(500);



        onView(withId(R.id.errorText)).check(matches(withText(containsString("health\":200"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("BossNameChecker"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("attack\":250"))));
        onView(withId(R.id.errorText)).check(matches(withText(containsString("defense\":22"))));

    }
    @Test
    public void updateBossFlashcardInfo() throws InterruptedException {

        onView(withId(R.id.btnEnemyCreate)).perform(click());
        onView(withId(R.id.teacherCheck)).perform(click());
        onView(withId(R.id.bossCreateButton)).perform(click());
        Thread.sleep(500);



        onView(withId(R.id.idInput)).perform(typeText("29"), closeSoftKeyboard());
        onView(withId(R.id.cardInput)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.addCard)).perform(click());
        Thread.sleep(500);


        onView(withId(R.id.errorText)).check(matches(withText(containsString("success"))));

    }
    @Test
    public void GetBossInfo() throws InterruptedException {
        onView(withId(R.id.btnEnemyCreate)).perform(click());
        onView(withId(R.id.teacherCheck)).perform(click());
        onView(withId(R.id.bossCreateButton)).perform(click());
        Thread.sleep(500);




        onView(withId(R.id.idInput)).perform(typeText("28"), closeSoftKeyboard());
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
        onView(withId(R.id.IDGet)).perform(typeText("9"), closeSoftKeyboard());
        onView(withId(R.id.btnStringReq)).perform(click());
        Thread.sleep(500);




        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("name\":\"NewNameTester\""))));
        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("password\":\"Blankpass\""))));


    }
    @Test
    public void getTeacherInfo() throws InterruptedException {
        onView(withId(R.id.btnStringRequest)).perform(click());
        onView(withId(R.id.IDGet)).perform(typeText("22"), closeSoftKeyboard());
        onView(withId(R.id.teacherChip)).perform(click());
        onView(withId(R.id.btnStringReq)).perform(click());
        Thread.sleep(500);




        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("name\":\"TestingTeach\""))));
        onView(withId(R.id.msgResponse)).check(matches(withText(containsString("password\":\"TestPass123\""))));


    }
//    @Test
//    public void checkOnlineInfo() throws InterruptedException {
//        onView(withId(R.id.onlineScreen)).perform(click());
//        onView(withId(R.id.usernameGet)).perform(replaceText("HiTesting"), closeSoftKeyboard());
//        onView(withId(R.id.onlineButton)).perform(click());
//        Thread.sleep(500);
//
//        onView(withId(R.id.debugText)).check(matches(withText(containsString("HiTesting"))));
//
//        Espresso.pressBack();
//
//        onView(withId(R.id.btnEnemyCreate)).perform(click());
//        onView(withId(R.id.teacherCheck)).perform(click());
//        onView(withId(R.id.bossCreateButton)).perform(click());
//        Thread.sleep(500);
//
//        onView(withId(R.id.onlineList)).check(matches(withText(containsString("HiTesting"))));
//
//
//
//
//
//
//    }










}

