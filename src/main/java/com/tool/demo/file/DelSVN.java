package com.tool.demo.file;

import java.io.File;

/**
 * 删除svn文件
 *
 * @author Mr.Wang
 * @date 2018年3月21日
 */
public class DelSVN {

    private static final String SVN_STR = ".svn";

    public static void main(String[] args) {
        DelSVN delSvn = new DelSVN();
        String path = "D:\\fa\\gjb_v90";
        delSvn.delSvnDic(path);
        System.out.println("删除完成！");
    }

    /**
     * 删除svn文件
     * @param root
     */
    public void delSvnDic(String root) {
        File directory = new File(root);
        File[] files = directory.listFiles();
        if (files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                if (SVN_STR.equals(f.getName())) {
                    delDirectory(f);
                } else {
                    delSvnDic(f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * delete a certain directory
     * @param directory
     */
    private void delDirectory(File directory) {
        File[] children = directory.listFiles();
        if (children.length == 0) {
            System.out.println("Delete Directory:" + directory.getAbsolutePath());
            directory.delete();
        } else {
            for (File child : children) {
                if (child.isFile()) {
                    System.out.println("Delete File:" + child.getAbsolutePath());
                    child.delete();
                } else if (child.isDirectory()) {
                    delDirectory(child);
                }
            }
            System.out.println("Delete Directory:" + directory.getAbsolutePath());
            directory.delete();
        }
    }

}