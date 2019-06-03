package com.spring.web.security;

import com.spring.web.util.EncodingUtil;
import com.spring.web.util.Utf8EncodeOrDecodeUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.codec.CodecSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: StandardPasswordEncoder
 * @Description:
 * @author FL
 * @date 2016年1月26日 上午11:34:49
 * @version V1.0
 */
public final class StandardPasswordEncoder extends CodecSupport implements CredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(StandardPasswordEncoder.class);
    private final Digester digester;
    private final byte[] secret;
    private final BytesKeyGenerator saltGenerator;

    /**
     * Constructs a standard password encoder with no additional secret value.
     */
    public StandardPasswordEncoder() {
        this("");
    }

    /**
     * @param secret
     */
    public StandardPasswordEncoder(CharSequence secret) {
        this("SHA-256", secret);
    }

    public String encode(CharSequence rawPassword) {
        return encode(rawPassword, saltGenerator.generateKey());
    }

    public boolean matches(CharSequence encodedPassword, CharSequence rawPassword) {
        byte[] digested = decode(encodedPassword);
        byte[] salt = EncodingUtil.subArray(digested, 0, saltGenerator.getKeyLength());
        return matches(digested, digest(rawPassword, salt));
    }

    // internal helpers
    private StandardPasswordEncoder(String algorithm, CharSequence secret) {
        this.digester = new Digester(algorithm, DEFAULT_ITERATIONS);
        this.secret = Utf8EncodeOrDecodeUtil.encode(secret);
        this.saltGenerator = KeyGenerators.secureRandom();
    }

    private String encode(CharSequence rawPassword, byte[] salt) {
        byte[] digest = digest(rawPassword, salt);
        return new String(Hex.encode(digest));
    }

    private byte[] digest(CharSequence rawPassword, byte[] salt) {
        byte[] digest = digester.digest(EncodingUtil.concatenate(salt, secret,
                Utf8EncodeOrDecodeUtil.encode(rawPassword)));
        return EncodingUtil.concatenate(salt, digest);
    }

    private byte[] decode(CharSequence encodedPassword) {
        return Hex.decode(encodedPassword);
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     */
    private boolean matches(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

    private static final int DEFAULT_ITERATIONS = 1024;

    /**
     * (非 Javadoc)
     * 
     * @param token
     * @param info
     * @return
     * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenCredentials = token.getCredentials();
        Object infoCredentials = info.getCredentials();
        if (isByteSource(tokenCredentials) && isByteSource(infoCredentials)) {
            if (log.isDebugEnabled()) {
                log.debug("Both credentials arguments can be easily converted to byte arrays.  Performing "
                        + "array equals comparison");
            }
            byte[] tokenBytes = toBytes(tokenCredentials);
            byte[] accountBytes = toBytes(infoCredentials);
            return matches(toString(accountBytes), toString(tokenBytes));
        } else {
            return matches(tokenCredentials.toString(), infoCredentials.toString());
        }
    }
}
