package com.test.Thread.download;


import java.io.*;

public class DownLoadThread extends Thread {
    private File src;
    private File dest;
    int beginPos;
    int size;

    public DownLoadThread(){}

    public DownLoadThread(File src,File dest,int beginPos,int size){
        this.src=src;
        this.dest=dest;
        this.beginPos=beginPos;
        this.size=size;
    }

    @Override
    public void run() {
        try (RandomAccessFile rafr = new RandomAccessFile(src,"r");
             RandomAccessFile rafw = new RandomAccessFile(dest,"rw")) {

            rafr.seek(beginPos);
            rafw.seek(beginPos);
            byte[] bytes = new byte[1024*100];
            int len = -1;

            while ((len = rafr.read(bytes)) != -1) {
                if(size>bytes.length) {
                    rafw.write(bytes, 0,len );
                    size-=bytes.length;
                }else{
                    rafw.write(bytes,0,size);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : 下载完毕");
    }
}

