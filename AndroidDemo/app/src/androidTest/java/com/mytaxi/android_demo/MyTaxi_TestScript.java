package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;


import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;






@RunWith(AndroidJUnit4.class)
public class MyTaxi_TestScript {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void loginTest() {

        //validate username text box is displayed
        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        //validate password text box is displayed
        onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        //validate login button is displayed
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));

        // Type username text and validate if the username has been entered
        onView(withId(R.id.edt_username))
                .perform(typeText("crazydog335"), closeSoftKeyboard())
                .check(matches(withText("crazydog335")));

        // Type password text and validate if the password has been entered
        onView(withId(R.id.edt_password))
                .perform(typeText("venture"), closeSoftKeyboard())
                .check(matches(withText("venture")));

        //Click on login button
        onView(withId(R.id.btn_login)).perform(click());


    }

    @Test
    public void searchDriver() {

        //validate search text box is displayed
        onView(withId(R.id.textSearch)).check(matches(isDisplayed()));

        //Enter text in search textbox
        onView(withId(R.id.textSearch))
                .perform(typeText("sa"));


        //Below code is written for the workaround of an existing bug where auto populate list is not generated
        //To generate the list, a backspace is entered and last letter 'a' is typed again to populate the list
        int count = 1;
        boolean result = false;
        while (!result && count < 5) {
            try {
                count++;
                onView(withId(R.id.textSearch))
                        .perform(replaceText("sa"))
                        .perform(click())
                        .perform(pressKey(KeyEvent.KEYCODE_DEL))
                        .perform(typeText("a"));

                onView(withText("Sarah Scott"))
                        .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                        .perform(click());
                result = true;
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    @Test
    public void clickOnCall() {
        onView(withId(R.id.fab))
                .perform(click());
    }

}





