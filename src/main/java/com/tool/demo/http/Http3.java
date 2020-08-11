package com.tool.demo.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/*
需要jar包
commons-httpclient-3.1.jar
commons-codec-1.11.jar
*/
public class Http3 {

    public static void main(String[] args) {
        String httpurl = "http://124.205.149.82:8888/webservice/staff_manage.php?optr=add&no=8001&name=erp1";
        String result = doGet(httpurl);
        System.out.println(result);
    }


    //apache HttpClient3.1
    public static String doGet(String url) {
        // 输入流
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        // 创建httpClient实例
        HttpClient httpClient = new HttpClient();
        // 设置http连接主机服务超时时间：15000毫秒
        // 先获取连接管理器对象，再获取参数对象,再进行参数的赋值
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建一个Get方法实例对象
        GetMethod getMethod = new GetMethod(url);
        // 设置get请求超时为60000毫秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        // 设置请求重试机制，默认重试次数：3次，参数设置为true，重试机制可用，false相反
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, true));
        try {
            // 执行Get方法
            int statusCode = httpClient.executeMethod(getMethod);
            // 判断返回码
            if (statusCode != HttpStatus.SC_OK) {
                // 如果状态码返回的不是ok,说明失败了,打印错误信息
                System.err.println("Method faild: " + getMethod.getStatusLine());
            } else {
                // 通过getMethod实例，获取远程的一个输入流
                is = getMethod.getResponseBodyAsStream();
                // 包装输入流
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                // 读取封装的输入流
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp).append("\r\n");
                }

                result = sbf.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放连接
            getMethod.releaseConnection();
        }
        return result;
    }

    //apache HttpClient3.1
    public static String doPost(String url, Map<String, Object> paramMap) {
        // 获取输入流
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

        NameValuePair[] nvp = null;
        // 判断参数map集合paramMap是否为空
        if (null != paramMap && paramMap.size() > 0) {// 不为空
            // 创建键值参数对象数组，大小为参数的个数
            nvp = new NameValuePair[paramMap.size()];
            // 循环遍历参数集合map
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();

            int index = 0;
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                // 从mapEntry中获取key和value创建键值对象存放到数组中
                try {
                    nvp[index] = new NameValuePair(mapEntry.getKey(),
                            new String(mapEntry.getValue().toString().getBytes("UTF-8"), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
        // 判断nvp数组是否为空
        if (null != nvp && nvp.length > 0) {
            // 将参数存放到requestBody对象中
            postMethod.setRequestBody(nvp);
        }
        // 执行POST方法
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            // 判断是否成功
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method faild: " + postMethod.getStatusLine());
            }
            // 获取远程返回的数据
            is = postMethod.getResponseBodyAsStream();
            // 封装输入流
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp).append("\r\n");
            }

            result = sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放连接
            postMethod.releaseConnection();
        }
        return result;
    }



    private final static String URL_STR = "";
    private final static String userName = "";
    private final static String passwd = "";

    /**
     * 客户端上传文件
     * PostMethod
     *
     * @param localFile
     */
    public void upload(String localFile){
        File file = new File(localFile);
        PostMethod filePost = new PostMethod(URL_STR);
        HttpClient client = new HttpClient();

        try {
            // 通过以下方法可以模拟页面参数提交
            filePost.setParameter("userName", userName);
            filePost.setParameter("passwd", passwd);

            Part[] parts = { new FilePart(file.getName(), file) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));

            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                System.out.println("上传成功");
            } else {
                System.out.println("上传失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
    }

    /**
     * 客户端上传
     * HttpPost方式
     * HttpClient是org.apache.http.client.methods下的
     *
     * @param localFile
     */
    /*public void upload1(String localFile){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();

            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(URL_STR);

            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(localFile));

            StringBody userName = new StringBody("Scott", ContentType.create(
                    "text/plain", Consts.UTF_8));
            StringBody password = new StringBody("123456", ContentType.create(
                    "text/plain", Consts.UTF_8));

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    // 相当于<input type="file" name="file"/>
                    .addPart("file", bin)

                    // 相当于<input type="text" name="userName" value=userName>
                    .addPart("userName", userName)
                    .addPart("pass", password)
                    .build();

            httpPost.setEntity(reqEntity);

            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);

            System.out.println("The response value of token:" + response.getFirstHeader("token"));

            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应长度
                System.out.println("Response content length: " + resEntity.getContentLength());
                // 打印响应内容
                System.out.println(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
            }

            // 销毁
            EntityUtils.consume(resEntity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    private final static String uploadPath = "";

    /**
     * 服务端接收文件
     *
     * @param request
     * @param response
     */
    public void processUpload(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        System.out.println("Come on, baby .......");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //检测是不是存在上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //指定在内存中缓存数据大小,单位为byte,这里设为1Mb
            factory.setSizeThreshold(1024 * 1024);

            //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
            factory.setRepository(new File("D:\\temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
            upload.setFileSizeMax(50 * 1024 * 1024);

            //指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
            upload.setSizeMax(50 * 1024 * 1024);
            upload.setHeaderEncoding("UTF-8");

            List<FileItem> items = null;

            try {
                // 解析request请求
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            if (items != null) {
                //解析表单项目
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    //如果是普通表单属性
                    if (item.isFormField()) {
                        //相当于input的name属性   <input type="text" name="content">
                        String name = item.getFieldName();

                        //input的value属性
                        String value = item.getString();

                        System.out.println("属性:" + name + " 属性值:" + value);
                    }
                    //如果是上传文件
                    else {
                        //属性名
                        String fieldName = item.getFieldName();

                        //上传文件路径
                        String fileName = item.getName();
                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名

                        try {
                            item.write(new File(uploadPath, fileName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        response.addHeader("token", "hello");
    }

    /**
     * 客户端文件下载
     * GetMethod方式
     *
     * @param remoteFileName
     * @param localFileName
     */
    public void downLoad(String remoteFileName, String localFileName) {
        HttpClient client = new HttpClient();
        GetMethod get = null;
        FileOutputStream output = null;

        try {
            get = new GetMethod(URL_STR);
            get.setRequestHeader("userName", userName);
            get.setRequestHeader("passwd", passwd);
            get.setRequestHeader("fileName", remoteFileName);

            int i = client.executeMethod(get);

            if (1 == i) {
                System.out.println("The response value of token:" + get.getResponseHeader("token"));

                File storeFile = new File(localFileName);
                output = new FileOutputStream(storeFile);

                // 得到网络资源的字节数组,并写入文件
                output.write(get.getResponseBody());
            } else {
                System.out.println("DownLoad file occurs exception, the error code is :" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            get.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }

    /**
     * 客户端下载
     * HttpGet方式
     * 此处的HttpClient是org.apache.http.client.methods
     *
     * @param remoteFileName
     * @param localFileName
     */
    public void downLoad1(String remoteFileName, String localFileName) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        OutputStream out = null;
        InputStream in = null;

        try {
            HttpGet httpGet = new HttpGet(URL_STR);

            httpGet.addHeader("userName", userName);
            httpGet.addHeader("passwd", passwd);
            httpGet.addHeader("fileName", remoteFileName);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();

            long length = entity.getContentLength();
            if (length <= 0) {
                System.out.println("下载文件不存在！");
                return;
            }

            System.out
                    .println("The response value of token:" + httpResponse.getFirstHeader("token"));

            File file = new File(localFileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength = in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 客户端下载
     * HttpURLConnection方式
     *
     * @param remoteFileName
     * @param localFileName
     */
    public void download3(String remoteFileName, String localFileName) {
        FileOutputStream out = null;
        InputStream in = null;

        try {
            URL url = new URL(URL_STR);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            // true -- will setting parameters
            httpURLConnection.setDoOutput(true);
            // true--will allow read in from
            httpURLConnection.setDoInput(true);
            // will not use caches
            httpURLConnection.setUseCaches(false);
            // setting serialized
            httpURLConnection
                    .setRequestProperty("Content-type", "application/x-java-serialized-object");
            // default is GET
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charsert", "UTF-8");
            // 1 min
            httpURLConnection.setConnectTimeout(60000);
            // 1 min
            httpURLConnection.setReadTimeout(60000);

            httpURLConnection.addRequestProperty("userName", userName);
            httpURLConnection.addRequestProperty("passwd", passwd);
            httpURLConnection.addRequestProperty("fileName", remoteFileName);

            // connect to server (tcp)
            httpURLConnection.connect();

            in = httpURLConnection.getInputStream();// send request to
            // server
            File file = new File(localFileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength = in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String downloadPath = "";

    /**
     * 服务端下载
     *
     * @param request
     * @param response
     */
    public void processDownload(HttpServletRequest request, HttpServletResponse response) {
        int BUFFER_SIZE = 4096;
        InputStream in = null;
        OutputStream out = null;

        System.out.println("Come on, baby .......");

        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");

            String userName = request.getHeader("userName");
            String passwd = request.getHeader("passwd");
            String fileName = request.getHeader("fileName");

            System.out.println("userName:" + userName);
            System.out.println("passwd:" + passwd);
            System.out.println("fileName:" + fileName);

            //可以根据传递来的userName和passwd做进一步处理，比如验证请求是否合法等
            File file = new File(downloadPath + "\\" + fileName);
            response.setContentLength((int) file.length());
            response.setHeader("Accept-Ranges", "bytes");

            int readLength = 0;

            in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
            out = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[BUFFER_SIZE];
            while ((readLength = in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }

            out.flush();

            response.addHeader("token", "hello 1");

        } catch (Exception e) {
            e.printStackTrace();
            response.addHeader("token", "hello 2");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

}