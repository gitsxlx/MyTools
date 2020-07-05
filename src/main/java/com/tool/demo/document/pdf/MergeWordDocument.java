package com.tool.demo.document.pdf;

//import com.spire.doc.Document;
//import com.spire.doc.FileFormat;
import java.io.File;

/**
 * MergeWordDocument
 *
 * @author WangChao
 * @date 2020/06/20
 */
public class MergeWordDocument {

	/**
	 * 合并
	 * @param docPath
	 * @param desPath
	 * @return
	 */
    /*public static boolean merge(String docPath, String desPath) {
        File[] fs = getSplitFiles(docPath);
        Document document = new Document(docPath + "test0.docx");

        for (int i = 1; i < fs.length; i++) {
            document.insertTextFromFile(docPath + "test" + i + ".docx", FileFormat.Docx_2013);
        }
        //第四步：对合并的doc进行保存2
        document.saveToFile(desPath);
        return true;
    }*/

	/**
	 * 取得某一路径下所有的pdf
	 * @param path
	 * @return
	 */
    private static File[] getSplitFiles(String path) {
        File f = new File(path);
        File[] fs = f.listFiles();
        if (fs == null) {
            return null;
        }
        return fs;
    }

}
