package de.bib.pbg2h15a;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame {
    public static void main (String[] args) {
        new LwjglApplication(new Game(), "Elements of Alchemy", 1000, 750);
    }
}
