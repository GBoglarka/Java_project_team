
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProtoTest {
    Proto proto;
    public static void assertReaders(BufferedReader expected, BufferedReader actual) throws IOException {
        String expectedLine;
        while ((expectedLine = expected.readLine()) != null) {
            String actualLine = actual.readLine();
            assertNotNull(actualLine, "Expected had more lines then the actual.");
            assertEquals(expectedLine, actualLine);
        }
        assertNull(actual.readLine(), "Actual had more lines then the expected." );
    }

    //baj van, mert nincs airFreshener
    @Test
    void Teszt1_init() {
        proto = new Proto("teszt1_out.txt");
        proto.reading_from_file("teszt1_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt1_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt1_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt2_student_move() {
        proto = new Proto("teszt2_out.txt");
        proto.reading_from_file("teszt2_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt2_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt2_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt3_instructor_move() {
        proto = new Proto("teszt3_out.txt");
        proto.reading_from_file("teszt3_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt3_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt3_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt4_cleaner_move() {
        proto = new Proto("teszt4_out.txt");
        proto.reading_from_file("teszt4_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt4_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt4_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt5_Student_move_into_poisoned_room_unprotected() {
        proto = new Proto("teszt5_out.txt");
        proto.reading_from_file("teszt5_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt5_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt5_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt6_Student_move_into_poisoned_room_protected() {
        proto = new Proto("teszt6_out.txt");
        proto.reading_from_file("teszt6_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt6_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt6_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt7_Student_move_into_sticky_room() {
        proto = new Proto("teszt7_out.txt");
        proto.reading_from_file("teszt7_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt7_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt7_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt8_Instructor_move_into_sticky_room() {
        proto = new Proto("teszt8_out.txt");
        proto.reading_from_file("teszt8_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt8_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt8_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt9_Instructor_move_into_poisoned_room() {
        proto = new Proto("teszt9_out.txt");
        proto.reading_from_file("teszt9_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt9_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt9_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt10_Cleaner_move_into_stickyroom() {
        proto = new Proto("teszt10_out.txt");
        proto.reading_from_file("teszt10_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt10_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt10_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt11_Cleaner_move_into_poisoned_room() {
        proto = new Proto("teszt11_out.txt");
        proto.reading_from_file("teszt11_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt11_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt11_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt12_Cleaner_move_into_occupied_room() {
        proto = new Proto("teszt12_out.txt");
        proto.reading_from_file("teszt12_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt12_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt12_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt13_Student_meets_instructor_unprotected() {
        proto = new Proto("teszt13_out.txt");
        proto.reading_from_file("teszt13_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt13_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt13_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt14_Student_meets_instructor_protected() {
        proto = new Proto("teszt14_out.txt");
        proto.reading_from_file("teszt14_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt14_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt14_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt15_MergeRoom() {
        proto = new Proto("teszt15_out.txt");
        proto.reading_from_file("teszt15_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt15_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt15_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt16_SortRoom() {
        proto = new Proto("teszt16_out.txt");
        proto.reading_from_file("teszt16_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt16_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt16_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt17_Student_PickupItem() {
        proto = new Proto("teszt17_out.txt");
        proto.reading_from_file("teszt17_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt17_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt17_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt18_Instructor_PickupItem() {
        proto = new Proto("teszt18_out.txt");
        proto.reading_from_file("teszt18_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt18_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt18_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void Teszt19_Student_Transistor_Ability() {
        proto = new Proto("teszt19_out.txt");
        proto.reading_from_file("teszt19_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt19_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt19_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void Teszt20_Student_TVSZ_Ability() {
        proto = new Proto("teszt20_out.txt");
        proto.reading_from_file("teszt20_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt20_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt20_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt21_Student_Fake_TVSZ_Ability() {
        proto = new Proto("teszt21_out.txt");
        proto.reading_from_file("teszt21_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt21_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt21_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt22_Student_Rag_Ability() {
        proto = new Proto("teszt22_out.txt");
        proto.reading_from_file("teszt22_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt22_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt22_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt23_Student_Logarlec_Ability() {
        proto = new Proto("teszt23_out.txt");
        proto.reading_from_file("teszt23_in.txt");
        Controller.setGameOver(false);
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt23_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt23_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt24_Student_Fake_Logarlec_Ability() {
        proto = new Proto("teszt24_out.txt");
        proto.reading_from_file("teszt24_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt24_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt24_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt25_Student_Camamber_Ability() {
        proto = new Proto("teszt25_out.txt");
        proto.reading_from_file("teszt25_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt25_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt25_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt26_Student_Fake_FFP2_Ability() {
        proto = new Proto("teszt26_out.txt");
        proto.reading_from_file("teszt26_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt26_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt26_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt27_Student_Beer_Ability() {
        proto = new Proto("teszt27_out.txt");
        proto.reading_from_file("teszt27_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt27_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt27_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt28_Student_AirFreshener_Ability() {
        proto = new Proto("teszt28_out.txt");
        proto.reading_from_file("teszt28_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt28_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt28_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt29_WickedRoom_Roomspecial_Door_Appear() {
        proto = new Proto("teszt29_out.txt");
        proto.reading_from_file("teszt29_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt29_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt29_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void Teszt30_WickedRoom_Roomspecial_Door_Disappear() {
        proto = new Proto("teszt30_out.txt");
        proto.reading_from_file("teszt30_in.txt");
        try {
            BufferedReader elv = new BufferedReader(new FileReader("teszt30_exp.txt"));
            BufferedReader kim = new BufferedReader(new FileReader("teszt30_out.txt"));
            assertReaders(elv, kim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
