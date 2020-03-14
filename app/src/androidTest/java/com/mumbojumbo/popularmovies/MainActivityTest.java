package com.mumbojumbo.popularmovies;

import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule activityTestRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void tootbarTitle(){
        onView(
        allOf(isAssignableFrom(TextView.class),
        withParent(isAssignableFrom(Toolbar.class)))
        ).check(matches(withText(R.string.app_name)));
    }

    @Test
    public void onCreate() {
        openActionBarOverflowOrOptionsMenu(activityTestRule.getActivity());
        onView(withText(R.string.menu_sort_by_popularity));
        onView(withText(R.string.menu_sort_by_rating));

    }

    @Test
    public void recyclerViewTest(){
       onView(withId(R.id.rv_movie_posters))
               .perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

       onView(withText(R.string.synopsis));
       onView(withText(R.string.user_rating));
       onView(withText(R.string.release_date));
    }
}