import java.awt.EventQueue;

import javax.swing.JFrame;

public class GameApp extends JFrame{
    public GameApp() {

        initUI();
    }

    private void initUI() {

        add(new GameArea());
        setResizable(false);
        pack();

        setTitle("waafarga3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
    	GameApp ex = new GameApp();
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                GameApp ex = new GameApp();
//                ex.setVisible(true);
//            }
//        });
    }
}
