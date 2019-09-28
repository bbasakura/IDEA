package cn.itcast.b2c.gciantispider.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;
/**
 * 消息摘要加密算法的工具类，包含了MD5,SHA1加密。
 * 
 */
public class PasswordEncoderUtil implements PasswordEncoder {
    
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    /**
     * 加密类型，即MD5或者SHA1
     */
    private final String ssoEncodingAlgorithm;
    
    private String characterEncoding;
    
    public PasswordEncoderUtil(final String ssoEncodingAlgorithm) {
        this.ssoEncodingAlgorithm = ssoEncodingAlgorithm;
    }

    @Override
    public String encode(String password) {
        if (password == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest
                .getInstance(this.ssoEncodingAlgorithm);

            if (StringUtils.hasText(this.characterEncoding)) {
                messageDigest.update(password.getBytes(this.characterEncoding));
            } else {
                messageDigest.update(password.getBytes());
            }


            final byte[] digest = messageDigest.digest();

            return getFormattedText(digest);
        } catch (final NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    private String getFormattedText(byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (int j = 0; j < bytes.length; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    
    public final void setCharacterEncoding(final String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }
    
    public static void main(String[] args) {
        String text = "000000";
        PasswordEncoderUtil pEncoderUtil = new PasswordEncoderUtil("SHA1");
        System.out.println(pEncoderUtil.encode(text));
    }
      
}
