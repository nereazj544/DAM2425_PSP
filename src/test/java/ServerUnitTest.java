import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class ServerUnitTest {

    @SuppressWarnings("resource")
    @Test
    @DisplayName("No se envía petición")
    void test01() {
        try (Socket socket = new Socket("localhost", 9000)) {
            socket.setSoTimeout(10000);
            assertTrue(new DataInputStream(socket.getInputStream()).readUTF().startsWith("ERROR:"));
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("resource")
    @Test
    @DisplayName("Petición vacía")
    void test02() {
        try (Socket socket = new Socket("localhost", 9000)) {
            socket.setSoTimeout(10000);
            socket.shutdownOutput();
            assertTrue(new DataInputStream(socket.getInputStream()).readUTF().startsWith("ERROR:"));
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("resource")
    @Test
    @DisplayName("No se envía EOF")
    void test03() {
        try (Socket socket = new Socket("localhost", 9000)) {
            socket.setSoTimeout(10000);
            new DataOutputStream(socket.getOutputStream()).writeUTF("hola");
            assertTrue(new DataInputStream(socket.getInputStream()).readUTF().startsWith("ERROR:"));
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("resource")
    @Test
    @DisplayName("Petición correcta")
    void test05() {
        try (Socket socket = new Socket("localhost", 9000)) {
            socket.setSoTimeout(10000);
            new DataOutputStream(socket.getOutputStream()).writeUTF("hola");
            socket.shutdownOutput();
            assertEquals("hola", new DataInputStream(socket.getInputStream()).readUTF());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

}