package cn.itcast.b2c.gciantispider.util;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA算法加密/解密工具类。
 * 
 * @version 1.0.0, 2010-05-05
 */
public abstract class RSAUtils
{

    private static final Logger LOGGER = Logger
            .getLogger(RSAUtils.class);

    /** 算法名称 */
    private static final String ALGORITHOM = "RSA";

    /** 密钥大小 */
    private static final int KEY_SIZE = 1024;

    /** 默认的安全服务提供者 */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    private static KeyPairGenerator keyPairGen = null;

    private static KeyFactory keyFactory = null;

    /** 缓存的密钥对。 */
    private static KeyPair oneKeyPair = null;

    static
    {
        try
        {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM,
                    DEFAULT_PROVIDER);
            keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        }
        catch (NoSuchAlgorithmException ex)
        {
            LOGGER.error(ex.getMessage());
        }
    }

    protected RSAUtils()
    {
    }

    public static KeyPair generateKeyPair(String key)
    {
        try
        {
            Date date = new Date();
            keyPairGen.initialize(KEY_SIZE, new SecureRandom(key.getBytes()));
            oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        }
        catch (InvalidParameterException ex)
        {
            LOGGER.error("KeyPairGenerator does not support a key length of "
                    + KEY_SIZE + ".", ex);
        }
        catch (NullPointerException ex)
        {
            LOGGER.error(
                    "RSAUtils#KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance.",
                    ex);
        }
        return null;
    }

    /**
     * 生成并返回RSA密钥对。
     */
    private static synchronized KeyPair generateKeyPair()
    {
        String key ="http://www.kingpointcn.com";
        return generateKeyPair(key);
    }

    /**
     * 返回RSA密钥对。
     */
    public static KeyPair getKeyPair()
    {
        if (oneKeyPair != null)
        {
            return oneKeyPair;
        }
        return generateKeyPair();
    }

    /**
     * 根据给定的系数和专用指数构造一个RSA专用的公钥对象。
     * 
     * @param modulus 系数。
     * @param publicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus,
            byte[] publicExponent)
    {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(
                modulus), new BigInteger(publicExponent));
        try
        {
            return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            LOGGER.error("RSAPublicKeySpec is unavailable.", ex);
        }
        catch (NullPointerException ex)
        {
            LOGGER.error(
                    "RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.",
                    ex);
        }
        return null;
    }

    /**
     * 根据给定的系数和专用指数构造一个RSA专用的私钥对象。
     * 
     * @param modulus 系数。
     * @param privateExponent 专用指数。
     * @return RSA专用私钥对象。
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,
            byte[] privateExponent)
    {
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(
                new BigInteger(modulus), new BigInteger(privateExponent));
        try
        {
            return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            LOGGER.error("RSAPrivateKeySpec is unavailable.", ex);
        }
        catch (NullPointerException ex)
        {
            LOGGER.error(
                    "RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.",
                    ex);
        }
        return null;
    }

    /**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串。
     * 
     * @param encrypttext 密文。
     * @return {@code encrypttext} 的原文字符串。
     */
    public static String decryptStringByJs(String encrypttext)
    {
        String text = decryptString(encrypttext);
        if (text == null)
        {
            return null;
        }
        return StringUtils.reverse(text);
    }

    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的私钥对象。
     * 
     * @param modulus 系数。
     * @param privateExponent 专用指数。
     * @return RSA专用私钥对象。
     */
    public static RSAPrivateKey getRSAPrivateKey(String hexModulus,
            String hexPrivateExponent)
    {
        if (StringUtils.isBlank(hexModulus)
                || StringUtils.isBlank(hexPrivateExponent))
        {
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("hexModulus and hexPrivateExponent cannot be empty. RSAPrivateKey value is null to return.");
            }
            return null;
        }
        byte[] modulus = null;
        byte[] privateExponent = null;
        try
        {
            modulus = Hex.decodeHex(hexModulus.toCharArray());
            privateExponent = Hex.decodeHex(hexPrivateExponent.toCharArray());
        }
        catch (DecoderException ex)
        {
            LOGGER.error("hexModulus or hexPrivateExponent value is invalid. return null(RSAPrivateKey).");
        }
        if (modulus != null && privateExponent != null)
        {
            return generateRSAPrivateKey(modulus, privateExponent);
        }
        return null;
    }

    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的公钥对象。
     * 
     * @param modulus 系数。
     * @param publicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    public static RSAPublicKey getRSAPublidKey(String hexModulus,
            String hexPublicExponent)
    {
        if (StringUtils.isBlank(hexModulus)
                || StringUtils.isBlank(hexPublicExponent))
        {
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("hexModulus and hexPublicExponent cannot be empty. return null(RSAPublicKey).");
            }
            return null;
        }
        byte[] modulus = null;
        byte[] publicExponent = null;
        try
        {
            modulus = Hex.decodeHex(hexModulus.toCharArray());
            publicExponent = Hex.decodeHex(hexPublicExponent.toCharArray());
        }
        catch (DecoderException ex)
        {
            LOGGER.error("hexModulus or hexPublicExponent value is invalid. return null(RSAPublicKey).");
        }
        if (modulus != null && publicExponent != null)
        {
            return generateRSAPublicKey(modulus, publicExponent);
        }
        return null;
    }

    /**
     * 使用指定的公钥加密数据。
     * 
     * @param publicKey 给定的公钥。
     * @param data 要加密的数据。
     * @return 加密后的数据。
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data)
            throws Exception
    {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }

    /**
     * 使用指定的私钥解密数据。
     * 
     * @param privateKey 给定的私钥。
     * @param data 要解密的数据。
     * @return 原数据。
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data)
            throws Exception
    {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }

    /**
     * 使用给定的公钥加密给定的字符串。
     * <p />
     * 若 {@code publicKey} 为 {@code null}，或者 {@code plaintext} 为 {@code null}
     * 则返回 {@code null}。
     * 
     * @param publicKey 给定的公钥。
     * @param plaintext 字符串。
     * @return 给定字符串的密文。
     */
    public static String encryptString(PublicKey publicKey, String plaintext)
    {
        if (publicKey == null || plaintext == null)
        {
            return null;
        }
        byte[] data = plaintext.getBytes();
        try
        {
            byte[] en_data = encrypt(publicKey, data);
            return new String(Hex.encodeHex(en_data));
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getCause().getMessage());
        }
        return null;
    }

    /**
     * 使用默认的公钥加密给定的字符串。
     * <p />
     * 若{@code plaintext} 为 {@code null} 则返回 {@code null}。
     * 
     * @param plaintext 字符串。
     * @return 给定字符串的密文。
     */
    public static String encryptString(String plaintext)
    {
        if (plaintext == null)
        {
            return null;
        }
        byte[] data = plaintext.getBytes();
        KeyPair keyPair = getKeyPair();
        try
        {
            byte[] en_data = encrypt((RSAPublicKey) keyPair.getPublic(), data);
            return new String(Hex.encodeHex(en_data));
        }
        catch (NullPointerException ex)
        {
            LOGGER.error("keyPair cannot be null.");
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getCause().getMessage());
        }
        return null;
    }

    /**
     * 使用给定的私钥解密给定的字符串。
     * <p />
     * 若私钥为 {@code null}，或者 {@code encrypttext} 为 {@code null}或空字符串则返回
     * {@code null}。 私钥不匹配时，返回 {@code null}。
     * 
     * @param privateKey 给定的私钥。
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    public static String decryptString(PrivateKey privateKey, String encrypttext)
    {
        if (privateKey == null || StringUtils.isBlank(encrypttext))
        {
            return null;
        }
        try
        {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt(privateKey, en_data);
            return new String(data);
        }
        catch (Exception ex)
        {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s",
                    encrypttext, ex.getCause().getMessage()));
        }
        return null;
    }

    /**
     * 使用默认的私钥解密给定的字符串。
     * <p />
     * 若{@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。 私钥不匹配时，返回
     * {@code null}。
     * 
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    public static String decryptString(String encrypttext)
    {
        if (StringUtils.isBlank(encrypttext))
        {
            return null;
        }
        KeyPair keyPair = getKeyPair();
        try
        {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt((RSAPrivateKey) keyPair.getPrivate(), en_data);
            return new String(data);
        }
        catch (NullPointerException ex)
        {
            LOGGER.error("keyPair cannot be null.");
        }
        catch (Exception ex)
        {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s",
                    encrypttext, ex.getMessage()));
        }
        return null;
    }

    // /**
    // * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串。
    // *
    // * @param encrypttext 密文。
    // * @return {@code encrypttext} 的原文字符串。
    // */
    // public static String decryptStringByJs(String encrypttext)
    // {
    // String text = decryptString(encrypttext);
    // if (text == null)
    // {
    // return null;
    // }
    // return StringUtils.reverse(text);
    // }

    /** 返回已初始化的默认的公钥。 */
    public static RSAPublicKey getDefaultPublicKey()
    {
        KeyPair keyPair = getKeyPair();
        if (keyPair != null)
        {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }

    public static String getModulus()
    {
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        return getModulus(publicKey);
    }

    public static String getModulus(KeyPair key)
    {
        return getModulus((RSAPublicKey) key.getPublic());
    }

    public static String getModulus(RSAPublicKey publicKey)
    {
        return new String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
    }

    public static String getExponent()
    {
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        return getExponent(publicKey);
    }

    public static String getExponent(KeyPair key)
    {
        return getExponent((RSAPublicKey) key.getPublic());
    }

    public static String getExponent(RSAPublicKey publicKey)
    {
        return new String(Hex.encodeHex(publicKey.getPublicExponent()
                .toByteArray()));
    }

    /** 返回已初始化的默认的私钥。 */
    public static RSAPrivateKey getDefaultPrivateKey()
    {
        KeyPair keyPair = getKeyPair();
        if (keyPair != null)
        {
            return (RSAPrivateKey) keyPair.getPrivate();
        }
        return null;
    }
    
    /******************* 用户RSA加密处理，随机密匙 ****************/
    /**
     * 获取RSA随机钥匙,并且将密钥存放于session中
     * 
     * @param request
     * @return [0]:KEY,[1]:MODULES,[2]:EXPONENT
     */
    public static String[] generateRSAKeys(HttpServletRequest request)
    {
        Map<String, KeyPair> temps = (Map<String, KeyPair>) request.getSession().getAttribute(Constants.RSA_KEY);
        if (temps == null)
        {
            temps = new HashMap<String, KeyPair>();
            request.getSession().setAttribute(Constants.RSA_KEY, temps);
        }
        String key = UUID.randomUUID().toString();
        KeyPair keyPair = RSAUtils.generateKeyPair(key);
        temps.put(key, keyPair);
        request.getSession().setAttribute(Constants.KEY, key);
        return new String[] { key, RSAUtils.getModulus(keyPair), RSAUtils.getExponent(keyPair)};
    }
    /**
     * 解密
     * @param request
     * @param pwdKey
     * @param pwd
     * @return
     * @throws Exception
     */
    public static String decryptPwd(HttpServletRequest request, String pwdKey, String pwd) throws Exception
    {
        if (StringUtils.isEmpty(pwdKey))
        {
            throw new Exception("非法登录");
        }
        if (StringUtils.isEmpty(pwd))
        {
            return pwd;
        }
        HttpSession session =  request.getSession();
        Map<String, KeyPair> temps = (Map<String, KeyPair>) session.getAttribute(Constants.RSA_KEY);
        KeyPair keyPair = null;
        if (temps != null)
        {
            keyPair = temps.get(pwdKey);
        }
        if (keyPair == null)
        {
            throw new Exception("登录超时");
        }
        temps.remove(pwdKey);
        String newText = null;
        try
        {
            byte[] en_data = Hex.decodeHex(pwd.toCharArray());
            byte[] data = RSAUtils.decrypt((RSAPrivateKey) keyPair.getPrivate(), en_data);
            newText = new String(data);
        }
        catch (Exception ex)
        {
            throw ex;
        }

        return StringUtils.reverse(newText);
    }
    
    /******************* 用户RSA加密处理，随机密匙 ****************/
    /**
     * 获取RSA随机钥匙,并且将密钥存放于session中
     * 
     * @param request
     */
    public static Map<String, Object> generateRSAKeys1(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, KeyPair> temps = (Map<String, KeyPair>) request.getSession().getAttribute(Constants.RSA_KEY);
        if (temps == null)
        {
            temps = new HashMap<String, KeyPair>();
            request.getSession().setAttribute(Constants.RSA_KEY, temps);
        }
        String key = UUID.randomUUID().toString();
        KeyPair keyPair = RSAUtils.generateKeyPair(key);
        temps.put(key, keyPair);
        request.getSession().setAttribute(Constants.KEY, key);
        map.put("_RSA_KEY", key);
        map.put("_RSA_EXPONENT", RSAUtils.getExponent(keyPair));
        map.put("_RSA_MODULES", RSAUtils.getModulus(keyPair));       
        return map;
    }
    
    public static void main(String[] args)
    {       

        String enString = "68c88d7ceb0f42ee33de4a0df962a55a6a33a2024d55ea0743f1016d6bec9ab4806b0b34e7ad039a37620152559ac26b8b6dfb6f56b59cb2b6c22fca18066aafde0af1494a58cd03aeb3c0b5a61bc17318fc01817a1587c1916f5ee99c3b8cbfcbe703cd24a3cd7655bd3b617be3018595c93933ed38fed00400afb85a53ce07";
        String enString1 = "766b66f9aeb79bb507e39d22dbcc9f468109c697ec10ee178ec3987eddc750fa5f47fa2103b89dc71a014dd18fab89284a78ca2fcbeb76d4d151f6d0768efde092552eecfcca6fe59939e851732e49ea99be06f13080db01148b74814378e65f2e7747c862e5653dcc5c9a3f295d6c76b2c3d747a5cd23141e84030599ca5263";
        System.out.println(RSAUtils.decryptString(enString));
        System.out.println(RSAUtils.decryptString(enString1));
    }
}