package com.sistema.util;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Tema {
    public static void aplicarTemaDark() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
