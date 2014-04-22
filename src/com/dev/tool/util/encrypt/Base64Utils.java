package com.dev.tool.util.encrypt;


import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import java.io.*;

/**
 * Created by gk on 4/22/14.
 */
public class Base64Utils {

    public static void main(String[] args) throws Exception {

        testSunBase64();
        testMimeUtitlityBase64();
        testApacheBase64();
        testMiGBase64();


    }

    /**
     * Using sun.misc.BASE64Encoder / sun.misc.BASE64Decoder Example
     *
     * The best way to encode / decode in Base64 without using any third party libraries, you can use Using
     * sun.misc.BASE64Encoder / sun.misc.BASE64Decoder. sun.* packages are not "officially supported" by
     * Sun as it is proprietary classes, so there is chance to remove from jdk in future versions.
     *
     */
    private static void testSunBase64(){

        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            String encodedBytes = encoder.encodeBuffer("JavaTips.net".getBytes());
            System.out.println("encodedBytes " + encodedBytes);
            byte[] decodedBytes = decoder.decodeBuffer(encodedBytes);
            System.out.println("decodedBytes " + new String(decodedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Using javax.mail.internet.MimeUtility Example
     *
     * javax.mail.internet.MimeUtility is a part of JavaMail package and you can download
     * the jar file from the following link JavaMail and should be in Class path
     * download path:
     * https://java.net/projects/javamail/pages/Home#Download_JavaMail_1.5.1_Release
     *
     * use jar file:
     * javax.mail.jar
     *
     * @throws MessagingException
     * @throws IOException
     */
    private static void testMimeUtitlityBase64() throws MessagingException,IOException{

        String test = "JavaTips.net";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream base64OutputStream = MimeUtility.encode(baos, "base64");
        base64OutputStream.write(test.getBytes());
        base64OutputStream.close();
        System.out.println("encoded String " + baos.toString());

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        InputStream base64InputStream = MimeUtility.decode(bais, "base64");
        byte[] tmp = new byte[baos.toByteArray().length];
        int n = base64InputStream.read(tmp);
        byte[] res = new byte[n];
        System.arraycopy(tmp, 0, res, 0, n);
        System.out.println("decoded String " + new String(res));

    }

    /**
     * Using Apache Commons Codec Example
     * You can also use Apache Commons Codec for encode / decode in Base64.
     * You can download the jar from the following link Apache Commons Codec and should be in Class path
     *
     * download path:
     * http://commons.apache.org/proper/commons-codec/
     *
     * org-apache-commons-codec.jar
     *
     */
   private static void testApacheBase64(){

       byte[] encodedBytes = Base64.encodeBase64("JavaTips.net".getBytes());
       System.out.println("encodedBytes " + new String(encodedBytes));
       byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
       System.out.println("decodedBytes " + new String(decodedBytes));

   }

    /**
     *    Using MiGBase64 Example
     *
     *    download path:
     *    http://sourceforge.net/projects/migbase64/
     *
     */
   private static void testMiGBase64(){

       String encodeToString = com.dev.tool.util.encrypt.Base64.encodeToString("JavaTips.net".getBytes(), true);
       System.out.println("encodeToString " + encodeToString);
       byte[] decodedBytes = com.dev.tool.util.encrypt.Base64.decode(encodeToString.getBytes());
       System.out.println("decodedToString " + new String(decodedBytes));

   }
}
