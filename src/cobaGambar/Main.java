package cobaGambar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Akira - Community Java
 */
public class Main extends JFrame{

    public Main() {
        setUndecorated(true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        userInterface();
    }
    
    private void userInterface(){
        JPanel main_pan = new JPanel(new GridLayout(1, 2));
        
        JPanel left_pan = new JPanel(new BorderLayout());
        left_pan.setBackground(new Color(0, 80, 239));
        
        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("images/pengguna.png")));
        logo.setText("Authentication User");
        logo.setHorizontalTextPosition(JLabel.CENTER);
        logo.setVerticalTextPosition(JLabel.BOTTOM);
        logo.setForeground(Color.white);
        logo.setFont(new Font("Segoe UI", 0, 15));
        left_pan.add(logo);
        
        main_pan.add(left_pan);
        
        JPanel right_pan = new JPanel(new BorderLayout());
        right_pan.setBackground(Color.white);
        
        JPanel pan_exit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel exit = new JLabel("X");

   //        Aksi Untuk Menutup atau Keluar Dari Form Login
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        exit.setPreferredSize(new Dimension(30, 30));
        exit.setHorizontalAlignment(JLabel.CENTER);
        exit.setVerticalAlignment(JLabel.CENTER);
        exit.setFont(new Font("Segoe UI", Font.BOLD, 25));
        exit.setBackground(new Color(0, 80, 239));
        exit.setForeground(Color.white);
        exit.setOpaque(true);
        pan_exit.add(exit);
        right_pan.add(pan_exit, "North");
        
        JPanel right_comp = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Sign Up");
        title.setPreferredSize(new Dimension(this.getWidth(), 70));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Segoe UI", 0, 30));
        right_comp.add(title, "North");
        
        JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel _user = new JLabel("Username");
        _user.setFont(new Font("Segoe UI", 0, 14));
        _user.setPreferredSize(new Dimension(200, 20));
        pan.add(_user);
        JTextField user = new JTextField();
        user.setPreferredSize(new Dimension(200, 30));
        pan.add(user);
        
        JLabel _pass = new JLabel("Password");
        _pass.setFont(new Font("Segoe UI", 0, 14));
        _pass.setPreferredSize(new Dimension(200, 20));
        pan.add(_pass);
        JPasswordField pass = new JPasswordField();
        pass.setPreferredSize(new Dimension(200, 30));
        pan.add(pass);
        
        right_comp.add(pan);
        
        right_pan.add(right_comp);
        
        JPanel pan_btn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pan_btn.setPreferredSize(new Dimension(this.getWidth(), 70));

        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(120, 30));
        login.setFont(new Font("Segoe UI", 0, 17));
        login.setContentAreaFilled(false);
        login.setForeground(new Color(0, 80, 239));
        login.setBorder(BorderFactory.createLineBorder(new Color(0, 80, 239), 1, true));
        pan_btn.add(login);
        right_pan.add(pan_btn, "South");
        
        main_pan.add(right_pan);
        
        getContentPane().add(main_pan);
    }
    
    public static void Main(String[] args) {
        Main obj = new Main();
        obj.setVisible(true);
    }
}