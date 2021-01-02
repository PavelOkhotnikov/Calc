package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calc
{
    public JFrame window = new JFrame("Калькулятор");
    public JTextField input = new JTextField();

    public Calc()
    {
        window.setSize(290, 405);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.green);
        window.setLayout(null);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        enter_area();
        month_button();

        window.setVisible(true);
    }

    private void enter_area()
    {
        input.setFont(new Font("Arial", Font.ROMAN_BASELINE, 24));
        input.setBounds(16, 10, 248, 36);
        input.setBackground(Color.white);
        input.setHorizontalAlignment(JTextField.RIGHT);

        window.add(input);

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher((KeyEventDispatcher) new KeyDispatcher());
    }

    class KeyDispatcher implements KeyEventDispatcher
    {
        public boolean dispatchKeyEvent(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                result();
            }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                input.setText("");
            }
            return false;}}

    private void month_button()
    {
        int num = 0;
        String arr[] = {"1","2","3","С","4","5","6","*","7","8","9","-","0",".","+","/","(",")","="};
        JButton[] jbutton_n = new JButton[arr.length];

        for (int e=0; e<5; e++)
        {
            for (int r=0; r<4; r++)
            {
                jbutton_n[num] = new JButton();
                jbutton_n[num].setFont(new Font("Bold", Font.PLAIN, 36));
                jbutton_n[num].setText(arr[num]);
                jbutton_n[num].setMargin(new Insets(0,0,0,0));
                if (num < arr.length - 1)
                {
                    jbutton_n[num].setBounds(16+r*62, 55+e*62, 60, 60);
                }
                else
                {
                    jbutton_n[num].setBounds(16+r*62, 55+e*62, 122, 60);
                }
                jbutton_n[num].setFocusable(false);

                window.add(jbutton_n[num]);

                ActionListener num_button = new GoNumListener();
                jbutton_n[num].addActionListener(num_button);

                if (num < arr.length - 1)
                {
                    num++;
                }
                else
                {
                    break;}}}}

    public class GoNumListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = ((JButton)e.getSource()).getText();
            if (name == "=" || name == "С")
            {}
            else
            {
                input.setText(input.getText()+name);
            }
            if (name == "=")
            {
                result();
            }
            if (name == "С")
            {
                input.setText("");
            }
            window.repaint();}}

    private void result()
    {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        try
        {
            input.setText("" + engine.eval(input.getText()));
        }
        catch (ScriptException e1)
        {}}

    public static void main(String[] args)
    {
        new Calc();
    }
}