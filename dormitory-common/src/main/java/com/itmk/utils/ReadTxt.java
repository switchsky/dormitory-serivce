package com.itmk.utils;

import java.io.*;

public class ReadTxt {

    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    public static String readFile(String Url) {
        File file = new File(Url);
        return txt2String(file);
    }
    public static String writeFile(String filePath, String content) throws IOException {
        try {
            //判断有无文件
            File writename = new File(filePath);
            if(!writename.exists()){
                writename.createNewFile();
            }
            //true表示追加内容
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
            bufferedWriter.write(content);
            //换行
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println("写入文件成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}