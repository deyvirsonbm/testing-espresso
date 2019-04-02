package br.com.youse.qa.test.qatestandroid.views.intro;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.youse.qa.test.qatestandroid.R;
import br.com.youse.qa.test.qatestandroid.views.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActvityTest {
    public ActivityTestRule<MainActivity> MainActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void whenUserDoASuccessLogin_shouldDisplayWelcomeState() {
        /*
        * Aqui eu não sei como abrir so a intent main, por conta que precisa de credenciais :/
        * Ainda tentei validar apenas o action bar mas nao consegui :/
        * */
//        MainActivityTestRule.launchActivity(new Intent());
//        onView(withId(R.id.action_bar)).check(matches(isDisplayed()));
    }

}
