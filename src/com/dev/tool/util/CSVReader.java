package com.dev.tool.util;//package com.eric.tool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @author Alpha JAVA 操作 excel 文档中的 .csv文件格式
*/
public class CSVReader {

    private static Log logger = LogFactory.getLog(CSVReader.class);

    public String filename = null;

    private BufferedReader bufferedreader = null;

    private List<String> list = new ArrayList<String>();

    public CSVReader() {

    }

    public CSVReader(String filename) {
        logger.info("CsvReader filename = " + filename);
        try {
            this.filename = filename;
//			bufferedreader = new BufferedReader(new FileReader(filename));
            bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
                    filename)), Charset.forName("utf-8")));
            String stemp;
            while ((stemp = bufferedreader.readLine()) != null) {
                list.add(stemp);
            }
        } catch (Exception e) {
            logger.error("CsvReader exception:", e);
        }
    }

    public List<String> getList() throws IOException {
        return list;
    }

    public int getRowNum() {
        return list.size();
    }

    public int getColNum() {
        if (!list.toString().equals("[]")) {
            if (list.get(0).toString().contains(",")) {
                int iCount = list.get(0).toString().split(",").length;
                return iCount;
            } else if (list.get(0).toString().trim().length() != 0) {
                return list.get(0).toString().split(",").length;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public String getRow(int index) {
        if (this.list.size() != 0)
            return (String) list.get(index);
        else
            return null;
    }

    public String getCol(int index) {
        if (this.getColNum() == 0) {
            return null;
        }
        StringBuffer scol = new StringBuffer();
        String temp = null;
        int colnum = this.getColNum();
        if (colnum > 1) {
            for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
                temp = it.next().toString();
                scol = scol.append(temp.split(",")[index] + ",");
            }
        } else {
            for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
                temp = it.next().toString();
                scol = scol.append(temp + ",");
            }
        }
        String str = new String(scol.toString());
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public String getString(int row, int col) {
        String temp = null;
        int colnum = this.getColNum(); // 列
        // System.out.println(row+"行，有"+colnum+"列:=======");
        if (colnum > 1) {
            temp = list.get(row).toString().split(",")[col];
            // System.out.println("某:"+row+"行，某："+col+"列"+temp);
        } else if (colnum == 1) {
            temp = list.get(row).toString().split(",")[col];
        } else {
            temp = null;
        }
        return temp;
    }

    public void CsvClose() throws IOException {
        this.bufferedreader.close();
        this.list.clear();
    }

}

