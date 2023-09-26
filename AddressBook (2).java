import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

class LinkList {
    person head;

    class person {
        String name;
        String number;
        String email;
        String address;
        person next;

        person(String name, String number, String email, String address) {
            this.name = name;
            this.number = number;
            this.email = email;
            this.address = address;
            next = null;
        }
    }

    void insert(LinkList list, String name, String number, String email, String address) {
        person p = new person(name, number, email, address);
        if (list.head == null) {
            head = p;
        } else {
            person last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = p;
        }
    }
}

public class AddressBook implements ActionListener {
    JButton Add;
    JButton Delete;
    JButton ALLContacts;
    JButton search;

    JTextField Sbar;
    JFrame f4;

    LinkList list = new LinkList();
    File f = new File("contactbook1.txt");
    LinkList.person last1 = null;
    LinkList.person last = null;
    FileWriter fw = null;
    FileReader fr = null;
    int c = 0;

    // JLabel ADD,DELETE,ALLCONTACTS;
    public static void main(String args[]) throws Exception {
        AddressBook b = new AddressBook();

        b.gui();

    }

    public void gui() throws Exception {

        if (f.length() != 0) {
            fr = new FileReader(f);
            int i;

            boolean val = true;

            while (val) {
                String name1 = new String();
                String number1 = new String();
                String email1 = new String();
                String address1 = new String();
                while ((i = fr.read()) != -1) {
                    if ((char) i == '-')
                        break;
                    else
                        name1 = name1 + (char) i;
                }
                while ((i = fr.read()) != -1) {
                    if ((char) i == '-')
                        break;
                    else
                        number1 = number1 + (char) i;
                }
                while ((i = fr.read()) != -1) {

                    if ((char) i == '-')
                        break;
                    else
                        email1 = email1 + (char) i;
                }
                while ((i = fr.read()) != -1) {
                    if ((char) i == '-') {
                        fr.read();
                        break;
                    } else
                        address1 = address1 + (char) i;
                }
                if (i == -1) {
                    val = false;
                    break;
                } else {
                    list.insert(list, name1, number1, email1, address1);
                    c++;
                }
            }
            last = list.head;
            while (last != null) {
                System.out.println(last.name);
                last = last.next;
            }

        }

        JFrame f1 = new JFrame("AddressBook");
        f1.getContentPane().setBackground(Color.orange);
        Add = new JButton("ADD");
        Delete = new JButton("DELETE");
        ALLContacts = new JButton("ALLCONTACTS");
        Add.setBounds(20, 20, 140, 20);
        Delete.setBounds(180, 20, 140, 20);
        ALLContacts.setBounds(340, 20, 140, 20);
        Sbar = new JTextField();
        search = new JButton("Search");
        search.setBounds(240, 100, 80, 30);

        Sbar.setBounds(20, 100, 200, 30);
        Sbar.setColumns(15);

        f1.add(Add);

        f1.add(ALLContacts);
        f1.add(Sbar);
        f1.add(search);

        Add.addActionListener(this);
        Delete.addActionListener(this);
        ALLContacts.addActionListener(this);
        search.addActionListener(this);

        f1.setLayout(null);
        f1.setSize(500, 300);
        f1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (Add == obj) {
            JTextField t1;
            JTextField t2;
            JTextField t3;
            JTextField t4;
            JFrame f2 = new JFrame("ADD CONTACT");
            f2.getContentPane().setBackground(Color.CYAN);
            f2.setLocation(200, 200);
            JLabel l1 = new JLabel("Enter Name:");
            l1.setBounds(25, 100, 100, 30);
            f2.add(l1);
            JLabel l2 = new JLabel("Enter Phone Number");
            l2.setBounds(25, 150, 150, 30);
            f2.add(l2);
            JLabel l3 = new JLabel("Enter Email");
            l3.setBounds(25, 200, 100, 30);
            f2.add(l3);
            JLabel l4 = new JLabel("Enter Address");
            l4.setBounds(25, 250, 100, 30);
            f2.add(l4);

            t1 = new JTextField();
            t1.setBounds(150, 100, 150, 30);
            f2.add(t1);
            t2 = new JTextField();
            t2.setBounds(150, 150, 150, 30);
            f2.add(t2);
            t3 = new JTextField();
            t3.setBounds(150, 200, 150, 30);
            f2.add(t3);
            t4 = new JTextField();
            t4.setBounds(150, 250, 150, 30);
            f2.add(t4);
            t3.setEnabled(false);
            t4.setEnabled(false);
            JButton yes1 = new JButton("yes");
            f2.add(yes1);
            yes1.setBounds(320, 200, 60, 30);
            yes1.addActionListener((ActionListener) new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    t3.setEnabled(true);
                }
            });

            JButton yes2 = new JButton("yes");
            f2.add(yes2);
            yes2.setBounds(320, 250, 60, 30);
            yes2.addActionListener((ActionListener) new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    t4.setEnabled(true);
                }
            });

            f2.setLayout(null);
            f2.setVisible(true);
            f2.setSize(500, 500);
            JButton save = new JButton("SAVE");
            f2.add(save);
            save.setBounds(100, 350, 80, 25);
            save.setBackground(Color.GREEN);
            save.setForeground(Color.BLACK);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean val = false;
                    String namecheck = t1.getText();
                    String numbercheck = t2.getText();
                    if (list.head != null) {
                        LinkList.person last = list.head;
                        while (last != null) {
                            if (namecheck.equals(last.name)) {
                                val = true;

                                JOptionPane.showMessageDialog(null, "THE GIVEN NAME ALREADY EXISTS");
                                break;
                            }
                            if (numbercheck.equals(last.number)) {
                                val = true;
                                JOptionPane.showMessageDialog(null, "THE GIVEN NUMBER ALREADY EXISTS");
                                break;
                            }
                            last = last.next;
                        }
                    }
                    if (namecheck.length() == 0) {
                        val = true;
                        JOptionPane.showMessageDialog(null, "NAME CANNOT BE EMPTY");
                    }
                    if (numbercheck.length() != 10) {
                        val = true;
                        JOptionPane.showMessageDialog(null, "ENTER 10 DIGITS ONLY");
                    }
                    for (int a = 0; a < 10; a++) {
                        if (!(Character.isDigit(numbercheck.charAt(a)))) {
                            val = true;
                            JOptionPane.showMessageDialog(null, "ENTER NUMBERS ONLY");
                            break;
                        }
                    }

                    // person pe = new person();
                    if (!val) {

                        String name1 = t1.getText();
                        String number1 = t2.getText();
                        String email1 = t3.getText();
                        String address1 = t4.getText();
                        list.insert(list, name1, number1, email1, address1);

                        try {
                            fw = new FileWriter(f);
                            LinkList.person last = list.head;
                            while (last != null) {
                                fw.write(last.name + "-");
                                fw.write(last.number + "-");
                                fw.write(last.email + "-");
                                fw.write(last.address + "-;");
                                last = last.next;
                            }

                            fw.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "CONTACT SAVED");
                        c++;
                        f2.dispose();
                    }

                }
            });
        }

        else if (obj == Delete) {

            if (last == list.head) {
                if (last.next == null)
                    list.head = null;
                else {
                    list.head = last.next;
                    last.next = null;
                }
                try {
                    fw = new FileWriter(f);
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            } else {
                last1.next = last.next;
                last.next = null;
            }
            try {
                fw = new FileWriter(f);
                LinkList.person last2 = list.head;
                while (last2 != null) {
                    fw.write(last2.name + "-");
                    fw.write(last2.number + "-");
                    fw.write(last2.email + "-");
                    fw.write(last2.address + "-");
                    last2 = last2.next;
                }

                fw.close();
            } catch (IOException e1) {

                e1.printStackTrace();

            }

            JOptionPane.showMessageDialog(null, "number has been deleted");
            c--;
            f4.dispose();

        }

        else if (obj == search) {
            String name2 = Sbar.getText();
            last = list.head;
            last1 = list.head;
            boolean val = false;
            while (last != null && val == false) {
                // System.out.println(last.name + "==" + name2);
                if ((last.name).equals(name2) || (last.number).equals(name2)) {
                    System.out.println(last.name + "  " + name2);

                    f4 = new JFrame(name2);
                    f4.getContentPane().setBackground(Color.CYAN);
                    f4.setLocation(200, 200);
                    JLabel l1 = new JLabel("Name is " + last.name);
                    l1.setBounds(200, 100, 150, 30);
                    f4.add(l1);
                    JLabel l2 = new JLabel("Number is " + last.number);
                    l2.setBounds(200, 150, 150, 30);
                    f4.add(l2);
                    JLabel l3 = new JLabel("Email is " + last.email);
                    l3.setBounds(200, 200, 150, 30);
                    f4.add(l3);
                    JLabel l4 = new JLabel("Address is " + last.address);
                    l4.setBounds(200, 250, 150, 30);
                    f4.add(l4);

                    f4.setLayout(null);
                    f4.setVisible(true);
                    f4.setSize(500, 500);
                    val = true;
                    f4.add(Delete);
                    JButton edit = new JButton("EDIT CONTACT ");
                    edit.setBounds(40, 350, 180, 40);
                    Delete.setBounds(300, 350, 100, 40);
                    f4.add(edit);
                    edit.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            JTextField t1;
                            JTextField t2;
                            JTextField t3;
                            JTextField t4;

                            JFrame f2 = new JFrame("EDIT CONTACT");
                            f2.getContentPane().setBackground(Color.CYAN);
                            f2.setLocation(200, 200);
                            JLabel l1 = new JLabel("Name ");
                            l1.setBounds(25, 100, 100, 30);
                            f2.add(l1);
                            JLabel l2 = new JLabel(" Phone Number ");
                            l2.setBounds(25, 150, 150, 30);
                            f2.add(l2);
                            JLabel l3 = new JLabel(" Enter Email ");
                            l3.setBounds(25, 200, 100, 30);
                            f2.add(l3);
                            JLabel l4 = new JLabel(" Enter Address ");
                            l4.setBounds(25, 250, 100, 30);
                            f2.add(l4);

                            t1 = new JTextField(last.name);
                            t1.setBounds(150, 100, 150, 30);
                            f2.add(t1);
                            t2 = new JTextField(last.number);
                            t2.setBounds(150, 150, 150, 30);
                            f2.add(t2);
                            t3 = new JTextField(last.email);
                            t3.setBounds(150, 200, 150, 30);
                            t3.setEnabled(false);
                            f2.add(t3);
                            t4 = new JTextField(last.address);
                            t4.setBounds(150, 250, 150, 30);
                            t4.setEnabled(false);
                            f2.add(t4);
                            f2.setLayout(null);
                            f2.setVisible(true);
                            f2.setSize(500, 500);
                            JButton save1 = new JButton("SAVE");
                            f2.add(save1);
                            JButton yes1 = new JButton("yes");
                            f2.add(yes1);
                            yes1.setBounds(320, 200, 60, 30);
                            yes1.addActionListener((ActionListener) new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    t3.setEnabled(true);
                                }
                            });

                            JButton yes2 = new JButton("yes");
                            f2.add(yes2);
                            yes2.setBounds(320, 250, 60, 30);
                            yes2.addActionListener((ActionListener) new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    t4.setEnabled(true);
                                }
                            });

                            save1.setBounds(100, 350, 80, 25);
                            save1.setBackground(Color.GREEN);
                            save1.setForeground(Color.BLACK);
                            save1.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    boolean val = false;
                                    String namecheck = t1.getText();
                                    String numbercheck = t2.getText();
                                    LinkList.person last2 = list.head;
                                    if (!(namecheck.equals(last.name))) {
                                        while (last2 != null) {

                                            if (namecheck.equals(last2.name)) {
                                                val = true;

                                                JOptionPane.showMessageDialog(null, "THE GIVEN NAME ALREADY EXISTS");
                                                break;
                                            } else
                                                last2 = last2.next;
                                        }
                                    }
                                    if (!(numbercheck.equals(last.number))) {
                                        while (last2 != null) {
                                            if (numbercheck.equals(last2.number)) {
                                                val = true;
                                                JOptionPane.showMessageDialog(null, "THE GIVEN NUMBER ALREADY EXISTS");
                                                break;
                                            } else
                                                last2 = last2.next;
                                        }
                                    }
                                    if (namecheck.length() == 0) {
                                        val = true;
                                        JOptionPane.showMessageDialog(null, "NAME CANNOT BE EMPTY");
                                    }
                                    if (numbercheck.length() != 10) {
                                        val = true;
                                        JOptionPane.showMessageDialog(null, "ENTER 10 DIGITS ONLY");
                                    }
                                    for (int a = 0; a < 10; a++) {
                                        if (!(Character.isDigit(numbercheck.charAt(a)))) {
                                            val = true;
                                            JOptionPane.showMessageDialog(null, "ENTER NUMBERS ONLY");
                                            break;
                                        }
                                    }

                                    // person pe = new person();
                                    if (!val) {
                                        last.name = t1.getText();
                                        last.number = t2.getText();
                                        last.email = t3.getText();
                                        last.address = t4.getText();

                                        try {
                                            fw = new FileWriter(f);
                                            last2 = list.head;
                                            while (last2 != null) {
                                                fw.write(last2.name + "-");
                                                fw.write(last2.number + "-");
                                                fw.write(last2.email + "-");
                                                fw.write(last2.address + "-");
                                                last2 = last2.next;
                                            }

                                            fw.close();

                                        } catch (IOException e1) {

                                            e1.printStackTrace();
                                        }
                                        JOptionPane.showMessageDialog(null, "CONTACT MODIFIED SUCCESSFULLY");
                                        f2.dispose();
                                        f4.dispose();
                                    }
                                }
                            });
                        }
                    });
                    val = true;
                } else {
                    last1 = last;

                    last = last.next;

                }
            }
            if (!val) {
                JOptionPane.showMessageDialog(null, "NO SUCH CONTACT EXIST");
            }
        }

        else if (obj == ALLContacts) {

            JFrame j5 = new JFrame("ALL CONTACTS");
            j5.setSize(800, 800);
            String heading[] = { "NAME", "NUMBER", "EMAIL", "ADDRESS" };

            String[][] data = new String[c][4];

            // int k = 0;
            // data[k][0] = "NAME";
            // data[k][1] = "NUMBER";
            // data[k][2] = "EMAIL";
            // data[k][3] = "ADDRESS";

            int k = 0;
            last = list.head;
            while (last != null) {

                data[k][0] = last.name;
                data[k][1] = last.number;
                data[k][2] = last.email;
                data[k][3] = last.address;
                k++;
                last = last.next;
            }
            JTable jt = new JTable(data, heading);

            j5.add(jt);

            j5.setVisible(true);

        }
    }
}