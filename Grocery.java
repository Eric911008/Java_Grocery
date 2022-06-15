package com.company.final_project_test2;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Grocery extends JFrame {

    protected ArrayList<Food> shopList;
    protected ArrayList<Integer> total;
    protected static final int MAX = 10;
    private Container cp;
    private JLabel typeLabel, numberLabel, inputLabel;
    private JComboBox types;
    private JTextField price, quantity;
    private JButton addButton, clrButton, modButton, searchButton,calButton ,printButton;
    private JTextArea textarea;
    private final String[] items = {"apple", "orange","beef","pork","butter","cheese"};

    public Grocery(){
        this.shopList = new ArrayList<Food>(10);
        this.total = new ArrayList<Integer>(10);
        setTitle("Grocery Store");
        setSize(700, 350);
        setLocation(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        types = new JComboBox(items);
        typeLabel = new JLabel("Product");
        numberLabel = new JLabel("Price");
        price = new JTextField(20);
        price.setText("20");
        price.setEditable(false);
        inputLabel = new JLabel("Quantity");
        quantity = new JTextField(20);
        addButton = new JButton("Add");
        clrButton = new JButton("Clear");
        modButton = new JButton("Modify");
        searchButton = new JButton("Search");
        calButton = new JButton("calculate Sum");
        printButton = new JButton("Print Receipt");
        textarea = new JTextArea(10,15);
        textarea.setEditable(false);

        cp = getContentPane();
        cp.setLayout(new GridLayout(1,2));
        JPanel newp = new JPanel();
        newp.setLayout(new BorderLayout(5,10));
        cp.add(newp);
        cp.add(new JScrollPane(textarea));

        JPanel cpan = new JPanel();
        cpan.setLayout(new GridLayout(3,2,15,10));
        cpan.add(typeLabel);
        cpan.add(types);
        cpan.add(numberLabel);
        cpan.add(price);
        cpan.add(inputLabel);
        cpan.add(quantity);

        JPanel span = new JPanel();
        span.setLayout(new GridLayout(3,2,15,10));
        span.add(addButton);
        span.add(clrButton);
        span.add(modButton);
        span.add(searchButton);
        span.add(calButton);
        span.add(printButton);
        newp.add(cpan, BorderLayout.NORTH);
        newp.add(span, BorderLayout.SOUTH);
        // register
        types.addItemListener(new ListHandler());
        addButton.addActionListener(new AddButtonHandler());
        clrButton.addActionListener(new ClrButtonHandler());
        modButton.addActionListener(new ModButtonHandler());
        searchButton.addActionListener(new SearchButtonHandler());
        calButton.addActionListener(new CalButtonHandler());
        printButton.addActionListener(new PrintButtonHandler());
    }

    private class ListHandler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if(types.getSelectedIndex() == 0 || types.getSelectedIndex() == 1) {
                price.setText("20");
            }
            if(types.getSelectedIndex() == 2 || types.getSelectedIndex() == 3) {
                price.setText("100");
            }
            if(types.getSelectedIndex() == 4 || types.getSelectedIndex() == 5) {
                price.setText("35");
            }
        }
    }

    private class AddButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String n = types.getName();
            int h = Integer.parseInt(quantity.getText());
            Food f = null;
            int i = types.getSelectedIndex();
            if( i == 0 ) {
                f = new Fruit(items[0],h);
                shopList.add(f);
                total.add(f.calFee());
            }
            if( i == 1 ) {
                f = new Fruit(items[1],h);
                shopList.add(f);
                total.add(f.calFee());
            }
            if( i == 2 ) {
                f = new Meat(items[2],h);
                shopList.add(f);
                total.add(f.calFee());
            }
            if( i == 3 ) {
                f = new Meat(items[3],h);
                shopList.add(f);
                total.add(f.calFee());
            }
            if( i == 4 ) {
                f = new Dairy(items[4],h);
                shopList.add(f);
                total.add(f.calFee());
            }
            if( i == 5 ) {
                f = new Dairy(items[5],h);
                shopList.add(f);
                total.add(f.calFee());
            }

            textarea.append("product name: " + types.getSelectedItem() +",  quantity:" + h
                    + ",  amount: " +f.calFee() + "\n" );
            textarea.setCaretPosition(textarea.getDocument().getLength());
        }
    }

    private class ClrButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            quantity.setText("");
            textarea.setText("");
            shopList.removeAll(shopList);
            total.removeAll(total);
        }
    }

    private class ModButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Enter the number of items to edit your list "));
            try {
                int y = textarea.getLineStartOffset(x);
                int z = textarea.getLineEndOffset(x);
                textarea.replaceRange(null, y , z);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
            shopList.remove(x);
            total.remove(x);
        }
    }

    private class SearchButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int y = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Enter the number of items to search for details "));
            JOptionPane.showMessageDialog(null,shopList.get(y).getItemInfo());
        }
    }

    private class CalButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double sum = 0;
            for(int i = 0; i < total.size(); i++)
                sum += total.get(i);
            textarea.append("\n" + "                     TOTAL AMOUNT: " + sum + "\n");
            textarea.setCaretPosition(textarea.getDocument().getLength());
        }
    }

    private class PrintButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter("Receipt.txt", false));
                textarea.write(writer);
                writer.write("\n" + "****************** THANK YOU ******************");
                writer.close();
                JOptionPane.showMessageDialog(null, "File has been saved",
                        "File Saved",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error occurred");
                e1.printStackTrace();
            }
        }
    }
}





