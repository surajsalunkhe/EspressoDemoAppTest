package com.mytaxi.android_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by Suraj Salunkhe on 16/12/17.
 * This Is Common Steps file in which permission and common Steps are Written
 * Just extend the class CommonMyTaxiAppSteps for using functionality
 */

public class CommonMyTaxiAppSteps implements UserDetails {

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    //Set activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public MainActivity mActivity = null;

    @Before
    public void setActivity() {
        //Set the Activity
        mActivity = mActivityRule.getActivity();

    }

    //UI element Mapping
    private static final ViewInteraction USERNAME_EDIT = onView(withId(R.id.edt_username));
    private static final ViewInteraction PASSWORD_EDIT = onView(withId(R.id.edt_password));
    private static final ViewInteraction LOGIN_BUTTON = onView(withId(R.id.btn_login));

    public void typeUsername(String username) {
        /* clearText() implemented before typeText().
        * This is considered to be a 'safe' operation, since in the case there is text already there
        * it will be deleted first, preventing the test from returning a failure for a different scenario.*/

        USERNAME_EDIT.perform(clearText(), typeText(username));
    }

    public void typePassword(String password) {
        PASSWORD_EDIT.perform(clearText(), typeText(password));
    }

    public void clickLoginButton() {
        LOGIN_BUTTON.perform(click());
    }

    public void doLogin() {
        typeUsername(USERNAME);
        typePassword(PASSWORD);
        clickLoginButton();
    }

    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());
    }

    public void addDelay(int number) throws Exception{
        Thread.sleep(number);
    }
}
