import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class Main  {

    public static void main(String[] args){
        GaTime gatime = new GaTime();
    }
}

class GaTime extends Frame implements ActionListener, KeyListener {
    Button start, stop;
    public TextField time;
    boolean timing = false;
    public GaTime(){
        start = new Button("Start");
        start.setBounds(1, 45, 80, 30);
        start.addActionListener(this);
        stop = new Button("Stop");
        stop.setBounds(90, 45, 80, 30);
        stop.addActionListener(this);
        add(start);
        add(stop);
        time = new TextField();
        time.setText("0.00");
        time.setBounds(10, 100, 150, 50);
        time.setEditable(false);
        add(time);

        setSize(200, 300);
        setLayout(null);
        setVisible(true);
        setAlwaysOnTop(true);

        Thread thread = new Thread(() -> {
            Timer timer = new Timer();
            while (true){
                System.out.println("Updating");
                if (timing){
                    if (!timer.started){
                        timer.start();
                    }
                    timer.update();
                    DecimalFormat df_obj = new DecimalFormat("#.##");
                    time.setText(String.valueOf(df_obj.format(timer.getTime())));
                }
                else{
                    timer.started = false;
                }
                try {
                    Thread.sleep(0, 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == start){
            System.out.println("Starting timer...");
            timing = true;
        }
        else if (e.getSource() == stop){
            timing = false;
        }
        System.out.println(timing);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD9){
            timing = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD3){
            timing = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
