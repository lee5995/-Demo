package com.test.Thread.download;


import java.io.*;
import java.util.Scanner;

public class DownLoad {

    private String srcPath;
    private String destPath;

    public DownLoad(){}

    public DownLoad(String srcPath, String destPath){
        this.srcPath=srcPath;
        this.destPath=destPath;
    }

    public  void download(){

        File src=new File(srcPath);
        File dest= new File(destPath);

        long len =src.length();

        final int blockSize=1024*1024;

        int actualNums=(int) Math.ceil(len*1.0/blockSize);

        //起始位置和实际大小
        int beginPos=0;

        int actualSize= (int)(len>blockSize?blockSize:len);

        for (int i = 0; i <actualNums ; i++) {
            beginPos=i*actualSize;

            new DownLoadThread(src, dest, beginPos, actualSize).start();

            if (len>blockSize){
                actualSize=blockSize;
                len-=blockSize;
            }else {
                actualSize=(int) len;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String in=sc.nextLine();
        String out=sc.nextLine();

        DownLoad d=new DownLoad(in,out);
        d.download();
    }
}
