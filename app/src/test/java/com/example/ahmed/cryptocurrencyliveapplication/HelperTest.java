package com.example.ahmed.cryptocurrencyliveapplication;


import android.content.Context;
import android.test.ActivityTestCase;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class HelperTest extends ActivityTestCase {

    @Mock
    Context mMockContext;

    @Test
    public void formateDoubleTest() throws Exception {
        assertEquals(Helper.formateDouble(1.1),"1.10000");
        assertEquals(Helper.formateDouble(0),"0.00000");
        assertEquals(Helper.formateDouble(1.111111),"1.11111");
        assertEquals(Helper.formateDouble(-1.1),"-1.10000");

    }
}