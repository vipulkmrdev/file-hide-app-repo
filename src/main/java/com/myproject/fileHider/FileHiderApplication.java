package com.myproject.fileHider;

import com.myproject.fileHider.views.Welcome;

public class FileHiderApplication {

    public static void main(String[] args){
        Welcome w = new Welcome();
        do {
            w.welcomeScreen();
        } while (true);
    }

}
