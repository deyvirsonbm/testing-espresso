package br.com.youse.qa.test.qatestandroid.views.intro;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.youse.qa.test.qatestandroid.R;
import br.com.youse.qa.test.qatestandroid.views.login.LoginActivity;
import br.com.youse.qa.test.qatestandroid.views.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class, false, false);

    @Test
    public void whenLoginActivityIsLaunched_shouldDisplayInitialState() {
        loginActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.nextBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

    @Test
    public void whenEmailIsEmpty_shouldDisplayAnError(){
        loginActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withText("Email Inválido")).check(matches(isDisplayed()));
    }

    @Test
    public void whenEmailIsWrong_shouldDisplayAnError(){
        loginActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.email)).perform(typeText("a@a.com.br"), closeSoftKeyboard());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withText("Email Inválido")).check(matches(isDisplayed()));
    }

    @Test
    public void whenEmailIsCorrect_adPwIsEmpty_ShouldDisplayAnError(){
        loginActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.email)).perform(typeText("youser1@youse.com.br"), closeSoftKeyboard());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withText("Senha Inválida")).check(matches(isDisplayed()));
    }

    @Test
    public void whenEmailIsCorrect_adPwIsWrong_ShouldDisplayAnError(){
        loginActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.email)).perform(typeText("youser1@youse.com.br"), closeSoftKeyboard());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.password)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withText("Senha Inválida")).check(matches(isDisplayed()));
    }

    @Test
    public void whenEmailAndPwCorrect_shouldOpenMainActivity(){
        Intents.init();

        loginActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.email)).perform(typeText("youser1@youse.com.br"), closeSoftKeyboard());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        Matcher<Intent> matcher = hasComponent(MainActivity.class.getName());

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);
        intending(matcher).respondWith(result);
        onView(withId(R.id.loginBtn)).perform(click());

        intended(matcher);
        Intents.release();
    }

}
