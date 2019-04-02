package br.com.youse.qa.test.qatestandroid.views.intro;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.youse.qa.test.qatestandroid.R;
import br.com.youse.qa.test.qatestandroid.views.login.LoginActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class IntroActivityTest {
    public ActivityTestRule<IntroActivity> introActivityTestRule = new ActivityTestRule<>(IntroActivity.class, false, false);

    @Test
    public void WhenOnFirstScreen_ShouldBeVisible_Number_1() {
        introActivityTestRule.launchActivity(new Intent());
        onView(withText("Olá")).check(matches(isDisplayed()));
        onView(withText("1")).check(matches(isDisplayed()));
    }
    @Test
    public void WhenOnSecondScreen_ShouldBeVisible_Number_2() {
        introActivityTestRule.launchActivity(new Intent());
        onView(ViewMatchers.withId(R.id.pager)).perform(swipeLeft());
        onView(withText("Bem Vindo!")).check(matches(isDisplayed()));
        onView(withText("2")).check(matches(isDisplayed()));
    }

    @Test
    public void WhenOnThirdScreen_ShouldBeVisible_Number_3_And_AccessButton() {
        introActivityTestRule.launchActivity(new Intent());
        Intents.init();

        onView(ViewMatchers.withId(R.id.pager)).perform(swipeLeft());
        onView(ViewMatchers.withId(R.id.pager)).perform(swipeLeft());

        Matcher<Intent> matcher = hasComponent(LoginActivity.class.getName());

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);
        intending(matcher).respondWith(null);
        onView(allOf(withId(R.id.accessBtn), hasSibling(withText("3")))).perform(click());
        Intents.release();
        }

    }


