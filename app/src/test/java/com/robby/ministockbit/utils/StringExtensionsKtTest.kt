package com.robby.ministockbit.utils

import junit.framework.TestCase
import org.junit.Assert

class StringExtensionsKtTest : TestCase() {
    fun testFormatDoubleWithPrefix() {
        var dollar = "12.9122222222223333"
        Assert.assertEquals("+13.0", dollar.formatDoubleWithPrefix() )
    }
}