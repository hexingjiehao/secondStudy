package javaKnowledge.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedReader reader=new PipedReader();
        PipedWriter writer=new PipedWriter();

        //管道链接
        writer.connect(reader);

        Thread thread=new Thread(new Print(reader),"PrintTread");
        thread.start();

        int receive=0;
        try {
            while( (receive=System.in.read()) !=-1 ){
                writer.write(receive);
            }
        } finally {
            writer.close();
        }

    }


    //管道流读取
    static class Print implements Runnable{

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive=0;
            try {
                while( (receive=in.read()) != -1 ){
                    System.out.print( (char)receive ) ;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
