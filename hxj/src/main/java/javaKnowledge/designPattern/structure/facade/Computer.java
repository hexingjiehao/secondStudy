package javaKnowledge.designPattern.structure.facade;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Computer {

    private Cpu cpu;
    private Disk disk;

    public Computer() {
        this.cpu = new Cpu();
        this.disk = new Disk();
    }

    public void start(){
        System.out.println("外观模式：Computer启动开始");
        cpu.start();
        disk.start();
        System.out.println("外观模式：Computer启动结束");
    }

    public static void main(String[] args){
        Computer computer=new Computer();
        computer.start();
    }

}
