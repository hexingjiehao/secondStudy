package other.JGroup;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JGroupDemo extends ReceiverAdapter {
    JChannel channel;

    @Override
    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }

    @Override
    public void viewAccepted(View view) {
        System.out.println("** view: " + view);
    }

    private void start() throws Exception {
        channel=new JChannel().setReceiver(this);;
        channel.connect("ChatCluster");
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit"))
                    break;
                line="[内容是：] " + line;
                Message msg=new Message(null, line);
                channel.send(msg);
            }
            catch(Exception e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new JGroupDemo().start();
    }
}
