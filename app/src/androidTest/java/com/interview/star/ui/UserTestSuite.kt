package com.interview.star.ui

import com.interview.star.ui.detail.DetailFragmentTest
import com.interview.star.ui.home.HomeFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ApplicationContextTest::class,
    DetailFragmentTest::class,
    HomeFragmentTest::class,
)

// Start testing for all app
class UserTestSuite