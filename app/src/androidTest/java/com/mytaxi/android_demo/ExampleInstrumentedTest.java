package com.mytaxi.android_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.matcher.RootMatchers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Rule;


import com.mytaxi.android_demo.activities.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String mUsername;
    private String mPassword;
    private String mSearchInput;
    private String mSearchedName;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

//    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
//            MainActivity.class, true, false);

    @Before
    public void initValidString() {
        // init test params
        mUsername = "crazydog335";
        mPassword = "venture";
        mSearchInput = "sa";
        mSearchedName = "Sarah Scott";

    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());
    }

    @Test
    public void testMakingCall() throws Exception {
        try {
            // check the app
            Espresso.onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
            // View is displayed
        } catch (NoMatchingViewException e) {
            fail("Can not find the Login View, please make sure the myTaxi app is logged out before running the test");
        }

        // Click the username input and type the username
        Espresso.onView(withId(R.id.edt_username))
                .perform(typeText(mUsername), closeSoftKeyboard());

        // Click the password input and type the credential
        Espresso.onView(withId(R.id.edt_password))
                .perform(typeText(mPassword), closeSoftKeyboard());

        // Click login button
        Espresso.onView(withId(R.id.btn_login)).perform(click());

        // type in search name input box and input partial 'sa'
        Espresso.onView(withId(R.id.textSearch))
                .perform(typeText(mSearchInput), closeSoftKeyboard());

        // Check that 'Sarah Scott' is displayed.
        Espresso.onView(withText(mSearchedName))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        // match the 'Sarah Scott' and click the row where the name is
        Espresso.onView(withText(mSearchedName))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());

        //  click the call button on driver's profile page
        Espresso.onView(withId(R.id.fab)).perform(click());

    }


}
