package fp.dam.psp.REPASO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main3 {
    public static void main(String[] args) {
        ExecutorService s = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            s.submit(new Runnable(){
                @Override
                public void run(){

                    try {
                        Thread.sleep((long) (Math.random() * 200 + 100));
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                }
            });
            s.shutdown();
            
        }
    }
}
