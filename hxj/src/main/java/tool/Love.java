package other.打印爱心;

import cn.hutool.core.bean.BeanUtil;

public class Love {


    /**
     * 心：((0.05*x)^2 + (0.1*y)^2-1)^3-(0.05*x)^2 * (0.1*y)^3 < = 0
     *
     * 箭：4*y-x == 0
     * @param args
     */
    public static void main(String[] args) {
        //简单❤
        simpleLove();
        //单心
        heart(15,0.9,"^");
        //双心
        heartTwo(15,0.9,"爱","梁山伯","祝英台");
        //点缀
        heartTwoWithXK(15,0.9,"爱","梁山伯","祝英台");
    }

    /**
     * 爱心的公式 （x²+y²-1）³-x²*y³=0
     */
    public static void simpleLove(){
        for(float y = (float) 1.5;y>-1.5;y -=0.1) {
            for (float x = (float) -1.5; x < 1.5; x += 0.05) {
                float a = x * x + y * y - 1;
                if ((a * a * a - x * x * y * y * y) <= 0.0) {
                    System.out.print("^");
                } else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * 单❤
     * @param r
     * @param size
     * @param req
     */
    private static void heart(int r,double size,String req){
        size=1/(1.5*r*size);
        StringBuilder sb=new StringBuilder();
        for (int y = r; y > -r; y--,sb.append("\n"))
            for (int x = -2*r; x < 2*r; x++ ) {
                char msg=(req + req).charAt((x - y) % req.length() + req.length());
                sb.append((inHeart(size,x,y)?msg+ " " : " "));
            }
        System.err.println(sb.toString());
    }

    /**
     * 双❤
     * @param r
     * @param size
     * @param center
     * @param left
     * @param right
     */
    private static void heartTwo(int r,double size,String center,String left,String right){
        size=1/(1.5*r*size);
        StringBuilder sb=new StringBuilder();
        for (int y = r; y > -r; y--,sb.append("\n"))
            for (int x = -2*r; x <4*r; x++ ) {
                boolean isLeft=inHeart(size,x,y);
                boolean isRight=inHeart(size,x-25,y-3);

                //双空格
                String req=null;
                if(isLeft && isRight) req=center;
                else if(isLeft) req=left;
                else if (isRight) req=right;
                if(req!=null) sb.append((req + req).charAt((x - y) % req.length() + req.length()));
                else sb.append(" ");//双空格
            }
        System.err.println(sb.toString());
    }

    /**
     * 双❤点缀
     * @param r
     * @param size
     * @param center
     * @param left
     * @param right
     */
    private static void heartTwoWithXK(int r,double size,String center,String left,String right){
        size=1/(1.5*r*size);
        StringBuilder sb=new StringBuilder();
        for (int y = r; y >=-r; y--,sb.append("\n"))
            for (int x = -2*r; x <= 4*r; x++ ) {
                boolean isLeft=inHeart(size,x,y+3);
                boolean isRight=inHeart(size,x-25,y);

                //双空格
                String req=null;
                String w="";
                if(isLeft && isRight) req=center;
                else if(isLeft) req=left;
                else if (isRight) req=right;
                else if((y==-r || y==r)) {
                    if (x < 3 * r - 7) {
                        req = "♥";
                        w = " ";
                    }
                }
                else if(x==4*r || x==-2*r || line(x,y+3)) req="♥";
                if(req!=null) sb.append((req + req).charAt((x - y) % req.length() + req.length()) + w);
                else sb.append(" ");//双空格
            }
        System.err.println(sb.toString());
    }

    private static boolean inHeart(double size,int x,int y){
        return Math.pow(Math.pow(x * size, 2) + Math.pow(y * 2*size, 2) - 1, 3) - Math.pow(x * size, 2) * Math.pow(y * 2*size, 3) <= 0;
    }

    private static boolean line(int x,int y){
        return 4*y-x == 0;
    }
}
