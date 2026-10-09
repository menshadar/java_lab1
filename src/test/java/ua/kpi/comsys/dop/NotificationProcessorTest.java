package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NotificationProcessorTest {

    private final NotificationProcessor processor = new NotificationProcessor();

    @Test
    void processesTextMsg() {
        String expected = """
                Notification: TEXT
                From: Alice
                Message: Hello!
                """;
        assertEquals(expected, processor.process(new TextMsg("Alice", "Hello!")));
    }

    @Test
    void processesImageMsg() {
        String expected = """
                Notification: IMAGE
                From: Bob
                URL: http://img.com/a.png (250 KB)
                """;
        assertEquals(expected, processor.process(new ImageMsg("Bob", "http://img.com/a.png", 250)));
    }

    @Test
    void processesAudioMsg() {
        String expected = """
                Notification: AUDIO
                From: Carol
                Duration: 42 seconds
                """;
        assertEquals(expected, processor.process(new AudioMsg("Carol", 42)));
    }

    @Test
    void nullNotificationThrows() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> processor.process(null));
        assertEquals("Notification cannot be null", ex.getMessage());
    }

    @Test
    void textMsgValidation() {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg(null, "x"));
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("", "x"));
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("  ", "x"));
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("a", null));
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("a", ""));
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("a", "   "));
    }

    @Test
    void imageMsgValidation() {
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg(null, "u", 1));
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg(" ", "u", 1));
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("a", null, 1));
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("a", " ", 1));
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("a", "u", 0));
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("a", "u", -5));
    }

    @Test
    void audioMsgValidation() {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg(" ", 1));
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg("a", 0));
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg("a", -1));
    }
}
