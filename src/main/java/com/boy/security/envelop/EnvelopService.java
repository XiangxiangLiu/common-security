package com.boy.security.envelop;

import com.boy.security.util.Base64Util;
import com.boy.security.util.CertUtil;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class EnvelopService {

    static {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
    }

    public String encryptEnvelop(String pubCertB64, byte[] srcData) {
        String envelopb64 = null;
        try {
            X509Certificate x509Certificate = CertUtil.getX509Cert(pubCertB64);
            CMSTypedData data = new CMSProcessableByteArray(srcData);
            CMSEnvelopedDataGenerator generator = new CMSEnvelopedDataGenerator();
            generator.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(x509Certificate).setProvider("BC"));
            CMSEnvelopedData envelopedData = generator.generate(data, new JceCMSContentEncryptorBuilder(PKCSObjectIdentifiers.rc4).setProvider("BC").build());
            ContentInfo contentInfo = envelopedData.toASN1Structure();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DEROutputStream dout = new DEROutputStream(bout);
            dout.writeObject(contentInfo);
            byte[] envelop = bout.toByteArray();
            envelopb64 = Base64Util.encode(envelop);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (CMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return envelopb64;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        EnvelopService sv = new EnvelopService();
        String certB64 = "MIIEHTCCAwWgAwIBAgIUche5La8wzyzammA2lNL9al/yQ08wDQYJKoZIhvcNAQEFBQAwZDEeMBwGA1UEAwwVV2FuZ1lpbiBVc2VyIENBKFRlc3QpMR8wHQYDVQQLDBZXYW5nWWluIFNlY3VyaXR5Q2VudGVyMRQwEgYDVQQKDAtXYW5nWWluLmNvbTELMAkGA1UEBhMCQ04wHhcNMTcwODEwMDI0NjMzWhcNMTgwODEwMDI0NjMzWjBdMQswCQYDVQQLDAJqcjELMAkGA1UECgwCamQxIjAgBgkqhkiG9w0BCQEWE3BlbmdzaGVuc2hlbkBqZC5jb20xHTAbBgNVBAMMFEFLUy1QVUIoQUtTMDAwMDBBS1MpMIIBIDANBgkqhkiG9w0BAQEFAAOCAQ0AMIIBCAKCAQEAxN1pYO6S0FhvajICeLs0+z8bTxHwcFUpybzK62IG+TYv37CeuEEQBpdmu1wPgxuyExplSDrDaNPHQs2ve0nzyXMiVBBllIA6JBMRnBgdW21ABkGQXOF6Ym57WM8lJyZBce7tJag/2QtLBoZSQlGN6u2M7PktPdgpC1l2Y8+H5Ox/4YQ4nSJ7jQsdl5bmg+WGKYZCx4CCExCuYnHonHoto6Bdx1MoWZnoFGLfSs5tMaBLVzVGG9Ss2pxydWUy4Xb37Ke9DLDu64euvtWmMmlVQOSEFMQ39Mh8/E0nShSqHoTiHYgVipgMLqOf/3mn/hcp3jYMriUlclTLvUwf51bfeQIBA6OBzzCBzDAJBgNVHRMEAjAAMAsGA1UdDwQEAwIE8DByBgNVHR8EazBpMGegZaBjhmFGVUxMTkFNRVM6VVJJOmh0dHA6Ly8xNzIuMjQuNi4zNDo4MDgwL3B1YmxpYy9pdHJ1c2NybD9DQT02QTU2RUQyRkZCQkM3QTk5MTU5MkEzN0MzMjFFOTU1QUMwNzM0OTc0MB8GA1UdIwQYMBaAFH/r/GfKS6oxTAbxVq4asoqL3zygMB0GA1UdDgQWBBREbqlmC5u6KmxPLyrRX/Lw0rXJCTANBgkqhkiG9w0BAQUFAAOCAQEAMlJ4aN1tfA+wq1Zf6Q9V8besfPP0J/5gqOB3u83Y9yYXMRsI7SE6Er5OFAecK1Q9Yw/M56wFziKjYwElYRHVrk4d9TTEZ5xzcravAf+0VVy549p3CuevIYbdH86ZHgd5MNG1QDbuFvR5GfZ2dRcPOIboqJTewWVsAuZXuiKDObyR9me45KX0cK8Hs5BA26tK/Apa2c5nAnFcZwvV3Ms71jnOc2XGd8oReCPmXoJTVv8bybYVDUrXPJOAAbLrmjBbZcciIJ6uGpV0cxUAuklx3TRZMSRv+iK9U0M5GYWybXuns1IxbRhDuksbpNFm1nTUa5Ak+aHQTGqAtHTABNKs2A==";
        byte[] srcData = "刘祥祥".getBytes("UTF-8");
        String envelop = sv.encryptEnvelop(certB64, srcData);
        System.out.println(envelop);
    }

    public byte[] decryptEnvelop(String pfxCertB64, String password, String envelopB64) {

        return null;
    }
}
