package de.n26.codechallenge.messagebuffer;

import de.n26.codechallenge.AppRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;


@ContextConfiguration(classes = MessageBuffer.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageBufferTest {

    @Autowired
    private MessageBuffer buffer;

    @Test
    public void checkSizeInTime() throws InterruptedException {
        Map messages = buffer.getBuffer();
        assertEquals(messages.size(),60000);
        Thread.currentThread().sleep(5000);
        assertEquals(messages.size(),60000);
    }


}
