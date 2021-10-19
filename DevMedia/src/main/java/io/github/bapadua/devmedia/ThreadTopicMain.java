package io.github.bapadua.devmedia;

import java.util.logging.Logger;

public class ThreadTopicMain {
    static Logger log = Logger.getLogger(ThreadTopicMain.class.getName());
    int x;
    int y;

    public static void main(String[] args) {
        ThreadTopicMain t = new ThreadTopicMain();
        t.sessaoCritica();
        System.out.printf("x=%d y=%d%n", t.x, t.y);
    }

    public void sessaoCritica() {
        synchronized (this) {
            x = x + y;
            y = x + y + 1;
        }
    }
}
