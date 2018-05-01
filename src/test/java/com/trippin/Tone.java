package com.trippin;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.junit.Test;

public class Tone {

    public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz

    @Test
    public void test()
        throws LineUnavailableException {

        byte[] engineNote = makeEngineNote();
        AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.SAMPLE_RATE);
        line.start();
        for (int i = 15; i > 0; i--) {
            byte[] b = removeEvery(engineNote, i + 5);
            line.write(b, 0, b.length);
        }
        line.drain();
        line.close();
    }

    private byte[] removeEvery(byte[] original, int step) {

        int steps = original.length / step;
        int destStep = step - 1;
        int srcPos = 0;
        int destPos = 0;

        byte[] dest = new byte[steps * destStep];
        for (int i = 0; i < steps; i++) {
            System.arraycopy(original, srcPos, dest, destPos, destStep);
            srcPos += step;
            destPos += destStep;
        }

        return dest;
    }

    private byte[] makeEngineNote() {

        byte[] b = new byte[SAMPLE_RATE / 10];

        double f = 80d;
        double period = (double) SAMPLE_RATE / f;
        for (int i = 0; i < b.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            b[i] = (byte) (Math.sin(angle) * 127f);
            b[i] = (byte) (b[i] - (7 * Math.random()));
        }

        return b;
    }

    private static void play(SourceDataLine line, Note note, int ms) {
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.SAMPLE_RATE * ms / 1000;
        line.write(note.data(), 0, length);
    }
}



enum Note {

    REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5;
    public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
    public static final int SECONDS = 2;
    private byte[] sin = new byte[SECONDS * SAMPLE_RATE];

    Note() {
        int n = this.ordinal();
        if (n > 0) {
            double exp = ((double) n - 1) / 12d;
            double f = 440d * Math.pow(2d, exp);
            for (int i = 0; i < sin.length; i++) {
                double period = (double) SAMPLE_RATE / f;
                double angle = 2.0 * Math.PI * i / period;
                sin[i] = (byte) (Math.sin(angle) * 127f);
            }
        }
    }

    public byte[] data() {
        return sin;
    }
}
