package hr.foi.air1719.personaltracker;


import android.content.SharedPreferences;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    @Rule
    public ActivityTestRule<Main> mActivityTestRule = new ActivityTestRule<>(Main.class);

    @Test
    public void settingsTest() {

        SharedPreferences sp = mActivityTestRule.getActivity().getSharedPreferences("user", 0);
        if(!sp.contains("username")) {

            ViewInteraction appCompatEditText = onView(
                    allOf(withId(R.id.txtLogInUserName),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.constraint.ConstraintLayout")),
                                            0),
                                    1),
                            isDisplayed()));
            appCompatEditText.perform(click());

            ViewInteraction appCompatEditText2 = onView(
                    allOf(withId(R.id.txtLogInUserName),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.constraint.ConstraintLayout")),
                                            0),
                                    1),
                            isDisplayed()));
            appCompatEditText2.perform(replaceText("dv"), closeSoftKeyboard());

            ViewInteraction appCompatEditText3 = onView(
                    allOf(withId(R.id.txtLogInPassword),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.constraint.ConstraintLayout")),
                                            0),
                                    3),
                            isDisplayed()));
            appCompatEditText3.perform(replaceText("123"), closeSoftKeyboard());

            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.btnLogIn), withText("Log in"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.constraint.ConstraintLayout")),
                                            0),
                                    4),
                            isDisplayed()));
            appCompatButton.perform(click());
        }

        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction editText = onView(
                allOf(withId(R.id.inputFullName), withText("Drazen Vuk"),
                        childAtPosition(
                                allOf(withId(R.id.content_account_settings),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("Drazen Vuk")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.inputUserName), withText("dv"),
                        childAtPosition(
                                allOf(withId(R.id.content_account_settings),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                3),
                        isDisplayed()));
        //editText2.check(matches(withText("dv")));




        ViewInteraction editText3 = onView(
                allOf(withId(R.id.inputEmail), withText("drazen0001@gmail.com"),
                        childAtPosition(
                                allOf(withId(R.id.content_account_settings),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                5),
                        isDisplayed()));
        //editText3.check(matches(withText("drazen0001@gmail.com")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
