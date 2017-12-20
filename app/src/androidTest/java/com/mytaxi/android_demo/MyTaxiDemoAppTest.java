package com.mytaxi.android_demo;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

/**
 * Created by Suraj Salunkhe on 16/12/17.
 * This Test Will Login To App and Search For Driver And Call in Dialer
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MyTaxiDemoAppTest extends CommonMyTaxiAppSteps {

    //UI element Mapping
    private static final ViewInteraction DRIVER_NAME_SEARCH_EDIT=onView(ViewMatchers.withId(R.id.textSearch));
    private static final ViewInteraction CALL_DRIVER_BUTTON = onView(withId(R.id.fab));
    private static final ViewInteraction DRIVER_PROFILE_NAME= onView(withId(R.id.textViewDriverName));

    @Test
    public void SignInMyTaxiApp(){
        useAppContext(); //verifies app under test is same
        doLogin(); //perform login
    }

    @Test
    public void SeachAndCallDriver() throws Exception {

        addDelay(10000);

        //Searching for Driver
        DRIVER_NAME_SEARCH_EDIT.perform(typeText(SEARCHKEY), closeSoftKeyboard());
        addDelay(10000);

        //Check Desired Driver is present
        onView(withText(DRIVERNAME))
                .inRoot(withDecorView(Matchers.not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        //Selecting the desired driver
        onView(withText(DRIVERNAME))
                .inRoot(withDecorView(Matchers.not(is(mActivity.getWindow().getDecorView()))))
                .perform(scrollTo()).perform(click());

        addDelay(10000);

        //Confirm Driver Name
        DRIVER_PROFILE_NAME.check(matches(withText(DRIVERNAME)));

        addDelay(10000);
        //Call the driver
        CALL_DRIVER_BUTTON.perform(click());

    }//End of Test Seach And Call Driver

}//End of class
